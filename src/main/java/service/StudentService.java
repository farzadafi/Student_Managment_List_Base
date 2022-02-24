package service;

import entity.Student;
import repository.StudentRepository;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private StudentRepository studentRepository = new StudentRepository();

    public StudentService() {
    }

    public void addStudnet(Student student) {
        try {
            studentRepository.add(student);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Student> findAll() {
        try {
            return studentRepository.findAll();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int updateStudent(Student student) {
        try {
            return studentRepository.update(student);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int delete(String username) {
        try {
            return studentRepository.delete(username);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int returnId(String username) {
        try {
            return studentRepository.returnId(username);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
