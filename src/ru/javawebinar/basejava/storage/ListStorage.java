package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int getSize() {
        return storage.size();
    }


    protected Integer getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean checkIndex(Object index) {
        int value = (int) index;
        return value >= 0;
    }

    protected void abstractSave(Object index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void abstractUpdate(Object index, Resume resume) {
        int value = (int) index;
        storage.set(value, resume);
    }

    @Override
    protected Resume abstractGet(Object index) {
        int value = (int) index;
        return storage.get(value);
    }

    @Override
    public List<Resume> abstractGetAllSorted() {
        return new ArrayList<>(storage);
    }

    protected void abstractDelete(Object index) {
        int value = (int) index;
        storage.remove(value);
    }
}
