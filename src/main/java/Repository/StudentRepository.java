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
                                                               "firstName varcahr(50)," +
                                                               "lastName varchar(50), " +
                                                               "nationalId varchar(50), " +
                                                               "username varchar(50), " +
                                                               "password varchar(50))";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }


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
