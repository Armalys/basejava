package ru.javawebinar.basejava.storage;

public class SqlStorageTest extends AbstractArrayStorageTest {

    public SqlStorageTest() {
//        super(new SqlStorage(Config.get().getUrl(), Config.get().getUser(), Config.get().getPassword()));
        super(new SqlStorage("jdbc:postgresql://localhost:5432/resumes","postgres","2121"));
    }
}