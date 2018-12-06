package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Config;

public class SqlStorageTest extends AbstractArrayStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getUrl(), Config.get().getUser(), Config.get().getPassword()));
    }
}