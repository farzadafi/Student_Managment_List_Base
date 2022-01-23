package Manager;

import Entity.Login;
import Entity.Student;
import Entity.TypeUser;
import Service.LoginService;
import Service.StudentService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private StudentService studentService = new StudentService();
    private LoginService loginService = new LoginService();
    private Scanner input = new Scanner(System.in);
    private String firstName,lastName,nationalId,username,password;

    public StudentManager() throws SQLException, ClassNotFoundException {
    }

    public void addStudent() throws SQLException {
        System.out.print("Enter first name:");
        firstName = input.nextLine();
        System.out.print("Enter last name:");
        lastName = input.nextLine();
        while(true) {
            System.out.print("Enter nationalId:");
            nationalId = input.nextLine();
            try {
                nationalIdChecker(nationalId);
                break;
            }catch (InvalidNationalIdException exception){
                System.out.println("You enter a wrong nationalId!");
            }
        }
        while(true) {
            System.out.print("Enter username:");
            username = input.nextLine();
            List<Login> loginList = loginService.findAll();
            try{
                for (Login login:loginList)
                {
                    Login login1 = new Login();
                    login1 = login;
                    if(login.getUsername().equals(username)){
                        System.out.println("This username define before!");
                        break;
                    }
                    else
                        break;
                }
                break;
            }catch (Exception exception){
                break;
            }
        }
        System.out.print("Enter password:");
        password = input.nextLine();
        Student student = new Student(firstName,lastName,nationalId,username,password);
        studentService.addStudnet(student);
        Login login = new Login(username,password,TypeUser.STUDENT);
        loginService.addLogin(login);
        System.out.println(firstName + " successful added!");
        }


        //::::>
        public void nationalIdChecker(String nationalId){
        if(nationalId.length() > 10 ){
            throw new InvalidNationalIdException();
        }
            for (Character ch:nationalId.toCharArray()) {
                if(!Character.isDigit(ch))
                    throw new InvalidNationalIdException();
            }
        }
}
