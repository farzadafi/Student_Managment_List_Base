package Repository;

import Entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements Repository<Student> {
    Connection connection = Singleton.getInstance().getConnection();

    public StudentRepository() throws SQLException, ClassNotFoundException {
        String createTable = " CREATE TABLE IF NOT EXISTS Student(id serial," +
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
        String find = "SELECT * FROM Student ";
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        List<Student> studentList = new ArrayList<>();
        ResultSet resultSet;
        try{
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
            return null;
        }
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
        try{
            preparedStatement.executeUpdate();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
            return 0;
        }
        return 1;
    }

    @Override
    public int delete(String username) throws SQLException {
        String delete = " DELETE FROM Student WHERE username = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setString(1,username);
        try {
            return preparedStatement.executeUpdate();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
        return 0;
    }

    public int returnId(String username) throws SQLException {
        String id = "SELECT * FROM Student WHERE username = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(id);
        preparedStatement.setString(1,username);
        ResultSet resultSet;
        try {
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            return 0;
        }
        if(resultSet.next()){
            return resultSet.getInt("id");
        }
        else
            return 0;

    }
}
