package ru.javawebinar.basejava.model;

public class TextSection extends AbstractTypeOfSection<String> {
    public TextSection(String information) {
        super(information);
    }

    @Override
    public String toString() {
        return information;
    }
}
