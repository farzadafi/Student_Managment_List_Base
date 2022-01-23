package Repository;

import Entity.Login;
import Entity.OfferLesson;
import Entity.TypeUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferLessonRepository implements Repository<OfferLesson> {
    Connection connection = Singleton.getInstance().getConnection();

    public OfferLessonRepository() throws SQLException, ClassNotFoundException {
        String createTable = "CREATE TABLE IF NOT EXISTS OfferLesson(id serial,lessonName varchar(50),unitNumber integer)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
    }


    @Override
    public int add(OfferLesson offerLesson) throws SQLException {
        String add = " INSERT INTO OfferLesson(lessonName,unitNumber) VALUES (? ,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1,offerLesson.getLessonName());
        preparedStatement.setInt(2,offerLesson.getUnitNumber());
        try {
            return preparedStatement.executeUpdate();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
        return 0;
    }

    @Override
    public List<OfferLesson> findAll() throws SQLException {
        String find = "SELECT * FROM OfferLesson";
        PreparedStatement preparedStatement = connection.prepareStatement(find);
        List<OfferLesson> offerLessonList = new ArrayList<>();
        ResultSet resultSet;
        try{
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
            return null;
        }
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
