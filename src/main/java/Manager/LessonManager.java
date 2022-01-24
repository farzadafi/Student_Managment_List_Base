package Manager;

import Entity.Lesson;
import Entity.OfferLesson;
import Entity.Professor;
import Service.LessonService;
import Service.OfferLessonService;
import Service.ProfessorService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LessonManager {
    private LessonService lessonService = new LessonService();
    private ProfessorService professorService = new ProfessorService();
    private OfferLessonService offerLessonService = new OfferLessonService();
    private InvalidQuarterNumber invalidQuarterNumber = new InvalidQuarterNumber();
    private Scanner input = new Scanner(System.in);
    private int quarterNumber,year,unitNumber;
    private String lessonName,lastProfessorName;

    public LessonManager() throws SQLException, ClassNotFoundException {
    }

    public void selectLesson(String username,int idStudent) throws SQLException {
        System.out.print("Enter quarter Number:");
        try {
            quarterNumber = input.nextInt();
            input.nextLine();
        }catch (Exception exception){
            System.out.println("you can enter just number!");
            input.nextLine();
            return;
        }
        try {
            invalidQuarterNumber.invalidQuarterNumber(quarterNumber);
        }catch (InvalidQuarterNumber exception){
            System.out.println(exception.getMessage());
            return;
        }

        System.out.print("Enter year:");
        try {
            year = input.nextInt();
            input.nextLine();
        }catch (Exception exception){
            System.out.println("you can enter just number!");
            return;
        }

        System.out.print("Enter lesson name:");
        lessonName = input.nextLine();
        unitNumber = findLessonName(lessonName);
        if(unitNumber == 0 ){
            System.out.println("This lesson is not exists!");
            return;
        }

        System.out.print("Enter lastProfessorName:");
        lastProfessorName = input.nextLine();
        if(!findLastProfessorName(lastProfessorName)){
            System.out.println("This professor is not exists!");
            return;
        }
        Lesson lesson = new Lesson(idStudent,quarterNumber,year,unitNumber,-1,lessonName,lastProfessorName);
        lessonService.addLesson(lesson);
        System.out.println("This lesson successful added!");
    }

    public void viewLesson(int idStudent) throws SQLException {
        List<Lesson> lessonList = new ArrayList<>();
        lessonList = lessonService.findAll();
        if(lessonList == null){
            System.out.println("you dont have any lesson yet!");
            return;
        }
        for (Lesson lesson:lessonList
             ) {
            Lesson lesson1 = new Lesson();
            lesson1 = lesson;
            if(lesson1.getIdStudent() == idStudent)
                System.out.println(lesson1.toString());
        }

    }

    public int findLessonName(String name) throws SQLException {
        List<OfferLesson> offerLessonList = new ArrayList<>();
        offerLessonList = offerLessonService.findAll();
        for (OfferLesson offer:offerLessonList
             ) {
            OfferLesson offerLesson = new OfferLesson();
            offerLesson = offer ;
            if(offer.getLessonName().equals(name)){
                return offer.getUnitNumber();
            }
        }
        return 0;
    }

    public boolean findLastProfessorName(String name) throws SQLException {
        List<Professor> professorList = new ArrayList<>();
        professorList = professorService.findAll();
        for (Professor pro:professorList
             ) {
            Professor professor = new Professor();
            professor = pro;
            if(pro.getLastName().equals(name))
                return true;
        }
        return false;
    }
}
