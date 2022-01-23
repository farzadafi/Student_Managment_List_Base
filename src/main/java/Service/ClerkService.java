package Service;

import Entity.Clerk;
import Repository.ClerkRepository;

import java.sql.SQLException;

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
}
