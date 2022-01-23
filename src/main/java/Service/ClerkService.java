package Service;

import Entity.Clerk;
import Entity.Student;
import Repository.ClerkRepository;

import java.sql.SQLException;
import java.util.List;

public class ClerkService {
    ClerkRepository clerkRepository = new ClerkRepository();

    public ClerkService() throws SQLException, ClassNotFoundException {
    }

    public void addClerk(Clerk clerk) throws SQLException {
        clerkRepository.add(clerk);
    }

    public int delete(String username) throws SQLException {
        return clerkRepository.delete(username);
    }

    public List<Clerk> findAll() throws SQLException {
        return clerkRepository.findAll();
    }

    public int updateClerk(Clerk clerk) throws SQLException {
        return clerkRepository.update(clerk);
    }
}
