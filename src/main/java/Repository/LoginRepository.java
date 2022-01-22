package Repository;

import Entity.Login;

import java.sql.SQLException;
import java.util.List;

public class LoginRepository implements Repository<Login> {
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
