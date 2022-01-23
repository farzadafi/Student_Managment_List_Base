package Service;
import Entity.Login;
import Repository.LoginRepository;
import java.sql.SQLException;
import java.util.List;

public class LoginService {
    private LoginRepository loginRepository = new LoginRepository();

    public LoginService() throws SQLException, ClassNotFoundException {
    }

    public List<Login> findAll() throws SQLException {
        return loginRepository.findAll();
    }

    public void addLogin(Login login) throws SQLException {
        loginRepository.add(login);
    }

    public int delete(String username) throws SQLException {
        return loginRepository.delete(username);
    }
}
