package ru.javawebinar.basejava.exception;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(Exception e) {
        super("Resume not exist", e);
    }

    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + " not exist", uuid);
    }
}
