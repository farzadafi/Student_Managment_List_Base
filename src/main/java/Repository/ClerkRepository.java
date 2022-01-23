package Repository;

import Entity.Clerk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ClerkRepository implements Repository<Clerk> {
    Connection connection = Singleton.getInstance().getConnection();

    public ClerkRepository() throws SQLException, ClassNotFoundException {
        String createTable = " CREATE TABLE IF NOT EXISTS Clerk(id serial," +
                                                        "firstName varcha(50)," +
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
    public int add(Clerk clerk) throws SQLException {
        String add = " INSERT INTO Clerk (firstName,lastName,nationalId,username,password) VALUES (? ,? ,?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1, clerk.getFirstName());
        preparedStatement.setString(2, clerk.getLastName());
        preparedStatement.setString(3, clerk.getNationalId());
        preparedStatement.setString(4, clerk.getUsername());
        preparedStatement.setString(5, clerk.getPassword());
        try {
            return preparedStatement.executeUpdate();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
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
    public int delete(String username) throws SQLException {
        return 0;
    }
}
