package Manager;

import java.util.Scanner;

public class Utility {
    private Scanner input = new Scanner(System.in);
    private InvalidPassword invalidPassword = new InvalidPassword();
    private String password;

    public String setPassword(){
        while(true) {
            System.out.print("Enter your password:");
            boolean isFalse = true;
            try {
                password = input.nextLine();
                invalidPassword.passwordCheck(password);
                isFalse = false;
            } catch (InvalidPassword except) {
                System.out.println(except.getMessage());
            }
            if(!isFalse)
                break;
        }
        return password;
    }
}
