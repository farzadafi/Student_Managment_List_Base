import Entity.Login;
import Service.LoginService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private int command;
    private String userName,password;
    private LoginService loginService = new LoginService();

    public Menu() throws SQLException, ClassNotFoundException {
    }


    public int mainMenu(){
        System.out.println("**********WELCOME**********");
        System.out.println("1-Enter.");
        System.out.println("2-Exit.");
        System.out.print("Please enter your command:");
        try {
            command = input.nextInt();
        }catch (InputMismatchException exception){
            input.nextLine();
            return 3;
        }
        input.nextLine();
        switch(command) {
            case 1:
                return 1;

            case 2:
                return 2;

            default:
                return 0;
        }
    }

    public void  enterMenu() throws SQLException {
        System.out.print("Please enter your username:");
        userName = input.nextLine();
        System.out.print("Please enter your password:");
        password = input.nextLine();
        if( userName.equals("admin") && password.equals("admin"))
            clerkMenu(userName);
        else{
            String result = null ;
            List<Login> loginList = loginService.findAll();
            try {

                for (Login login : loginList) {
                    Login login1 = new Login();
                    login1 = login;
                    if (login.getUsername().equals(userName) && login.getPassword().equals(password)) {
                        result = String.valueOf(login.getTypeUser());
                        break;
                    }

                }
            }catch (Exception exception){
                System.out.println("Login table is empty!");
                return;
            }
            if(result == null) {
                System.out.println("You enter a wrong username and password!");
                return;
            }
           if(result.equals("CLERK"))
               clerkMenu(userName);
           else if(result.equals("STUDENT"))
               studentMenu(userName);
           else if(result.equals("PROFESSOR"))
               professorMenu(userName);
        }
    }

    public void clerkMenu(String username){

    }

    public void studentMenu(String username){

    }

    public void professorMenu(String username){

    }







}
