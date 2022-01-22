package Repository;

import Entity.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LoginRepository implements Repository<Login> {
    Connection connection = Singleton.getInstance().getConnection();

    public LoginRepository() throws SQLException, ClassNotFoundException {
        String createTable = "CREATE TABLE IF NOT EXISTS Login(username varchar(50) UNIQU ,password varchar(50),kind varchar(10))";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }


    @Override
    public int add(Login login) throws SQLException {
        return 0;
    }

    @Override
    public List<Login> findAll() throws SQLException {
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
