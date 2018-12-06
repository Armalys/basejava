package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Object sqlHelping(String sql, SqlHelp sqlHelp) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            return sqlHelp.sqlHelp(ps);
        } catch (SQLException e) {
            throw new ExistStorageException(e);
        } catch (NotExistStorageException e) {
            throw new NotExistStorageException(e);
        }
    }

    public interface SqlHelp {
        Object sqlHelp(PreparedStatement ps) throws SQLException;
    }
}
