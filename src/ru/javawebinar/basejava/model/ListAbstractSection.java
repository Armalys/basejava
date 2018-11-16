package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListAbstractSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final List<String> items;

    public ListAbstractSection(String... items) {
        this(Arrays.asList(items));
    }

    public ListAbstractSection(List<String> items) {
        Objects.requireNonNull(items, " items must not be null");
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListAbstractSection that = (ListAbstractSection) o;

        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
