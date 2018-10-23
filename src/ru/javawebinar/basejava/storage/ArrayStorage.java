package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        int index = findValueOfIndex(resume.getUuid());
        if (size >= storage.length) {
            System.out.println("Storage is full");
        } else {
            if (index < 0) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Resume already in storage");
            }
        }
    }

    public void delete(String uuid) {
        int index = findValueOfIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume not in storage");
        }
    }

    private int findValueOfIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}

