package service;

import entity.Lesson;
import repository.LessonRepository;

import java.sql.SQLException;
import java.util.List;

public class LessonService {
    private LessonRepository lessonRepository = new LessonRepository();

    public LessonService() {
    }


    public void addLesson(Lesson lesson) {
        try {
            lessonRepository.add(lesson);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Lesson> findAll() {
        try {
            return lessonRepository.findAll();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int addGrade(Lesson lesson) {
        try {
            return lessonRepository.update(lesson);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
