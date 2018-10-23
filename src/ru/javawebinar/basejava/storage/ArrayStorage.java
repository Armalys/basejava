package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public int findValueOfIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void keep(Resume resume, int index) {
        storage[size] = resume;
        size++;
    }

    @Override
    public void remove(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }
}

