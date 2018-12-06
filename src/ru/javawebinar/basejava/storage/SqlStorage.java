package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;
import ru.javawebinar.basejava.util.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    private SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        ConnectionFactory connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        sqlHelper = new SqlHelper(connectionFactory);
    }

    @Override
    public void clear() {
        sqlHelper.sqlHelping("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.sqlHelping("INSERT INTO resume (uuid, full_name) VALUES (?,?)", new SqlHelper.SqlHelp<Boolean>() {
            @Override
            public Boolean sqlHelp(PreparedStatement ps) throws SQLException {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                return ps.execute();
            }
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.sqlHelping("UPDATE resume SET uuid=?, full_name=? WHERE uuid=?", ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.setString(3, resume.getUuid());
            int i = ps.executeUpdate();
            if (i == 0) {
                throw new NotExistStorageException(resume.getUuid());
            } else return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.sqlHelping("SELECT  * FROM resume r WHERE r.uuid=?", ps -> {
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
        sqlHelper.sqlHelping("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            } else return null;
        });
    }


    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumeList = new ArrayList<>();
        sqlHelper.sqlHelping("SELECT * FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) resumeList.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            return null;
        });
        return resumeList;
    }

    @Override
    public int size() {
        return sqlHelper.sqlHelping("SELECT count(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new StorageException("resume is empty");
            }
            return rs.getInt(1);
        });
    }
}
