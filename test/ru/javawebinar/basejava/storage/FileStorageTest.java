package ru.javawebinar.basejava.storage;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(storageDir, new ObjectSerialization()));
    }
}