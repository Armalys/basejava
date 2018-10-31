package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void save(Resume resume) {
        int index = findValueOfIndex(resume.getUuid());
        if (index < 0) {
            keep(resume, index);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void update(Resume resume) {
        int index = findValueOfIndex(resume.getUuid());
        if (index >= 0) {
            abstractUpdate(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = findValueOfIndex(uuid);
        if (index >= 0) {
            return abstractGet(index, uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findValueOfIndex(uuid);
        if (index >= 0) {
            remove(index, uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract Resume abstractGet(int index, String uuid);

    protected abstract int findValueOfIndex(String uuid);

    protected abstract void keep(Resume resume, int index);

    protected abstract void abstractUpdate(int index, Resume resume);

    protected abstract void remove(int valueOfIndex, String uuid);
}