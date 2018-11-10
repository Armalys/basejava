package ru.javawebinar.basejava.model;

import java.util.List;

public class ListOfTextSection extends AbstractTypeOfSection<List<String>> {
    private List<String> information;

    public ListOfTextSection(List<String> information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return String.valueOf(information);
    }
}
