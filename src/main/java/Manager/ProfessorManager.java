package Manager;

import Entity.*;
import Service.LoginService;
import Service.ProfessorService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfessorManager {
    private ProfessorService professorService = new ProfessorService();
    private LoginService loginService = new LoginService();
    private InvalidNationalIdException invalidNationalIdException = new InvalidNationalIdException();
    private Scanner input = new Scanner(System.in);
    private String firstName,lastName,username,password,nationalId;
    private ProfessorType professorType;

    public ProfessorManager() throws SQLException, ClassNotFoundException {
    }

    public void addProfessor() throws SQLException {
        System.out.print("Enter first name:");
        firstName = input.nextLine();
        System.out.print("Enter last name:");
        lastName = input.nextLine();
        while(true) {
            System.out.print("Enter nationalId:");
            nationalId = input.nextLine();
            try {
                invalidNationalIdException.nationalIdChecker(nationalId);
                break;
            }catch (InvalidNationalIdException exception){
                System.out.println("You enter a wrong nationalId!");
            }
        }
        while(true) {
            System.out.print("Enter username:");
            username = input.nextLine();
            List<Login> loginList = loginService.findAll();
            if(loginList == null)
                break;
            int i = 0;
            for (Login login:loginList)
            {
                Login login1 = new Login();
                login1 = login;
                if(login.getUsername().equals(username)){
                    i = 1;
                    break;
                }
            }
            if(i == 1)
                System.out.println("This username is defined before!");
            else
                break;
        }
        System.out.print("Enter password:");
        password = input.nextLine();
        int science = 0;
        System.out.print(firstName + " is a science?(0 or 1):");
        try {
             science = input.nextInt();
        }catch (Exception exception){
            System.out.println("You enter a wrong number!");
            return;
        }
        Professor professor;
        if(science == 0 ) {
            professor = new Professor(firstName,lastName,nationalId,username,password,ProfessorType.NORMAL);
        }
        else if(science == 1){
            professor = new Professor(firstName,lastName,nationalId,username,password,ProfessorType.SCIENCE);
        }
        else{
            System.out.println("You enter a wrong number!");
            return;
        }
        professorService.addProfessor(professor);
        Login login = new Login(username,password, TypeUser.PROFESSOR);
        loginService.addLogin(login);
        System.out.println(firstName + " successful added!");
    }

    public void deleteProfessor() throws SQLException {
        System.out.print("Enter username for delete:");
        username = input.nextLine();
        List<Login> loginList = loginService.findAll();
        if(loginList == null){
            System.out.println("Table is empty");
            return;
        }
        int i = 0;
        for (Login login:loginList)
        {
            Login login1 = new Login();
            login1 = login;
            if(login.getUsername().equals(username)){
                i = 1;
                break;
            }
        }
        if(i == 0) {
            System.out.println("This username isn't defined before!");
            return;
        }
        professorService.delete(username);
        loginService.delete(username);
        System.out.println(username + " successful deleted!");
    }

    public void updateProfessor() throws SQLException {
        List<Professor> professorList = new ArrayList<>();
        professorList = professorService.findAll();
        if(professorList == null){
            System.out.println("You dont have any student!");
            return;
        }
        System.out.print("Enter nationalId for edit:");
        nationalId = input.nextLine();
        int i = 0;
        for (Professor professor : professorList) {
            Professor professor1 = new Professor();
            professor1 = professor;
            if (professor.getNationalId().equals(nationalId)) {
                i = 1;
                break;
            }
        }
        if(i == 0){
            System.out.println("This national id is not found!");
            return;
        }

        System.out.print("Enter new first name:");
        firstName = input.nextLine();
        System.out.print("Enter new last name:");
        lastName = input.nextLine();
        System.out.print("Enter new password:");
        password = input.nextLine();
        Professor professor = new Professor(firstName,lastName,nationalId,null,password,null);
        int result = professorService.updateProfessor(professor);
        if(result == 0 )
            System.out.println("something is wrong!");
        else
            System.out.println("Edit successful!");
    }





}