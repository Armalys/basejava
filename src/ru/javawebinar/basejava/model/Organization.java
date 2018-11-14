package ru.javawebinar.basejava.model;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;

public class Organization {
    private final Link homePage;
    private final List<Stage> stage;

    public Organization(String name, String url, YearMonth startDate, YearMonth endDate, String title, String discriprion) {
        this(new Link(name, url), new Stage(startDate, endDate, title, discriprion));
    }

    public Organization(Link homePage, Stage... stages) {
        this(homePage, Arrays.asList(stages));
    }

    public Organization(Link homePage, List<Stage> stages) {
        this.homePage = homePage;
        this.stage = stages;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (homePage != null ? !homePage.equals(that.homePage) : that.homePage != null) return false;
        return stage != null ? stage.equals(that.stage) : that.stage == null;
    }

    @Override
    public int hashCode() {
        int result = homePage != null ? homePage.hashCode() : 0;
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return homePage + " " + stage;

    }
}
