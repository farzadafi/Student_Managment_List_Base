package Service;

import Entity.Student;
import Repository.StudentRepository;

import java.sql.SQLException;

public class StudentService {
    private StudentRepository studentRepository = new StudentRepository();

    public StudentService() throws SQLException, ClassNotFoundException {
    }

    public void addStudnet(Student student) throws SQLException {
        studentRepository.add(student);
    }
}
