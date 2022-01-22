package Repository;

import Entity.Clerk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ClerkRepository implements Repository<Clerk> {
    Connection connection = Singleton.getInstance().getConnection();

    public ClerkRepository() throws SQLException, ClassNotFoundException {
        String createTable = " CREATE TABLE IF NOT EXISTS Clerk(id serial" +
                                                        "firstName varcahr(50)," +
                                                        "lastName varchar(50), " +
                                                        "nationalId varchar(50), " +
                                                        "username varchar(50), " +
                                                        "password varchar(50))";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }


    @Override
    public int add(Clerk clerk) throws SQLException {
        return 0;
    }

    @Override
    public List<Clerk> findAll() throws SQLException {
        return null;
    }

    @Override
    public int update(Clerk clerk) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
