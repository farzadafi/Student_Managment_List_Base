package repository;

import entity.OfferLesson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferLessonRepository implements Repository<OfferLesson> {
    private Connection connection;

    public OfferLessonRepository() {
        try {
            connection = Singleton.getInstance().getConnection();
            String createTable = "CREATE TABLE IF NOT EXISTS OfferLesson(id serial,lessonName varchar(50) UNIQUE,unitNumber integer)";
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public int add(OfferLesson offerLesson) throws SQLException {
        String add = " INSERT INTO OfferLesson(lessonName,unitNumber) VALUES (? ,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1,offerLesson.getLessonName());
        preparedStatement.setInt(2,offerLesson.getUnitNumber());
        return preparedStatement.executeUpdate();
    }

    @Override
    public List<OfferLesson> findAll() throws SQLException {
        String find = "SELECT * FROM OfferLesson";
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        List<OfferLesson> offerLessonList = new ArrayList<>();
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        if(resultSet.isBeforeFirst()){
            while(resultSet.next()) {
                OfferLesson offerLesson = new OfferLesson();
                offerLesson.setId(resultSet.getInt("id"));
                offerLesson.setLessonName(resultSet.getString("lessonName"));
                offerLesson.setUnitNumber(resultSet.getInt("unitNumber"));
                offerLessonList.add(offerLesson);
            }
            return offerLessonList;
        }
        else
            return null;
    }

    @Override
    public int update(OfferLesson offerLesson) throws SQLException {
        return 0;
    }

    @Override
    public int delete(String username) throws SQLException {
        return 0;
    }
}
