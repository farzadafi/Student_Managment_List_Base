import entity.Clerk;
import entity.Login;
import manager.*;
import service.LoginService;
import service.OfferLessonService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private OfferLessonService offerLessonService = new OfferLessonService();
    private ClerkManager clerkManager = new ClerkManager();
    private StudentManager studentManager = new StudentManager();
    private ProfessorManager professorManager = new ProfessorManager();
    private OfferLessonManager offerLessonManager = new OfferLessonManager();
    private LessonManager lessonManager = new LessonManager();
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

    public void clerkMenu(String username) throws SQLException {
        boolean finalWhile = true;
        while (finalWhile) {
            System.out.println("****** Hi! " + username + " ******");
            System.out.println("1-Student Tools.");
            System.out.println("2-Professor Tools.");
            System.out.println("3-Clerk Tools.");
            System.out.println("4-Unit Tools.");
            System.out.println("5-View Salary bill.");
            System.out.println("6-Exit.");
            System.out.print("Please select:");
            try {
                command = input.nextInt();
            }catch (InputMismatchException exception){
                input.nextLine();
                System.out.println("You can just Enter number!");
                return;
            }
            input.nextLine();
            switch (command) {
                case 1:
                    System.out.println("*** Student Tool ***");
                    System.out.println("1-Register Student.");
                    System.out.println("2-Delete Student.");
                    System.out.println("3-Edit Student.");
                    System.out.print("Please select a number:");
                    try {
                        command = input.nextInt();
                    }catch (InputMismatchException exception){
                        input.nextLine();
                        System.out.println("You can just Enter number!");
                        return;
                    }
                    input.nextLine();
                    switch (command){
                        case 1:
                            studentManager.addStudent();
                            break;

                        case 2:
                            studentManager.deleteStudent();
                            break;

                        case 3:
                            studentManager.updateStudent();
                            break;

                        default:
                            System.out.println("You enter a wrong number!");

                    }

                    break;

                case 2:
                    System.out.println("*** Professor Tool ***");
                    System.out.println("1-Register Professor.");
                    System.out.println("2-Delete Professor.");
                    System.out.println("3-Edit Professor.");
                    System.out.print("Please select a number:");
                    try {
                        command = input.nextInt();
                    }catch (InputMismatchException exception){
                        input.nextLine();
                        System.out.println("You can just Enter number!");
                        return;
                    }
                    input.nextLine();
                    switch (command){
                        case 1:
                            professorManager.addProfessor();
                            break;

                        case 2:
                            professorManager.deleteProfessor();
                            break;

                        case 3:
                            professorManager.updateProfessor();
                            break;

                        default:
                            System.out.println("You enter a wrong number!");

                    }
                    break;

                case 3:
                    System.out.println("*** Clerk Tool ***");
                    System.out.println("1-Register clerk.");
                    System.out.println("2-Delete clerk.");
                    System.out.println("3-Edit clerk.");
                    System.out.print("Please select a number:");
                    try {
                        command = input.nextInt();
                    }catch (InputMismatchException exception){
                        input.nextLine();
                        System.out.println("You can just Enter number!");
                        return;
                    }
                    input.nextLine();
                    switch (command){
                        case 1:
                            clerkManager.addClerk();
                            break;

                        case 2:
                            clerkManager.deleteClerk();
                            break;

                        case 3:
                            clerkManager.updateClerk();
                            break;

                        default:
                            System.out.println("You enter a wrong number!");

                    }
                    break;

                case 4:
                    offerLessonManager.OfferLesson();
                    break;

                case 5:
                    Clerk clerk = new Clerk();
                    System.out.println("Your salary is:" + clerk.getCalcSalary());
                    break;

                case 6:
                    System.out.println("Good luck!");
                    finalWhile = false;
                    break;

                default:
                    System.out.println("You enter a wrong number!");

            }//switch
        }//while
    }//

    public void studentMenu(String username) throws SQLException {
        int idStudent = studentManager.returnId(username);
        if(idStudent == 0){
            System.out.println("Unfortunately your id not found!");
            return;
        }
        boolean finalWhile = true;
        while(finalWhile)
        {
            System.out.println("****** Hi! " + username + " ******");
            System.out.println("1-view lessonList.");
            System.out.println("2-Unit select.");
            System.out.println("3-view lesson and grade's.");
            System.out.println("4-Exit.");
            System.out.print("Please select a number:");
            try {
                command = input.nextInt();
            }catch (InputMismatchException exception){
                input.nextLine();
                System.out.println("You can just Enter number!");
                return;
            }
            input.nextLine();
            switch (command)
            {
                case 1:
                    offerLessonManager.showAll();
                    break;

                case 2:
                    lessonManager.selectLesson(username,idStudent);
                    break;

                case 3:
                    lessonManager.viewLesson(idStudent);
                    break;

                case 4:
                    System.out.println("Good luck!");
                    finalWhile = false;
                    break;

                default:
                    System.out.println("You enter a wrong number!");

            }//switch
        }//while

    }

    public void professorMenu(String username) throws SQLException {
        boolean falseWhile = true;
        while(falseWhile)
        {
            System.out.println("****** Hi! " + username + " ******");
            System.out.println("1-Register grad's Student.");
            System.out.println("2-view salary bill.");
            System.out.println("3-Exit.");
            System.out.print("please select a number:");
            try {
                command = input.nextInt();
            }catch (InputMismatchException exception){
                input.nextLine();
                System.out.println("You can just Enter number!");
                return;
            }
            input.nextLine();
            switch (command){
                case 1:
                    lessonManager.addGrade(username);
                    break;

                case 2:
                    professorManager.viewSalaryBill(username);
                    break;

                case 3:
                    System.out.println("Good luck!");
                    falseWhile = false;
                    break;

                default:
                    System.out.println("You enter a wrong number!");
            }

            }

    }







}
