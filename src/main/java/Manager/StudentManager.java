package Manager;

import Entity.Login;
import Entity.Student;
import Entity.enomeration.TypeUser;
import Service.LoginService;
import Service.StudentService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private Utility utility = new Utility();
    private InvalidName invalidName = new InvalidName();
    private InvalidUsername invalidUsername = new InvalidUsername();
    private InvalidNationalIdException invalidNationalIdException = new InvalidNationalIdException();
    private StudentService studentService = new StudentService();
    private LoginService loginService = new LoginService();
    private Scanner input = new Scanner(System.in);
    private String firstName,lastName,nationalId,username,password;
    private InvalidPassword invalidPassword = new InvalidPassword();

    public StudentManager() throws SQLException, ClassNotFoundException {
    }

    public void addStudent() throws SQLException {
        firstName = utility.setFirstName();
        lastName = utility.setLastName();
        nationalId = utility.setNationalId();
        username = utility.setUsername();
        password = utility.setPassword();
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

            firstName = utility.setFirstName();
            lastName = utility.setLastName();
            password = utility.setPassword();
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
