package Manager;

import java.util.Scanner;

public class Utility {
    private Scanner input = new Scanner(System.in);
    private InvalidPassword invalidPassword = new InvalidPassword();
    private String password,firstName;
    private InvalidName invalidName = new InvalidName();

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

    public
}
