package Manager;

import Entity.Clerk;
import Entity.Login;
import Entity.Student;
import Entity.TypeUser;
import Service.ClerkService;
import Service.LoginService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClerkManager {
    private ClerkService clerkService = new ClerkService();
    private LoginService loginService = new LoginService();
    private InvalidNationalIdException invalidNationalIdException = new InvalidNationalIdException();
    private String firstName,lastName,nationalId,username,password;
    private Scanner input = new Scanner(System.in);

    public ClerkManager() throws SQLException, ClassNotFoundException {
    }


    public void addClerk() throws SQLException {
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
        Clerk clerk = new Clerk(firstName,lastName,nationalId,username,password);
        clerkService.addClerk(clerk);

        Login login = new Login(username,password, TypeUser.CLERK);
        loginService.addLogin(login);
        System.out.println(firstName + " successful added!");
    }
}
