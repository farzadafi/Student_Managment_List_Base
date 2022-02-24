package manager;

import entity.Lesson;
import entity.OfferLesson;
import entity.Professor;
import manager.exception.InvalidGrade;
import manager.exception.InvalidQuarterNumber;
import service.LessonService;
import service.OfferLessonService;
import service.ProfessorService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LessonManager {
    private InvalidGrade invalidGrade = new InvalidGrade();
    private ProfessorService professorService = new ProfessorService();
    private OfferLessonService offerLessonService = new OfferLessonService();
    private LessonService lessonService = new LessonService();
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
            invalidQuarterNumber(quarterNumber);
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
            input.nextLine();
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
        if(!checkLastProfessorName(lastProfessorName)){
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

    public boolean checkLastProfessorName(String name) throws SQLException {
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

    public void addGrade(String username) throws SQLException {
        String lastName = findLastName(username);
        showLesson(lastName);
        System.out.print("Enter Student id:");
        int studentId = -1;
        try {
            studentId = input.nextInt();
            input.nextLine();
        }catch (Exception exception){
            System.out.println("You enter a wrong ID");
            input.nextLine();
            return;
        }
        if(!checkStudentId(studentId)){
            System.out.println("You enter a wrong student Id!");
            return;
        }
        System.out.print("Enter lesson name:");
        lessonName = input.nextLine();
        if(!checkLessonName(studentId,lessonName)){
            System.out.println("This lesson name for this ID is not exsit!");
            return;
        }
        System.out.print("Enter Grade:");
        int grade = 0;
        try {
            grade = input.nextInt();
            input.nextLine();
            invalidGrade(grade);
        }catch (InvalidGrade invalidGrade){
            System.out.println(invalidGrade.getMessage());
            input.nextLine();
            return;
        }catch (Exception exception){
            System.out.println("just enter number Professor!are you buy your license?");
            input.nextLine();
            return;
        }
        Lesson lesson = new Lesson(studentId,-1,-1,-1,grade,lessonName,lastName);
        if(lessonService.addGrade(lesson) == 1 )
            System.out.println("Grade set!");
    }


    public String findLastName(String username) throws SQLException {
        List<Professor> professorList = new ArrayList<>();
        professorList = professorService.findAll();
        for (Professor pro:professorList
             ) {
            Professor professor = new Professor();
            professor = pro;
            if(pro.getUsername().equals(username))
                return pro.getLastName();
        }
        return null;
    }

    public void showLesson(String lastName) throws SQLException {
        List<Lesson> lessonList = new ArrayList<>();
        lessonList = lessonService.findAll();
        int i = 0;
        for (Lesson lesson:lessonList
             ) {
            Lesson lesson1 = new Lesson();
            lesson1 = lesson;
            if(lesson1.getLastProfessorName().equals(lastName) && lesson1.getGrade() == -1 ){
                i++;
                System.out.println(lesson1.toString());
            }

        }
        if(i == 0)
            System.out.println("You dont have any lesson without grade!");
    }

    public boolean checkStudentId(int idStudent) throws SQLException {
        List<Lesson> lessonList = new ArrayList<>();
        lessonList = lessonService.findAll();
        for (Lesson lesson:lessonList
             ) {
            Lesson lesson1 = new Lesson();
            lesson1 = lesson;
            if(lesson1.getIdStudent() == idStudent )
                return true;
        }
        return false;
    }

    public boolean checkLessonName(int idStudent,String lessonName) throws SQLException {
        List<Lesson> lessonList = new ArrayList<>();
        lessonList = lessonService.findAll();
        for (Lesson lesson:lessonList
             ) {
            Lesson lesson1 = new Lesson();
            lesson1 = lesson;
            if(lesson1.getIdStudent() == idStudent && lesson1.getLessonName().equals(lessonName))
                return true;
        }
        return false;
    }

    public void invalidGrade(int grade){
        if(grade > 20 || grade < 0 )
            throw new InvalidGrade("You enter a wrong grade");
    }

    public void invalidQuarterNumber(int quarterNumber) {
        if (quarterNumber > 10 || quarterNumber < 1) {
            throw new InvalidQuarterNumber("You enter a wrong quarter number!");
        }
    }


}
