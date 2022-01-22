package Repository;

import Entity.Lesson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LessonRepository implements Repository<Lesson> {
    Connection connection = Singleton.getInstance().getConnection();

    public LessonRepository() throws SQLException, ClassNotFoundException {
        String createTable = " CREATE TABLE IF NOT EXISTS Lesson(id serial" +
                                                                "idStudent integer," +
                                                                "lessonName varchar(50) REFERENCES OfferLesson(lessonName), " +
                                                                "nationalId varchar(50) REFERENCES Student(nationalId), " +
                                                                "quarterNumber integer, " +
                                                                "unitNumber integer REFERENCES OfferLesson(unitNumber), " +
                                                                "yearl integer, " +
                                                                "lastProfessorName varchar(50) REFERENCES Professor(lastName), " +
                                                                "grade integer)";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }


    @Override
    public int add(Lesson lesson) throws SQLException {
        return 0;
    }

    @Override
    public List<Lesson> findAll() throws SQLException {
        return null;
    }

    @Override
    public int update(Lesson lesson) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
