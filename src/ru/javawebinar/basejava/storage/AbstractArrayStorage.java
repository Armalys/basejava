package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected Resume[] storage = new Resume[10_000];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void save(Resume resume) {
        int index = findValueOfIndex(resume.getUuid());
        if (size >= storage.length) {
            throw new StorageException("Storage is full", resume.getUuid());
        } else {
            if (index < 0) {
                keep(resume, index);
                size++;
            } else {
                throw new ExistStorageException(resume.getUuid());
            }
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findValueOfIndex(uuid);
        if (index >= 0) {
            remove(index, uuid);
            storage[size - 1] = null;
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
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
    protected void abstractUpdate(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected Resume abstractGet(int index, String uuid) {
        return storage[index];
    }

    protected abstract int findValueOfIndex(String uuid);
    protected abstract void keep(Resume resume, int index);
    protected abstract void remove(int valueOfIndex, String uuid);
}
