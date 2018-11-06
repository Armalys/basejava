package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
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
    protected Resume getIndex(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean checkIndex(Object index) {
        if (index == null) return false;
        Resume value = (Resume) index;
        return storage.containsKey(value.getUuid());
    }

    protected void abstractSave(Object index, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void abstractUpdate(Object index, Resume resume) {
        Resume value = (Resume) index;
        storage.replace(value.getUuid(), resume);
    }

    @Override
    protected Resume abstractGet(Object index) {
        Resume value = (Resume) index;
        return storage.get(value.getUuid());
    }

    @Override
    public List<Resume> abstractGetAllSorted() {
        return new ArrayList<>(storage.values());
    }

    protected void abstractDelete(Object index) {
        Resume value = (Resume) index;
        storage.remove(value.getUuid());
    }
}