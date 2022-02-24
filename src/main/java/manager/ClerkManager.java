package manager;

import entity.Clerk;
import entity.Login;
import entity.enomeration.TypeUser;
import service.ClerkService;
import service.LoginService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClerkManager {
    private Utility utility = new Utility();
    private InvalidUsername invalidUsername = new InvalidUsername();
    private InvalidName invalidName = new InvalidName();
    private ClerkService clerkService = new ClerkService();
    private LoginService loginService = new LoginService();
    private InvalidNationalIdException invalidNationalIdException = new InvalidNationalIdException();
    private String firstName,lastName,nationalId,username,password;
    private Scanner input = new Scanner(System.in);
    private InvalidPassword invalidPassword = new InvalidPassword();

    public ClerkManager() throws SQLException, ClassNotFoundException {
    }


    public void addClerk() throws SQLException {
        firstName = utility.setFirstName();
        lastName = utility.setLastName();
        nationalId = utility.setNationalId();
        username = utility.setUsername();
        password = utility.setPassword();
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

        firstName = utility.setFirstName();
        lastName = utility.setLastName();
        password = utility.setPassword();
        Clerk clerk = new Clerk(firstName,lastName,nationalId,null,password);
        int result = clerkService.updateClerk(clerk);
        if(result == 0 )
            System.out.println("something is wrong!");
        else
            System.out.println("Edit successful!");


    }


}
