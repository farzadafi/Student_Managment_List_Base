package Repository;

import Entity.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentRepository implements Repository<Student> {
    @Override
    public int add(Student student) throws SQLException {
        return 0;
    }

    @Override
    public List<Student> findAll() throws SQLException {
        return null;
    }

    @Override
    public int update(Student student) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
