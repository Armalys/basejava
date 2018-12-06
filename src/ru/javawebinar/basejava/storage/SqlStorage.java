package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;
import ru.javawebinar.basejava.util.SqlHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        sqlHelper = new SqlHelper(connectionFactory);
    }

    @Override
    public void clear() {
        sqlHelper.doSet("DELETE FROM resume", preparedStatement -> {});

    }

    @Override
    public void save(Resume resume) {
        sqlHelper.doSet("INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.doSet("UPDATE resume SET uuid=?,full_name=? where uuid=?", ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.setString(3, resume.getUuid());
        });
    }

    @Override
    public Resume get(String uuid) {
        return (Resume) sqlHelper.doGet("SELECT  * FROM resume r WHERE r.uuid=?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });

    }

    @Override
    public void delete(String uuid) {
        sqlHelper.doSet("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumeList = new ArrayList<>();
        sqlHelper.doGet("SELECT * FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resumeList.add(new Resume(rs.getString(1), rs.getString(1)));
            }
            return null;
        });
        return resumeList;
    }

    @Override
    public int size() {
        return (int) sqlHelper.doGet("SELECT count(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return 0;
            }
            return rs.getInt(1);
        });
    }
}
