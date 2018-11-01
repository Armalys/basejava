package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void abstractSave(Object index, Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected void abstractDelete(Object valueOfIndex) {
        storage[(int) valueOfIndex] = storage[size - 1];
    }
}

