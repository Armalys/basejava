package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;


    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void abstractSave(Object index, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is full", resume.getUuid());
        } else {
            keep(index, resume);
            size++;
        }
    }

    @Override
    protected void abstractDelete(Object index) {
        int value = (int) index;
        remove(value);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    protected boolean checkIndex(Object index) {
        int value = (int) index;
        return value >= 0;
    }

    @Override
    protected Resume abstractGet(Object index) {
        return storage[(int) index];
    }

    @Override
    protected void abstractUpdate(Object index, Resume resume) {
        storage[(int) index] = resume;
    }

    protected abstract void keep(Object index, Resume resume);

    protected abstract void remove(int value);
}
