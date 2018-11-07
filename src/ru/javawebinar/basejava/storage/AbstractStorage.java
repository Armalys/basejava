package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {
    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    @Override
    public void save(Resume resume) {
        SK searchKey = getSearchKey(resume.getUuid());
        if (!checkSearchKey(searchKey)) {
            abstractSave(searchKey, resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void update(Resume resume) {
        SK searchKey = getSearchKey(resume.getUuid());
        if (checkSearchKey(searchKey)) {
            abstractUpdate(searchKey, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (checkSearchKey(searchKey)) {
            return abstractGet(searchKey);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = abstractGetAllSorted();
        resumes.sort(RESUME_COMPARATOR);
        return resumes;
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (checkSearchKey(searchKey)) {
            abstractDelete(searchKey);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean checkSearchKey(SK searchKey);

    protected abstract void abstractSave(SK searchKey, Resume resume);

    protected abstract void abstractUpdate(SK searchKey, Resume resume);

    protected abstract Resume abstractGet(SK searchKey);

    protected abstract List<Resume> abstractGetAllSorted();

    protected abstract void abstractDelete(SK searchKey);
}