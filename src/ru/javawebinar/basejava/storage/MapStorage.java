package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    protected String getIndex(String uuid) {
        return uuid;
    }

    @Override
    protected boolean checkIndex(Object index) {
        String value = (String) index;
        Resume resume = storage.get(value);
        return resume != null;
    }

    protected void abstractSave(Object index, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void abstractUpdate(Object index, Resume resume) {
        String value = (String) index;
        storage.replace(value, resume);
    }

    @Override
    protected Resume abstractGet(Object index) {
        String value = (String) index;
        return storage.get(value);
    }

    protected void abstractDelete(Object index) {
        String value = (String) index;
        storage.remove(value);
    }
}
