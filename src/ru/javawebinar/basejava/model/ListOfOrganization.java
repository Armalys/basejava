package ru.javawebinar.basejava.model;

import java.util.List;

public class ListOfOrganization extends AbstractTypeOfSection<List> {
    private List<Organization> information;

    public ListOfOrganization(List<Organization> information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return String.valueOf(information);
    }
}
