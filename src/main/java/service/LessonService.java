package service;

import entity.Lesson;
import repository.LessonRepository;

import java.sql.SQLException;
import java.util.List;

public class LessonService {
    private LessonRepository lessonRepository = new LessonRepository();

    public LessonService() throws SQLException, ClassNotFoundException {
    }


    public void addLesson(Lesson lesson) throws SQLException {
         lessonRepository.add(lesson);
    }

    public List<Lesson> findAll() throws SQLException {
        return lessonRepository.findAll();
    }

    public int addGrade(Lesson lesson) throws SQLException {
        return lessonRepository.update(lesson);
    }
}
