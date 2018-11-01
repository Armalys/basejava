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
        Object index = getIndex(resume.getUuid());
        if (size >= storage.length) {
            throw new StorageException("Storage is full", resume.getUuid());
        } else {
            if (!checkIndex(index)) {
                abstractSave(index, resume);
                size++;
            } else {
                throw new ExistStorageException(resume.getUuid());
            }
        }
    }

    @Override
    public void delete(String uuid) {
        Object index = getIndex(uuid);
        if (checkIndex(index)) {
            abstractDelete(index);
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
