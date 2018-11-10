package ru.javawebinar.basejava.model;

import java.util.List;

public class ListOfTextSection extends AbstractTypeOfSection<List<String>> {
    public ListOfTextSection(List<String> information) {
        super(information);
    }

    @Override
    public String toString() {
        return String.valueOf(information);
    }
}
