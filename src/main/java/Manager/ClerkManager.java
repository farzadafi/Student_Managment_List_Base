package Manager;

import Entity.Clerk;
import Entity.Login;
import Entity.Student;
import Entity.TypeUser;
import Service.ClerkService;
import Service.LoginService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClerkManager {
    private InvalidUsername invalidUsername = new InvalidUsername();
    private InvalidName invalidName = new InvalidName();
    private ClerkService clerkService = new ClerkService();
    private LoginService loginService = new LoginService();
    private InvalidNationalIdException invalidNationalIdException = new InvalidNationalIdException();
    private String firstName,lastName,nationalId,username,password;
    private Scanner input = new Scanner(System.in);

    public ClerkManager() throws SQLException, ClassNotFoundException {
    }


    public void addClerk() throws SQLException {
        System.out.print("Enter first name(just alpha):");
        try {
            firstName = input.nextLine();
            invalidName.checkName(firstName);
        }catch (InvalidName name){
            System.out.println(name.getMessage());
            return;
        }
        System.out.print("Enter last name(just alpha):");
        try {
            lastName = input.nextLine();
            invalidName.checkName(lastName);
        }catch (InvalidName name){
            System.out.println(name.getMessage());
            return;
        }
        while(true) {
            System.out.print("Enter nationalId(just digit):");
            nationalId = input.nextLine();
            try {
                invalidNationalIdException.nationalIdChecker(nationalId);
                break;
            }catch (InvalidNationalIdException exception){
                System.out.println("You enter a wrong nationalId!");
            }
        }
        while(true) {
            System.out.print("Enter username(start with alpha):");
            try {
                username = input.nextLine();
                invalidUsername.checkUsername(username);
            }catch (InvalidUsername except){
                System.out.println(except.getMessage());
                return;
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
        System.out.print("Enter password:");
        password = input.nextLine();
        Clerk clerk = new Clerk(firstName,lastName,nationalId,username,password);
        clerkService.addClerk(clerk);

        Login login = new Login(username,password, TypeUser.CLERK);
        loginService.addLogin(login);
        System.out.println(firstName + " successful added!");
    }

    public void deleteClerk() throws SQLException {
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
        clerkService.delete(username);
        System.out.println(username + " successful deleted!");
    }

    public void updateClerk() throws SQLException {
        List<Clerk> clerkList = new ArrayList<>();
        clerkList = clerkService.findAll();
        if(clerkList == null){
            System.out.println("You dont have any student!");
            return;
        }
        System.out.println("Enter nationalId for edit:");
        nationalId = input.nextLine();
        int i = 0;
        for (Clerk clerk : clerkList) {
            Clerk clerk1 = new Clerk();
            clerk1 = clerk;
            if (clerk.getNationalId().equals(nationalId)) {
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
        Clerk clerk = new Clerk(firstName,lastName,nationalId,null,password);
        int result = clerkService.updateClerk(clerk);
        if(result == 0 )
            System.out.println("something is wrong!");
        else
            System.out.println("Edit successful!");


    }


}
