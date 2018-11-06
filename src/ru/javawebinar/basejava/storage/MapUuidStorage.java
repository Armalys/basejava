package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

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
    protected String getIndex(String uuid) {
        return uuid;
    }

    @Override
    protected boolean checkIndex(Object index) {
        return storage.containsKey(index);
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
