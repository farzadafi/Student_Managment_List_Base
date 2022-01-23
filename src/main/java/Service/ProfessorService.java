package Service;

import Entity.Login;
import Entity.Professor;
import Entity.Student;
import Repository.ProfessorRepository;

import java.sql.SQLException;

public class ProfessorService {
    private ProfessorRepository professorRepository = new ProfessorRepository();

    public ProfessorService() throws SQLException, ClassNotFoundException {
    }

    public void addProfessor(Professor professor) throws SQLException {
        professorRepository.add(professor);
    }

    public int delete(String username) throws SQLException {
        return professorRepository.delete(username);
    }
}
