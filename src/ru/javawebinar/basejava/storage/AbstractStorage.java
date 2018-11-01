package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        Object index = getIndex(resume.getUuid());
        if (!checkIndex(index)) {
            abstractSave(index, resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void update(Resume resume) {
        Object index = getIndex(resume.getUuid());
        if (checkIndex(index)) {
            abstractUpdate(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        Object index = getIndex(uuid);
        if (checkIndex(index)) {
            return abstractGet(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        Object index = getIndex(uuid);
        if (checkIndex(index)) {
            abstractDelete(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract Object getIndex(String uuid);

    protected abstract Boolean checkIndex(Object index);

    protected abstract void abstractSave(Object index, Resume resume);

    protected abstract void abstractUpdate(Object index, Resume resume);

    protected abstract Resume abstractGet(Object index);

    protected abstract void abstractDelete(Object valueOfIndex);
}