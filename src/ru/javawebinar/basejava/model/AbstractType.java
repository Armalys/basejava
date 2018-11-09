package ru.javawebinar.basejava.model;

public abstract class AbstractType<T> {
    protected T information;

    protected AbstractType(T information) {
        this.information = information;
    }
}
