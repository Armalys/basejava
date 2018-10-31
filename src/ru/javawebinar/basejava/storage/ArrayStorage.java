package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int findValueOfIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void keep(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void remove(int valueOfIndex, String uuid) {
        storage[valueOfIndex] = storage[size - 1];
    }
}

