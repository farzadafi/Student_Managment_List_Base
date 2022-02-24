package repository;

import entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements Repository<Student> {
    private Connection connection;

    public StudentRepository() {
        try {
            connection = Singleton.getInstance().getConnection();
            String createTable = " CREATE TABLE IF NOT EXISTS Student(id serial," +
                                                               "firstName varchar(50)," +
                                                               "lastName varchar(50), " +
                                                               "nationalId varchar(50), " +
                                                               "username varchar(50), " +
                                                               "password varchar(50))";
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
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
        return preparedStatement.executeUpdate();
    }

    @Override
    public List<Student> findAll() throws SQLException {
        String find = "SELECT * FROM Student ";
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        List<Student> studentList = new ArrayList<>();
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        if(resultSet.isBeforeFirst()){
            while(resultSet.next()) {
                Student student = new Student();
                student.setFirstName(resultSet.getString("firstname"));
                student.setLastName(resultSet.getString("lastName"));
                student.setNationalId(resultSet.getString("nationalId"));
                student.setUsername(resultSet.getString("username"));
                student.setPassword(resultSet.getString("password"));
                studentList.add(student);
            }
            return studentList;
        }
        else
            return null;
    }

    @Override
    public int update(Student student) throws SQLException {
        String update =  "UPDATE Student SET firstname = ? , lastName = ? , password = ? WHERE nationalId = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1,student.getFirstName());
        preparedStatement.setString(2,student.getLastName());
        preparedStatement.setString(3,student.getPassword());
        preparedStatement.setString(4,student.getNationalId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(String username) throws SQLException {
        String delete = " DELETE FROM Student WHERE username = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setString(1,username);
        return preparedStatement.executeUpdate();
    }

    public int returnId(String username) throws SQLException {
        String id = "SELECT * FROM Student WHERE username = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(id);
        preparedStatement.setString(1,username);
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt("id");
        }
        else
            return 0;
    }
}
