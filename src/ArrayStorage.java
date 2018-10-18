import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("Storage is full");
        } else {
            if (findValueOfIndex(resume.getUuid()) < 0) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Resume already in storage");
            }
        }
    }

    void update(Resume resume) {
        int index = findValueOfIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume not in storage");
        }
    }

    Resume get(String uuid) {
        int index = findValueOfIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("Resume not in storage");
            return null;
        }
    }

    void delete(String uuid) {
        int index = findValueOfIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume not in storage");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    int size() {
        return size;
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

