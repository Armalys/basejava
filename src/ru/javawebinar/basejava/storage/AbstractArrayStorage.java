package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void abstractSave(Integer searchKey, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage is full", resume.getUuid());
        } else {
            insert(searchKey, resume);
            size++;
        }
    }

    @Override
    protected void abstractDelete(Integer searchKey) {
        remove(searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public List<Resume> abstractGetAllSorted() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    protected boolean checkSearchKey(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected Resume abstractGet(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    protected void abstractUpdate(Integer searchKey, Resume resume) {
        storage[searchKey] = resume;
    }

    protected abstract void insert(Integer searchKey, Resume resume);

    protected abstract void remove(int value);
}
