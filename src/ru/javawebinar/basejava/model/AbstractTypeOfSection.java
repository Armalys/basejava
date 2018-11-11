package ru.javawebinar.basejava.model;

import java.util.Objects;

public abstract class AbstractTypeOfSection<T> {
    protected T information;

    public AbstractTypeOfSection(T information) {
        this.information = information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTypeOfSection<?> that = (AbstractTypeOfSection<?>) o;
        return Objects.equals(information, that.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(information);
    }
}
