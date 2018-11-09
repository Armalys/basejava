package ru.javawebinar.basejava.model;

import java.util.List;

public class ListType extends AbstractType<List<String>> {

    public ListType(List<String> information) {
        super(information);
    }

    @Override
    public String toString() {
        return String.valueOf(information);
    }
}
