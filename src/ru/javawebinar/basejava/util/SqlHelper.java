package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T sqlHelping(String sql, SqlHelp<T> sqlHelp) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            return sqlHelp.sqlHelp(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) throw new ExistStorageException(e);
            else throw new StorageException(e);
        } catch (NotExistStorageException e) {
            throw new NotExistStorageException(e);
        }
    }

    public interface SqlHelp<T> {
        T sqlHelp(PreparedStatement ps) throws SQLException;
    }
}
