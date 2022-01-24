package Service;

import Entity.Clerk;
import Entity.Lesson;
import Repository.LessonRepository;

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
}
