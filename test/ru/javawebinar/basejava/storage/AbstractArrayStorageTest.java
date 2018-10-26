package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private Resume resume = new Resume("uuid1");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        for (int i = 0; i < 100; i++) {
            storage.save(new Resume());
        }
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.getSize());
    }

    @Test
    public void save() {
        storage.save(resume);
        storage.get(resume.getUuid());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume);
        storage.save(resume);
    }

    @Test
    public void update() {
        storage.save(resume);
        storage.update(resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume);
        storage.update(new Resume("dummy"));
    }

    @Test
    public void get() {
        storage.save(resume);
        storage.get(resume.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void delete() {
        storage.save(resume);
        storage.delete(resume.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        storage.getAll();
    }

    @Test
    public void getSize() {
        Assert.assertEquals(100, storage.getSize());
    }

    @Test(expected = StorageException.class)
    public void storageException() {
        try {
            for (int i = 0; i < 9_900; i++) {
                storage.save(new Resume());
            }
            Assert.fail("Storage is not full");
        } finally {
            storage.save(resume);
        }
    }

}