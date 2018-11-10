package ru.javawebinar.basejava.model;

public abstract class AbstractTypeOfSection<T> {
    protected T information;

    public AbstractTypeOfSection(T information) {
        this.information = information;
    }
}
