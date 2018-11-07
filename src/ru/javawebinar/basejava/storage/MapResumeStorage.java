package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean checkSearchKey(Resume searchKey) {
        return searchKey != null;

    }

    protected void abstractSave(Resume searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void abstractUpdate(Resume searchKey, Resume resume) {
        storage.replace(searchKey.getUuid(), resume);
    }

    @Override
    protected Resume abstractGet(Resume searchKey) {
        return storage.get(searchKey.getUuid());
    }

    @Override
    public List<Resume> abstractGetAllSorted() {
        return new ArrayList<>(storage.values());
    }

    protected void abstractDelete(Resume searchKey) {
        storage.remove(searchKey.getUuid());
    }
}