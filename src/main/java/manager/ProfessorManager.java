package manager;

import entity.*;
import entity.enomeration.ProfessorType;
import entity.enomeration.TypeUser;
import service.LessonService;
import service.LoginService;
import service.ProfessorService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfessorManager {
    private Utility utility = new Utility();
    private InvalidUsername invalidUsername = new InvalidUsername();
    private InvalidName invalidName = new InvalidName();
    private LessonService lessonService = new LessonService();
    private LessonManager lessonManager = new LessonManager();
    private ProfessorService professorService = new ProfessorService();
    private LoginService loginService = new LoginService();
    private InvalidNationalIdException invalidNationalIdException = new InvalidNationalIdException();
    private Scanner input = new Scanner(System.in);
    private String firstName,lastName,username,password,nationalId;
    private Professor professor = new Professor();
    private InvalidPassword invalidPassword = new InvalidPassword();

    public ProfessorManager() throws SQLException, ClassNotFoundException {
    }

    public void addProfessor() throws SQLException {
        firstName = utility.setFirstName();
        lastName = utility.setLastName();
        nationalId = utility.setNationalId();
        username = utility.setUsername();
        password = utility.setPassword();
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
            professor = new Professor(firstName,lastName,nationalId,username,password, ProfessorType.NORMAL);
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
        firstName = utility.setFirstName();
        lastName = utility.setLastName();
        password = utility.setPassword();
        Professor professor = new Professor(firstName,lastName,nationalId,null,password,null);
        int result = professorService.updateProfessor(professor);
        if(result == 0 )
            System.out.println("something is wrong!");
        else
            System.out.println("Edit successful!");
    }


    public void viewSalaryBill(String username) throws SQLException {
        String lastName = lessonManager.findLastName(username);
        int unitNumber = calcUnitNumber(lastName);
        if(isCommit(username)) {
            System.out.println(professor.calcSalaryBill());
            System.out.println("Your Salary is: " + (professor.calcSalaryBill() + 1000000));
        }
        else
            System.out.println("Your Salary is: " + (professor.calcSalaryBill() + (unitNumber * 50000)));
    }

    public int calcUnitNumber(String lastName) throws SQLException {
        List<Lesson> lessonList = new ArrayList<>();
        lessonList = lessonService.findAll();
        if(lessonList == null)
            return 0;

        int sum = 0;
        for (Lesson lesson:lessonList
             ) {
            Lesson lesson1 = new Lesson();
            lesson1 = lesson;
            if(lesson1.getLastProfessorName().equals(lastName))
                sum += lesson1.getUnitNumber();
        }
        return sum;
    }

    public boolean isCommit(String username) throws SQLException {
        List<Professor> professorList = new ArrayList<>();
        professorList = professorService.findAll();

        for (Professor pro:professorList
             ) {
            Professor professor = new Professor();
            professor = pro;
            if(professor.getUsername().equals(username))
                if(professor.getProfessorType().toString().equals("SCIENCE"))
                    return true;
                else
                    return false;
        }
        return false;
    }




}
