import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void save(Resume r) {
        if (!Arrays.asList(storage).contains(r)) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Resume already in storage");
        }
    }

    void update(Resume r) {
        if (Arrays.asList(storage).contains(r)) {
            storage[Arrays.asList(storage).indexOf(r)] = r;
        } else {
            System.out.println("Resume not in storage");
        }
    }

    Resume get(String uuid) {
        if (isCheckInStorage(uuid)) {
            for (int i = 0; i < size; ++i) {
                if (uuid.equals(storage[i].uuid)) {
                    return storage[i];
                }
                break;
            }
        } else {
            System.out.println("Resume not in storage");
        }
        return null;
    }

    void delete(String uuid) {
        if (isCheckInStorage(uuid)) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                }
            }
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

    private boolean isCheckInStorage(String uuid) {
        for (Resume resume : storage) {
            if (uuid.equals(resume.uuid)) {
                return true;
            }
            break;
        }
        return false;
    }
}

