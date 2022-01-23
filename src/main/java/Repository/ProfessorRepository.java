package Repository;

import Entity.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProfessorRepository implements Repository<Professor> {
    Connection connection = Singleton.getInstance().getConnection();

    public ProfessorRepository() throws SQLException, ClassNotFoundException {
        String createTable = " CREATE TABLE IF NOT EXISTS Professor(id serial," +
                                                               "firstName varchar(50)," +
                                                               "lastName varchar(50), " +
                                                               "nationalId varchar(50), " +
                                                               "username varchar(50), " +
                                                               "password varchar(50), " +
                                                               "kindProfessor varchar(50))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
    }


    @Override
    public int add(Professor professor) throws SQLException {
        String add = " INSERT INTO Professor(firstName,lastName,nationalId,username,password,kindProfessor) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1,professor.getFirstName());
        preparedStatement.setString(2,professor.getLastName());
        preparedStatement.setString(3,professor.getNationalId());
        preparedStatement.setString(4,professor.getUsername());
        preparedStatement.setString(5,professor.getPassword());
        preparedStatement.setString(6, String.valueOf(professor.getProfessorType()));
        try {
            return preparedStatement.executeUpdate();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
        return 0;
    }

    @Override
    public List<Professor> findAll() throws SQLException {
        return null;
    }

    @Override
    public int update(Professor professor) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
