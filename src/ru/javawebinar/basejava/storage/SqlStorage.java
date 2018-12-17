package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    private SqlHelper sqlHelper;

    public SqlStorage() {
    }

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.<Void>transactionalExecute(conn -> {
                    executeResume(resume, "INSERT INTO resume (full_name, uuid) VALUES (?,?)", conn);
                    executeContact(resume, "INSERT INTO contact (type, value, resume_uuid) VALUES (?,?,?)", conn);
                    return null;
                }
        );
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.<Void>transactionalExecute(connection -> {
                    int i = executeResume(resume, "UPDATE resume SET full_name = ? WHERE uuid =?", connection);
                    if (i == 0) {
                        throw new NotExistStorageException(resume.getUuid());
                    }
                    sqlHelper.execute("DELETE FROM contact");
                    executeContact(resume, "INSERT INTO contact (type, value, resume_uuid) VALUES (?,?,?)", connection);
                    return null;
                }
        );
    }


    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                        "    SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid " +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        if (rs.getString("type") != null) {
                            ContactType type = ContactType.valueOf(rs.getString("type"));
                            String value = rs.getString("value");
                            resume.addContact(type, value);
                        }
                    } while (rs.next());
                    return resume;
                });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }


    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume r LEFT JOIN contact c on r.uuid = c.resume_uuid ORDER BY full_name,uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> listResumes = new ArrayList<>();
            Map<Resume, Map<ContactType, String>> mapResumes = new HashMap<>();
            Map<ContactType, String> contacts = new HashMap<>();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                String fullName = rs.getString("full_name");
                ContactType contactType = ContactType.valueOf(rs.getString("type"));
                String value = rs.getString("value");
                contacts.put(contactType, value);
                mapResumes.put(new Resume(uuid, fullName), contacts);
            }

            for (Map.Entry<Resume, Map<ContactType, String>> entry : mapResumes.entrySet()) {
                entry.getKey().setContacts(entry.getValue());
                listResumes.add(entry.getKey());
            }
            listResumes.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
            return listResumes;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", st -> {
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private int executeResume(Resume resume, String sql, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            return ps.executeUpdate();
        }
    }

    private void executeContact(Resume resume, String sql, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                ps.setString(1, entry.getKey().name());
                ps.setString(2, entry.getValue());
                ps.setString(3, resume.getUuid());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
