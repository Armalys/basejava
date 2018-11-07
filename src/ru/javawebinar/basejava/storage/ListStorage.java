package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int getSize() {
        return storage.size();
    }


    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean checkSearchKey(Integer searchKey) {
        return searchKey >= 0;
    }

    protected void abstractSave(Integer searchKey, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void abstractUpdate(Integer searchKey, Resume resume) {
        storage.set(searchKey, resume);
    }

    @Override
    protected Resume abstractGet(Integer searchKey) {
        return storage.get(searchKey);
    }

    @Override
    public List<Resume> abstractGetAllSorted() {
        return new ArrayList<>(storage);
    }

    protected void abstractDelete(Integer searchKey) {
        storage.remove(searchKey.intValue());
    }
}
