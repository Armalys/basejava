package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {
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
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean checkSearchKey(String searchKey) {
        return storage.containsKey(searchKey);
    }

    protected void abstractSave(String searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void abstractUpdate(String searchKey, Resume resume) {
        storage.replace(searchKey, resume);
    }

    @Override
    protected Resume abstractGet(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    public List<Resume> abstractGetAllSorted() {
        return new ArrayList<>(storage.values());
    }

    protected void abstractDelete(String searchKey) {
        storage.remove(searchKey);
    }
}
