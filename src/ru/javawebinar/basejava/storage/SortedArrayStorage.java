package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "new name");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected void insert(Resume resume, Integer searchKey) {
        int value = -searchKey - 1;
        System.arraycopy(storage, value, storage, value + 1, size - value);
        storage[value] = resume;
    }

    @Override
    protected void remove(int value) {
        System.arraycopy(storage, value + 1, storage, value, size - value - 1);
    }
}
