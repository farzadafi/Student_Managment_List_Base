package Repository;

import Entity.Professor;
import Entity.enomeration.ProfessorType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepository implements Repository<Professor> {
    Connection connection = Singleton.getInstance().getConnection();

    public ProfessorRepository() throws SQLException, ClassNotFoundException {
        String createTable = " CREATE TABLE IF NOT EXISTS Professor(id serial," +
                                                               "firstName varchar(50)," +
                                                               "lastName varchar(50), " +
                                                               "nationalId varchar(50), " +
                                                               "username varchar(50), " +
                                                               "password varchar(50), " +
                                                               "kindProfessor varchar(50))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
    }


    @Override
    public int add(Professor professor) throws SQLException {
        String add = " INSERT INTO Professor(firstName,lastName,nationalId,username,password,kindProfessor) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1,professor.getFirstName());
        preparedStatement.setString(2,professor.getLastName());
        preparedStatement.setString(3,professor.getNationalId());
        preparedStatement.setString(4,professor.getUsername());
        preparedStatement.setString(5,professor.getPassword());
        preparedStatement.setString(6, String.valueOf(professor.getProfessorType()));
        try {
            return preparedStatement.executeUpdate();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
        return 0;
    }

    @Override
    public List<Professor> findAll() throws SQLException {
        String find = "SELECT * FROM Professor ";
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        List<Professor> professorList = new ArrayList<>();
        ResultSet resultSet;
        try{
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
            return null;
        }
        if(resultSet.isBeforeFirst()){
            while(resultSet.next()) {
                Professor professor = new Professor();
                professor.setFirstName(resultSet.getString("firstname"));
                professor.setLastName(resultSet.getString("lastName"));
                professor.setNationalId(resultSet.getString("nationalId"));
                professor.setUsername(resultSet.getString("username"));
                professor.setPassword(resultSet.getString("password"));
                professor.setProfessorType(ProfessorType.valueOf(resultSet.getString("kindProfessor")));
                professorList.add(professor);
            }
            return professorList;
        }
        else
            return null;
    }

    @Override
    public int update(Professor professor) throws SQLException {
        String update =  "UPDATE Professor SET firstname = ? , lastName = ? , password = ? WHERE nationalId = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1,professor.getFirstName());
        preparedStatement.setString(2,professor.getLastName());
        preparedStatement.setString(3,professor.getPassword());
        preparedStatement.setString(4,professor.getNationalId());
        try{
            preparedStatement.executeUpdate();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
            return 0;
        }
        return 1;
    }

    @Override
    public int delete(String username) throws SQLException {
        String delete = " DELETE FROM Professor WHERE username = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setString(1,username);
        try {
            return preparedStatement.executeUpdate();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
        return 0;
    }
}
