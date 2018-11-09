package ru.javawebinar.basejava.model;

public class TextType extends AbstractType<String> {

    protected TextType(String information) {
        super(information);
    }

    @Override
    public String toString() {
        return information;
    }
}
