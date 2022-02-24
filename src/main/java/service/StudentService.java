package service;

import entity.Student;
import repository.StudentRepository;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private StudentRepository studentRepository = new StudentRepository();

    public StudentService() throws SQLException, ClassNotFoundException {
    }

    public void addStudnet(Student student) throws SQLException {
        studentRepository.add(student);
    }

    public List<Student> findAll() throws SQLException {
        return studentRepository.findAll();
    }

    public int updateStudent(Student student) throws SQLException {
        return studentRepository.update(student);
    }

    public int delete(String username) throws SQLException {
        return studentRepository.delete(username);
    }

    public int returnId(String username) throws SQLException {
        return studentRepository.returnId(username);
    }
}
