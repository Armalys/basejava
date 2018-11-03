package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insert(Object index, Resume resume) {
        int value = -(int) index - 1;
        System.arraycopy(storage, value, storage, value + 1, size - value);
        storage[value] = resume;
    }

    @Override
    protected void remove(int value) {
        System.arraycopy(storage, value + 1, storage, value, size - value - 1);
    }
}
