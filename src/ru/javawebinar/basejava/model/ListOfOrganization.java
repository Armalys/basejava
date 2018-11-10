package ru.javawebinar.basejava.model;

import java.util.List;

public class ListOfOrganization extends AbstractTypeOfSection<List<Organization>> {
    public ListOfOrganization(List<Organization> information) {
        super(information);
    }

    @Override
    public String toString() {
        return String.valueOf(information);
    }
}
