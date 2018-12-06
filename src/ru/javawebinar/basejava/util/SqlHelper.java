package ru.javawebinar.basejava.util;

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

    public void doSet(String sql, Write write) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            write.writer(ps);
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public Object doGet(String sql, Read read) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            return read.reader(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }


    public interface Write {
        void writer(PreparedStatement ps) throws SQLException;
    }

    public interface Read<T> {
        T reader(PreparedStatement ps) throws SQLException;
    }
}
