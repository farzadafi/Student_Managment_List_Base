package Repository;

import Entity.Clerk;

import java.sql.SQLException;
import java.util.List;

public class ClerkRepository implements Repository<Clerk> {
    @Override
    public int add(Clerk clerk) throws SQLException {
        return 0;
    }

    @Override
    public List<Clerk> findAll() throws SQLException {
        return null;
    }

    @Override
    public int update(Clerk clerk) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
