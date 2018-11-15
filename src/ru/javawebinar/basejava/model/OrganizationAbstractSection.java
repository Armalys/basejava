package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationAbstractSection extends AbstractSection {
    private final List<Organization> organizations;

    public OrganizationAbstractSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationAbstractSection(List<Organization> organizations) {
        Objects.requireNonNull(organizations, " organizations must not be null");
        this.organizations = organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationAbstractSection that = (OrganizationAbstractSection) o;

        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }

    @Override
    public String toString() {
        return organizations.toString();
    }
}
