package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapHashCodeUuidStorage extends AbstractStorage {
    private Map<Integer, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList<>(storage.values());
        resumes.sort(RESUME_COMPARATOR);
        return resumes;
    }

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    protected Integer getIndex(String uuid) {
        return uuid.hashCode();
    }

    @Override
    protected boolean checkIndex(Object index) {
        Integer value = (Integer) index;
        return storage.containsKey(value);
    }

    protected void abstractSave(Object index, Resume resume) {
        storage.put((Integer) index, resume);
    }

    @Override
    protected void abstractUpdate(Object index, Resume resume) {
        Integer value = (Integer) index;
        storage.replace(value, resume);
    }

    @Override
    protected Resume abstractGet(Object index) {
        Integer value = (Integer) index;
        return storage.get(value);
    }

    protected void abstractDelete(Object index) {
        Integer value = (Integer) index;
        storage.remove(value);
    }
}
