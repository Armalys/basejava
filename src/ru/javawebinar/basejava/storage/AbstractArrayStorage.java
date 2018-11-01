package ru.javawebinar.basejava.storage;

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
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    protected Boolean checkIndex(Object index) {
        int value = (int) index;
        if (value >= 0) return true;
        else return false;
    }

    @Override
    protected Resume abstractGet(Object index) {
        return storage[(int) index];
    }

    @Override
    protected void abstractUpdate(Object index, Resume resume) {
        storage[(int) index] = resume;
    }
}
