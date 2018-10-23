package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected Resume[] storage = new Resume[10_000];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void save(Resume resume) {
        int index = findValueOfIndex(resume.getUuid());
        if (size >= storage.length) {
            System.out.println("Storage is full");
        } else {
            if (index < 0) {
                keep(resume, index);
            } else {
                System.out.println("Resume already in storage");
            }
        }
    }

    @Override
    public void update(Resume resume) {
        int index = findValueOfIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume not in storage");
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = findValueOfIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("Resume not in storage");
            return null;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findValueOfIndex(uuid);
        if (index >= 0) {
            remove(index);
        } else {
            System.out.println("Resume not in storage");
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public int getSize() {
        return size;
    }

    public abstract int findValueOfIndex(String uuid);

    public abstract void keep(Resume resume, int index);

    public abstract void remove(int index);
}
