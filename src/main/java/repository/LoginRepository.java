package repository;

import entity.Login;
import entity.enomeration.TypeUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginRepository implements Repository<Login> {
    private Connection connection;

    public LoginRepository() {
        try {
            connection = Singleton.getInstance().getConnection();
        String createTable = "CREATE TABLE IF NOT EXISTS Login(username varchar(50) UNIQUE,password varchar(50),kind varchar(10))";
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public int add(Login login) throws SQLException {
        String add = " INSERT INTO Login(username,password,kind) VALUES (? ,? ,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1,login.getUsername());
        preparedStatement.setString(2,login.getPassword());
        preparedStatement.setString(3,String.valueOf(login.getTypeUser()));
        return preparedStatement.executeUpdate();
    }

    @Override
    public List<Login> findAll() throws SQLException {
        String find = "SELECT * FROM Login";
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        List<Login> loginList = new ArrayList<>();
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        if(resultSet.isBeforeFirst()){
            while(resultSet.next()) {
                Login login = new Login();
                login.setUsername(resultSet.getString("username"));
                login.setPassword(resultSet.getString("password"));
                login.setTypeUser(TypeUser.valueOf(resultSet.getString("kind")));
                loginList.add(login);
            }
            return loginList;
        }
        else
            return null;
    }

    @Override
    public int update(Login login) throws SQLException {
        return 0;
    }

    @Override
    public int delete(String username) throws SQLException {
        String delete = " DELETE FROM Login WHERE username = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setString(1,username);
        return preparedStatement.executeUpdate();
    }

}
