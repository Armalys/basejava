package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String DUMMY = "dummy";

    private static final String FULL_NAME_1 = "full name 1";
    private static final String FULL_NAME_2 = "full name 2";
    private static final String FULL_NAME_3 = "full name 3";
    private static final String FULL_NAME_DUMMY = "full name dummy";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_DUMMY;

    protected Storage storage;

    static {
//        RESUME_1 = new Resume(UUID_1, FULL_NAME_1);
//        RESUME_2 = new Resume(UUID_2, FULL_NAME_2);
//        RESUME_3 = new Resume(UUID_3, FULL_NAME_3);
//        RESUME_DUMMY = new Resume(DUMMY, FULL_NAME_DUMMY);
        RESUME_1 = new Resume(UUID_1);
        RESUME_2 = new Resume(UUID_2);
        RESUME_3 = new Resume(UUID_3);
        RESUME_DUMMY = new Resume(DUMMY);
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.getSize());
    }

    @Test
    public void save() {
        storage.save(RESUME_DUMMY);
        Assert.assertEquals(4, storage.getSize());
        Assert.assertEquals(RESUME_DUMMY, storage.get(RESUME_DUMMY.getUuid()));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void update() {
        final Resume resume5 = new Resume(UUID_1);
        storage.update(resume5);
        Assert.assertSame(resume5, storage.get(resume5.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_DUMMY);
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(DUMMY);
    }

    @Test
    public void getAllSorted() {
        final List<Resume> storageExpected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        final List<Resume> storageActual = storage.getAllSorted();
        Assert.assertEquals(storageExpected.size(), storageActual.size());
        Assert.assertEquals(storageExpected, storageActual);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.getSize());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(DUMMY);
    }

    @Test
    public void getSize() {
        Assert.assertEquals(3, storage.getSize());
    }
}