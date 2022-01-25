package Manager;

import Entity.Login;
import Service.LoginService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Utility {
    private Scanner input = new Scanner(System.in);
    private InvalidPassword invalidPassword = new InvalidPassword();
    private String password,firstName,username;
    private InvalidName invalidName = new InvalidName();
    private InvalidUsername invalidUsername = new InvalidUsername();
    private LoginService loginService = new LoginService();

    public Utility() throws SQLException, ClassNotFoundException {
    }

    public String setPassword(){
        while(true) {
            System.out.print("Enter your password:");
            try {
                password = input.nextLine();
                invalidPassword.passwordCheck(password);
                break;
            } catch (InvalidPassword except) {
                System.out.println(except.getMessage());
            }
        }
        return password;
    }

    public String setFirstName(){
        while(true){
            System.out.print("Enter first name(just alpha):");
            try {
                firstName = input.nextLine();
                invalidName.checkName(firstName);
                break;
            }catch (InvalidName except){
                System.out.println(except.getMessage());
            }
        }
        return firstName;
    }

    public String setLastName(){
        while(true){
            System.out.print("Enter last name(just alpha):");
            try {
                firstName = input.nextLine();
                invalidName.checkName(firstName);
                break;
            }catch (InvalidName except){
                System.out.println(except.getMessage());
            }
        }
        return firstName;
    }

    public String setUsername() throws SQLException {
        while(true) {
            while(true) {
                System.out.print("Enter username(start with alpha):");
                try {
                    username = input.nextLine();
                    invalidUsername.checkUsername(username);
                    break;
                } catch (InvalidUsername except) {
                    System.out.println(except.getMessage());
                }
            }
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
        return username;
    }
}
