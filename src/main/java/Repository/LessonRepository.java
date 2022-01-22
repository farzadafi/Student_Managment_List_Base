package Repository;

import Entity.Lesson;

import java.sql.SQLException;
import java.util.List;

public class LessonRepository implements Repository<Lesson> {
    @Override
    public int add(Lesson lesson) throws SQLException {
        return 0;
    }

    @Override
    public List<Lesson> findAll() throws SQLException {
        return null;
    }

    @Override
    public int update(Lesson lesson) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
