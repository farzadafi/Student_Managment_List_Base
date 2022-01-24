package Service;

import Entity.Lesson;
import Repository.LessonRepository;

import java.sql.SQLException;

public class LessonService {
    private LessonRepository lessonRepository = new LessonRepository();

    public LessonService() throws SQLException, ClassNotFoundException {
    }


    public void addLesson(Lesson lesson) throws SQLException {
         lessonRepository.add(lesson);
    }
}
