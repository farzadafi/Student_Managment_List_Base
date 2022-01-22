package Repository;

import Entity.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProfessorRepository implements Repository<Professor> {
    Connection connection = Singleton.getInstance().getConnection();

    public ProfessorRepository() throws SQLException, ClassNotFoundException {
        String createTable = " CREATE TABLE IF NOT EXISTS Professor(id serial" +
                                                               "firstName varcahr(50)," +
                                                               "lastName varchar(50), " +
                                                               "nationalId varchar(50), " +
                                                               "username varchar(50), " +
                                                               "password varchar(50), " +
                                                               "kindProfessor varchar(50))";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }


    @Override
    public int add(Professor professor) throws SQLException {
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
