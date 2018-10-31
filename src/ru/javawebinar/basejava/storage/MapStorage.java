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
    protected int findValueOfIndex(String uuid) {
        Resume resume = storage.get(uuid);
        if (resume != null) {
            return uuid.hashCode();
        } else {
            return -1;
        }
    }

    @Override
    protected void keep(Resume resume, int index) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void abstractUpdate(int index, Resume resume) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected Resume abstractGet(int index, String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void remove(int valueOfIndex, String uuid) {
        storage.remove(uuid);
    }
}
