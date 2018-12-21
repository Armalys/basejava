package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.<Void>transactionalExecute(connection -> {
                    insertResumes(resume, "INSERT INTO resume (full_name, uuid) VALUES (?,?)", connection);
                    insertContact(resume, connection);
                    insertSection(resume, connection);
                    return null;
                }
        );
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.<Void>transactionalExecute(connection -> {
                    int i = insertResumes(resume, "UPDATE resume SET full_name = ? WHERE uuid =?", connection);
                    if (i == 0) {
                        throw new NotExistStorageException(resume.getUuid());
                    }
                    clearContacts(resume, connection);
                    clearSections(resume, connection);
                    insertContact(resume, connection);
                    insertSection(resume, connection);
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                        "    SELECT * FROM resume r " +
                        " LEFT JOIN contact AS c" +
                        "        ON r.uuid = c.resume_uuid " +
                        " LEFT JOIN section AS s" +
                        "        ON r.uuid = s.resume_uuid" +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        addContact(rs, resume);
                        addSection(rs, resume);
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
        List<Resume> resumeList = new ArrayList<>();
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement psResume = connection.prepareStatement("SELECT * FROM resume ORDER BY full_name,uuid")) {
                ResultSet rsResume = psResume.executeQuery();
                Resume resume;
                while (rsResume.next()) {
                    String uuid = rsResume.getString("uuid");
                    String full_name = rsResume.getString("full_name");
                    resume = new Resume(uuid, full_name);

                    try (PreparedStatement psSection = connection.prepareStatement("SELECT * FROM section WHERE resume_uuid=?")) {
                        psSection.setString(1, uuid);
                        ResultSet rsSection = psSection.executeQuery();
                        while (rsSection.next()) {
                            addSection(rsSection, resume);
                        }
                    }

                    try (PreparedStatement psContacts = connection.prepareStatement("SELECT * FROM contact WHERE resume_uuid=?")) {
                        psContacts.setString(1, uuid);
                        ResultSet rsContacts = psContacts.executeQuery();
                        while (rsContacts.next()) {
                            addContact(rsContacts, resume);
                        }
                    }
                    resumeList.add(resume);
                }
            }
            return null;
        });
        return resumeList;
    }

//        return sqlHelper.execute("SELECT * FROM resume r" +
//                " LEFT JOIN contact c" +
//                " ON r.uuid = c.resume_uuid" +
//                " LEFT JOIN section AS s" +
//                "        ON r.uuid = s.resume_uuid" +
//                " ORDER BY full_name,uuid", ps -> {
//            ResultSet rs = ps.executeQuery();
//            Map<String, Resume> mapResumes = new LinkedHashMap<>();
//            while (rs.next()) {
//                String uuid = rs.getString("uuid");
//                String fullName = rs.getString("full_name");
//                Resume resume = mapResumes.computeIfAbsent(uuid, s -> new Resume(uuid, fullName));
//                addContact(rs, resume);
//                addSection(rs, resume);
//            }
//            return new ArrayList<>(mapResumes.values());
//        });
//    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", st -> {
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private int insertResumes(Resume resume, String sql, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            return ps.executeUpdate();
        }
    }

    private void insertContact(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contact (type, value, resume_uuid) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                ps.setString(1, entry.getKey().name());
                ps.setString(2, entry.getValue());
                ps.setString(3, resume.getUuid());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSection(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO section (type_section, value_section, resume_uuid) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                SectionType sectionType = entry.getKey();
                ps.setString(1, sectionType.name());
                AbstractSection value = entry.getValue();

                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        ps.setString(2, ((TextSection) value).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        StringBuilder stringBuffer = new StringBuilder();
                        List<String> items = ((ListSection) value).getItems();

                        for (String text : items) {
                            stringBuffer.append(text).append("\n");
                        }
                        ps.setString(2, stringBuffer.toString());
                        break;
                }
                ps.setString(3, resume.getUuid());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void clearContacts(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM contact WHERE resume_uuid=?")) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void clearSections(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM section WHERE resume_uuid=?")) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            ContactType type = ContactType.valueOf(rs.getString("type"));
            resume.addContact(type, value);
        }
    }

    private void addSection(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value_section");
        if (value != null) {
            SectionType sectionType = SectionType.valueOf(rs.getString("type_section"));
            switch (sectionType) {
                case OBJECTIVE:
                case PERSONAL:
                    resume.addSection(sectionType, new TextSection(value));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    resume.addSection(sectionType, new ListSection(Arrays.asList(value.split("\n"))));
                    break;
            }
        }
    }
}
