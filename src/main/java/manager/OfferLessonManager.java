package manager;

import entity.OfferLesson;
import manager.exception.InvalidUnitNumber;
import manager.exception.InvalidUsername;
import service.OfferLessonService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OfferLessonManager {
    private InvalidUsername invalidUsername = new InvalidUsername();
    private InvalidUnitNumber invalidUnitNumber = new InvalidUnitNumber();
    private Scanner input = new Scanner(System.in);
    private OfferLessonService offerLessonService = new OfferLessonService();
    private String lessonName;
    private int unitNumber;

    public OfferLessonManager() throws SQLException, ClassNotFoundException {
    }

    public void OfferLesson() throws SQLException {
        while(true) {
            System.out.print("Enter lesson name(start with alpha):");
            try {
                lessonName = input.nextLine();
                checkUsername(lessonName);
            }catch (InvalidUsername except){
                System.out.println("lesson name can not start with digit!");
                return;
            }
            List<OfferLesson> offerLessonList = offerLessonService.findAll();
            if(offerLessonList == null)
                break;
            int i = 0;
            for (OfferLesson offerLesson:offerLessonList)
            {
                OfferLesson offerLesson1 = new OfferLesson();
                offerLesson1 = offerLesson;
                if(offerLesson.getLessonName().equals(lessonName)){
                    i = 1;
                    break;
                }
            }
            if(i == 1)
                System.out.println("This lesson name is defined before!");
            else
                break;
        }
        while(true) {
            System.out.print("Enter unitNumber:");
            try {
                unitNumber = input.nextInt();
                input.nextLine();
            }catch (InputMismatchException exception){
                System.out.println("You have just enter a number!");
                input.nextLine();
                return;
            }
            try {
                unitNumberChecker(unitNumber);
                break;
            }catch (InvalidUnitNumber exception){
                System.out.println("You enter a wrong unitNumber!");
            }
        }
        OfferLesson offerLesson = new OfferLesson(lessonName,unitNumber);
        offerLessonService.addOfferLesson(offerLesson);
        System.out.println(lessonName + " is successful added!");

    }

    public void showAll() throws SQLException {
        List<OfferLesson> offerLessonList = offerLessonService.findAll();
        if(offerLessonList == null ){
            System.out.println("Offer list is empty!");
            return;
        }
        for (OfferLesson offer:offerLessonList) {
            System.out.println(offer.toString());
        }
    }

    public void unitNumberChecker(int unitNumber){
        if(unitNumber > 4 || unitNumber < 1 )
            throw new InvalidUnitNumber("You enter a wrong unit number!");
    }

    public void checkUsername(String username){
        if(username.length() < 3 )
            throw new InvalidUsername("Username should be more than 2!");
        char ch = username.charAt(0);
        if(Character.isDigit(ch))
            throw new InvalidUsername("Username can not start with digit!");
    }
}
