package repository;

import entity.Clerk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClerkRepository implements Repository<Clerk> {
    private Connection connection;

    public ClerkRepository() {
        try {
            connection = Singleton.getInstance().getConnection();
            String createTable = " CREATE TABLE IF NOT EXISTS Clerk(id serial," +
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
    public int add(Clerk clerk) throws SQLException {
        String add = " INSERT INTO Clerk (firstName,lastName,nationalId,username,password) VALUES (? ,? ,?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1, clerk.getFirstName());
        preparedStatement.setString(2, clerk.getLastName());
        preparedStatement.setString(3, clerk.getNationalId());
        preparedStatement.setString(4, clerk.getUsername());
        preparedStatement.setString(5, clerk.getPassword());
            return preparedStatement.executeUpdate();
    }


    @Override
    public List<Clerk> findAll() throws SQLException {
        String find = "SELECT * FROM Clerk ";
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        List<Clerk> clerkList = new ArrayList<>();
        ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();
        if(resultSet.isBeforeFirst()){
            while(resultSet.next()) {
                Clerk clerk = new Clerk();
                clerk.setFirstName(resultSet.getString("firstname"));
                clerk.setLastName(resultSet.getString("lastName"));
                clerk.setNationalId(resultSet.getString("nationalId"));
                clerk.setUsername(resultSet.getString("username"));
                clerk.setPassword(resultSet.getString("password"));
                clerkList.add(clerk);
            }
            return clerkList;
        }
        else
            return null;
    }

    @Override
    public int update(Clerk clerk) throws SQLException {
        String update =  "UPDATE Clerk SET firstname = ? , lastName = ? , password = ? WHERE nationalId = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1,clerk.getFirstName());
        preparedStatement.setString(2,clerk.getLastName());
        preparedStatement.setString(3,clerk.getPassword());
        preparedStatement.setString(4,clerk.getNationalId());
        preparedStatement.executeUpdate();
        return 1;
    }

    @Override
    public int delete(String username) throws SQLException {
        String delete = " DELETE FROM Clerk WHERE username = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setString(1,username);
        return preparedStatement.executeUpdate();
    }
}
