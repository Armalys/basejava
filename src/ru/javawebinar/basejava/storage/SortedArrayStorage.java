package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int findValueOfIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void keep(Resume resume, int index) {
        int valueOfIndex = -index - 1;
        System.arraycopy(storage, valueOfIndex, storage, valueOfIndex + 1, size - valueOfIndex);
        storage[valueOfIndex] = resume;
    }

    @Override
    protected void remove(int valueOfIndex) {
        System.arraycopy(storage, valueOfIndex + 1, storage, valueOfIndex, size - valueOfIndex - 1);
    }
}
