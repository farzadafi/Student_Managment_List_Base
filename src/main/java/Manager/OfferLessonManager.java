package Manager;

import Entity.Login;
import Entity.OfferLesson;
import Repository.OfferLessonRepository;
import Service.OfferLessonService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OfferLessonManager {
    private InvalidUnitNumber invalidUnitNumber = new InvalidUnitNumber();
    private Scanner input = new Scanner(System.in);
    private OfferLessonService offerLessonService = new OfferLessonService();
    private String lessonName;
    private int unitNumber;

    public OfferLessonManager() throws SQLException, ClassNotFoundException {
    }

    public void OfferLesson() throws SQLException {
        while(true) {
            System.out.print("Enter lesson name:");
            lessonName = input.nextLine();
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
                return;
            }
            try {
                invalidUnitNumber.unitNumberChecker(unitNumber);
                break;
            }catch (InvalidUnitNumber exception){
                System.out.println("You enter a wrong unitNumber!");
            }
        }
        OfferLesson offerLesson = new OfferLesson(lessonName,unitNumber);
        offerLessonService.addOfferLesson(offerLesson);
        System.out.println(lessonName + " is successful added!");

    }
}
