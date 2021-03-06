package repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    int add(T t ) throws SQLException;

    List<T> findAll() throws SQLException;

    int update(T t) throws SQLException;

    int delete(String input) throws SQLException;

}
