package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    static final private String dummy = "dummy";
    private Storage storage;
    static final private Resume resume1 = new Resume("uuid1");
    private static final Resume resume2 = new Resume("uuid2");
    static final private Resume resume3 = new Resume("uuid3");
    static final private Resume resume4 = new Resume(dummy);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.getSize());
    }

    @Test
    public void save() {
        storage.save(resume4);
        storage.get(dummy);
        Assert.assertEquals(4, storage.getSize());
        Assert.assertEquals(resume4, storage.get(dummy));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume1);
    }

    @Test(expected = StorageException.class)
    public void storageOverflow() {
        try {
            for (int i = 0; i < 9_997; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Storage is not full");
        }
        storage.save(resume4);
    }

    @Test
    public void update() {
        Resume resume5 = new Resume("uuid1");
        storage.update(resume5);
        Assert.assertSame(resume5, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume4);
    }

    @Test
    public void get() {
        storage.get(resume1.getUuid());
        Assert.assertEquals(resume1, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(dummy);
    }

    @Test
    public void getAll() {
        final Resume[] storageExpected = new Resume[]{resume1, resume2, resume3};
        Assert.assertEquals(3, storage.getAll().length);
        Assert.assertArrayEquals(storageExpected, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(resume1.getUuid());
        Assert.assertEquals(2, storage.getSize());
        storage.get(resume1.getUuid());

    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(dummy);
    }

    @Test
    public void getSize() {
        Assert.assertEquals(3, storage.getSize());
    }
}