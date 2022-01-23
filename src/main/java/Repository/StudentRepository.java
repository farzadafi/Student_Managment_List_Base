package Repository;

import Entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentRepository implements Repository<Student> {
    Connection connection = Singleton.getInstance().getConnection();

    public StudentRepository() throws SQLException, ClassNotFoundException {
        String createTable = " CREATE TABLE IF NOT EXISTS Student(id serial" +
                                                               "firstName varchar(50)," +
                                                               "lastName varchar(50), " +
                                                               "nationalId varchar(50), " +
                                                               "username varchar(50), " +
                                                               "password varchar(50))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
    }


    @Override
    public int add(Student student) throws SQLException {
        String add = " INSERT INTO Student (firstName,lastName,nationalId,username,password) VALUES (? ,? ,?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1, student.getFirstName());
        preparedStatement.setString(2, student.getLastName());
        preparedStatement.setString(3, student.getNationalId());
        preparedStatement.setString(4, student.getUsername());
        preparedStatement.setString(5, student.getPassword());
        try {
            return preparedStatement.executeUpdate();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
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
