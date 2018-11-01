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
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int getSize() {
        return storage.size();
    }


    protected Object getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Boolean checkIndex(Object index) {
        int value = (int) index;
        if (value >= 0) return true;
        else return false;
    }

    protected void abstractSave(Resume resume) {
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

    protected void abstractDelete(Object valueOfIndex) {
        int index = (int) valueOfIndex;
        storage.remove(index);
    }
}
