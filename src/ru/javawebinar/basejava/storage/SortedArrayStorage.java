package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    protected void abstractSave(Resume resume) {
        if (size >= storage.length) {
            throw new StorageException("Storage is full", resume.getUuid());
        } else {
            int value = (int) getIndex(resume.getUuid());
            int valueOfIndex = -value - 1;
            System.arraycopy(storage, valueOfIndex, storage, valueOfIndex + 1, size - valueOfIndex);
            storage[valueOfIndex] = resume;
            size++;
        }
    }

    @Override
    protected void abstractDelete(Object valueOfIndex) {
        int value = (int) valueOfIndex;
        System.arraycopy(storage, value + 1, storage, value, size - value - 1);
        storage[size - 1] = null;
        size--;
    }
}
