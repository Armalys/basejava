package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.StrategySerialization.ObjectSerialization;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(storageDir, new ObjectSerialization()));
    }
}