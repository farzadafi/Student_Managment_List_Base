package Repository;

import Entity.Professor;

import java.sql.SQLException;
import java.util.List;

public class ProfessorRepository implements Repository<Professor> {
    @Override
    public int add(Professor professor) throws SQLException {
        return 0;
    }

    @Override
    public List<Professor> findAll() throws SQLException {
        return null;
    }

    @Override
    public int update(Professor professor) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
