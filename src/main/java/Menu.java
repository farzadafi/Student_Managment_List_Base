import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private int command;




    public int mainMenu(){
        System.out.println("**********WELCOME**********");
        System.out.println("1-Enter.");
        System.out.println("2-Exit.");
        System.out.print("Please enter your command:");
        command = input.nextInt();
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








}
