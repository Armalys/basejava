package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        int index = findValueOfIndex(resume.getUuid());
        if (size >= storage.length) {
            System.out.println("Storage is full");
        } else {
            if (index < 0) {
                index = -index - 1;
                System.arraycopy(storage, index, storage, index + 1, size - index);
                storage[index] = resume;
                size++;
            } else {
                System.out.println("Resume already in storage");
            }
        }
    }

    public void delete(String uuid) {
        int index = findValueOfIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            size--;
        } else {
            System.out.println("Resume not in storage");
        }
    }

    public int findValueOfIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

}
