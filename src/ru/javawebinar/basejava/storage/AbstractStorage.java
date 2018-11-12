package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK searchKey = getExistSearchKey(resume.getUuid());
        doSave(searchKey, resume);
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SK searchKey = getNotExistSearchKey(resume.getUuid());
        doUpdate(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getNotExistSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("GetAllSorted");
        List<Resume> resumes = doGetAllSorted();
        Collections.sort(resumes);
        return resumes;
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getNotExistSearchKey(uuid);
        doDelete(searchKey);
    }

    private SK getExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doSave(SK searchKey, Resume resume);

    protected abstract void doUpdate(SK searchKey, Resume resume);

    protected abstract Resume doGet(SK searchKey);

    protected abstract List<Resume> doGetAllSorted();

    protected abstract void doDelete(SK searchKey);
}