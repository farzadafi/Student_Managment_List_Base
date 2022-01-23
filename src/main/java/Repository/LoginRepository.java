package Repository;

import Entity.Login;
import Entity.TypeUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginRepository implements Repository<Login> {
    Connection connection = Singleton.getInstance().getConnection();

    public LoginRepository() throws SQLException, ClassNotFoundException {
        String createTable = "CREATE TABLE IF NOT EXISTS Login(username varchar(50) UNIQUE,password varchar(50),kind varchar(10))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
    }


    @Override
    public int add(Login login) throws SQLException {
        String add = " INSERT INTO Login(username,password,kind)";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1,login.getUsername());
        preparedStatement.setString(2,login.getPassword());
        preparedStatement.setString(3, String.valueOf(login.getTypeUser()));
        try {
            return preparedStatement.executeUpdate();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
        return 0;
    }

    @Override
    public List<Login> findAll() throws SQLException {
        String find = "SELECT * FROM Login";
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        List<Login> loginList = new ArrayList<>();
        ResultSet resultSet;
        try{
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
            return null;
        }
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
    public int delete(int id) throws SQLException {
        return 0;
    }




}
