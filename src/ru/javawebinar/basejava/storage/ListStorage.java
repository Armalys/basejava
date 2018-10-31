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


    protected int findValueOfIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void keep(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    protected void abstractUpdate(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected Resume abstractGet(int index, String uuid) {
        return storage.get(index);
    }

    @Override
    protected void remove(int valueOfIndex, String uuid) {
        storage.remove(valueOfIndex);
    }
}
