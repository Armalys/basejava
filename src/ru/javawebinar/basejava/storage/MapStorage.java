package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<Object, Resume> storage = new HashMap<>();

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
    protected Object getIndex(String uuid) {
        Resume resume = storage.get(uuid);
        if (resume != null) {
            return uuid;
        } else {
            return null;
        }
    }

    @Override
    protected Boolean checkIndex(Object index) {
        if (index != null) return true;
        else return false;
    }

    protected void abstractSave(Object index, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void abstractUpdate(Object index, Resume resume) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected Resume abstractGet(Object index) {
        return storage.get(index);
    }

    protected void abstractDelete(Object valueOfIndex) {
        storage.remove(valueOfIndex);
    }
}
