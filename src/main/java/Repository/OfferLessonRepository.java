package Repository;

import Entity.OfferLesson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OfferLessonRepository implements Repository<OfferLesson> {
    Connection connection = Singleton.getInstance().getConnection();

    public OfferLessonRepository() throws SQLException, ClassNotFoundException {
        String createTable = "CREATE TABLE IF NOT EXISTS OfferLesson(id serial,lessonName varchar(50),unitNumebr integer)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
    }


    @Override
    public int add(OfferLesson offerLesson) throws SQLException {
        return 0;
    }

    @Override
    public List<OfferLesson> findAll() throws SQLException {
        return null;
    }

    @Override
    public int update(OfferLesson offerLesson) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
