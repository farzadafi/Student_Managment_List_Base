package Repository;

import Entity.Lesson;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonRepository implements Repository<Lesson> {
    Connection connection = Singleton.getInstance().getConnection();

    public LessonRepository() throws SQLException, ClassNotFoundException {
        String createTable = " CREATE TABLE IF NOT EXISTS Lesson(id serial," +
                                                                "idStudent integer," +
                                                                "lessonName varchar(50) REFERENCES OfferLesson(lessonName), " +
                                                                "quarterNumber integer, " +
                                                                "unitNumber integer, " +
                                                                "yearl integer, " +
                                                                "lastProfessorName varchar(50) , " +
                                                                "grade integer)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
            System.out.println("farzad");
        }


    }


    @Override
    public int add(Lesson lesson) throws SQLException {
        String add = " INSERT INTO Lesson (idStudent,lessonName,quarterNumber,unitNumber,yearl,lastProfessorName,grade) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setInt(1,lesson.getIdStudent());
        preparedStatement.setString(2,lesson.getLessonName());
        preparedStatement.setInt(3,lesson.getQuarterNumber());
        preparedStatement.setInt(4,lesson.getUnitNumber());
        preparedStatement.setInt(5,lesson.getYear());
        preparedStatement.setString(6,lesson.getLastProfessorName());
        preparedStatement.setInt(7,lesson.getGrade());
        try {
            return preparedStatement.executeUpdate();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
        return 0;
    }

    @Override
    public List<Lesson> findAll() throws SQLException {
        String find = " SELECT * FROM Lesson ";
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        ResultSet resultSet;
        List<Lesson> lessonList = new ArrayList<>();
        try{
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
            return null;
        }
        if(resultSet.isBeforeFirst()){
            while(resultSet.next()){
                Lesson lesson = new Lesson();
                lesson.setIdStudent(resultSet.getInt("idStudent"));
                lesson.setLessonName(resultSet.getString("lessonName"));
                lesson.setQuarterNumber(resultSet.getInt("quarterNumber"));
                lesson.setUnitNumber(resultSet.getInt("unitNumber"));
                lesson.setYear(resultSet.getInt("yearl"));
                lesson.setLastProfessorName(resultSet.getString("lastProfessorName"));
                lesson.setGrade(resultSet.getInt("grade"));
                lessonList.add(lesson);
            }
        }
        return lessonList;
    }

    @Override
    public int update(Lesson lesson) throws SQLException {
        String setGrade = "UPDATE Lesson SET grade = ? WHERE idStudent = ? AND lessonName = ? AND lastProfessorName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(setGrade);
        preparedStatement.setInt(1,lesson.getGrade());
        preparedStatement.setInt(2,lesson.getIdStudent());
        preparedStatement.setString(3,lesson.getLessonName());
        preparedStatement.setString(4,lesson.getLastProfessorName());
        try{
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            return 0;
        }
        return 1;
    }

    @Override
    public int delete(String username) throws SQLException {
        return 0;
    }
}
