package service;

import entity.Clerk;
import repository.ClerkRepository;

import java.sql.SQLException;
import java.util.List;

public class ClerkService {
    ClerkRepository clerkRepository = new ClerkRepository();

    public ClerkService() throws SQLException, ClassNotFoundException {
    }

    public void addClerk(Clerk clerk){
        try {
            clerkRepository.add(clerk);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public int delete(String username){
        try {
            return clerkRepository.delete(username);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public List<Clerk> findAll() {
        try {
            return clerkRepository.findAll();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int updateClerk(Clerk clerk) {
        try {
            return clerkRepository.update(clerk);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
