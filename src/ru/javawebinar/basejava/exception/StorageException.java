package ru.javawebinar.basejava.exception;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String message) {
        this(message, null, null);
    }

    public StorageException(Exception e) {
        this(e.getMessage(),e);
    }

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String mesage, Exception e) {
        this(mesage, null, e);
    }

    public StorageException(String message, String uuid, Exception e) {
        super(message, e);
        this.uuid = uuid;

    }

    public String getUidd() {
        return uuid;
    }
}
