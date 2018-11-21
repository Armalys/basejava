package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.StrategySerialization.ObjectSerialization;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(storageDir.getPath(), new ObjectSerialization()));
    }
}