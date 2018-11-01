package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    protected void abstractSave(Object index, Resume resume) {
        int valueOfIndex = -(int) index - 1;
        System.arraycopy(storage, valueOfIndex, storage, valueOfIndex + 1, size - valueOfIndex);
        storage[valueOfIndex] = resume;
    }

    @Override
    protected void abstractDelete(Object valueOfIndex) {
        System.arraycopy(storage, (int) valueOfIndex + 1, storage, (int) valueOfIndex, size - (int) valueOfIndex - 1);
    }
}
