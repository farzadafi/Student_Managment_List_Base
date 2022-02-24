package service;
import entity.Login;
import repository.LoginRepository;
import java.sql.SQLException;
import java.util.List;

public class LoginService {
    private LoginRepository loginRepository = new LoginRepository();

    public LoginService() {
    }

    public List<Login> findAll() {
        try {
            return loginRepository.findAll();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void addLogin(Login login) {
        try {
            loginRepository.add(login);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public int delete(String username) {
        try {
            return loginRepository.delete(username);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
