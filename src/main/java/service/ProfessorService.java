package service;

import entity.Professor;
import repository.ProfessorRepository;

import java.sql.SQLException;
import java.util.List;

public class ProfessorService {
    private ProfessorRepository professorRepository = new ProfessorRepository();

    public ProfessorService() {
    }

    public void addProfessor(Professor professor) {
        try {
            professorRepository.add(professor);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public int delete(String username) {
        try {
            return professorRepository.delete(username);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public List<Professor> findAll() {
        try {
            return professorRepository.findAll();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int updateProfessor(Professor professor) {
        try {
            return professorRepository.update(professor);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }




}
