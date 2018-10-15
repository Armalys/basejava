/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
            size = 0;
        }
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if(uuid.equals(resume.toString())) {
                return resume;
            } break;
        } return null;
    }

    void delete(String uuid) {
        for(int i = 0; i < size; i++) {
            if(uuid.equals(storage[i].toString())) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] r = new Resume[size];
        for (int i = 0; i < size; i++) {
            r[i] = storage[i];
        } return r;
    }

    int size() {
        return size;
    }
}
