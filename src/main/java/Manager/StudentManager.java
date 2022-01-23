package Manager;

import Entity.Login;
import Entity.Student;
import Entity.TypeUser;
import Service.LoginService;
import Service.StudentService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private InvalidNationalIdException invalidNationalIdException = new InvalidNationalIdException();
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
        Student student = new Student(firstName,lastName,nationalId,username,password);
        studentService.addStudnet(student);
        Login login = new Login(username,password,TypeUser.STUDENT);
        loginService.addLogin(login);
        System.out.println(firstName + " successful added!");
        }


        public void updateStudent() throws SQLException {
            List<Student> studentList = new ArrayList<>();
            studentList = studentService.findAll();
            if(studentList == null){
                System.out.println("You dont have any student!");
                return;
            }
            System.out.println("Enter nationalId for edit:");
            nationalId = input.nextLine();
            int i = 0;
                for (Student student : studentList) {
                    Student student1 = new Student();
                    student1 = student;
                    if (student.getNationalId().equals(nationalId)) {
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
            Student student = new Student(firstName,lastName,nationalId,null,password);
            int result = studentService.updateStudent(student);
            if(result == 0 )
                System.out.println("something is wrong!");
            else
                System.out.println("Edit successful!");


        }

        public void deleteStudent() throws SQLException {
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
            loginService.delete(username);
            studentService.delete(username);
            System.out.println(username + " successful deleted!");
        }

        public int returnId(String username) throws SQLException {
        return studentService.returnId(username);
        }





}
