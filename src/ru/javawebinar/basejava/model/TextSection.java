package ru.javawebinar.basejava.model;

public class TextSection extends AbstractTypeOfSection<String> {
    private String information;

    public TextSection(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return information;
    }
}
