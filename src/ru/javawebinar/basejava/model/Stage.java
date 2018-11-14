package ru.javawebinar.basejava.model;

import java.time.YearMonth;
import java.util.Objects;

public class Stage {
    private final YearMonth startDate;
    private final YearMonth endDate;
    private final String title;
    private final String description;

    public Stage(YearMonth startDate, YearMonth endDate, String title, String description) {
        Objects.requireNonNull(startDate, " startDate must not be null");
        Objects.requireNonNull(endDate, " endDate must not be bull");
        Objects.requireNonNull(title, " title must not be bull");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stage stage = (Stage) o;

        if (!startDate.equals(stage.startDate)) return false;
        if (!endDate.equals(stage.endDate)) return false;
        if (!title.equals(stage.title)) return false;
        return description != null ? description.equals(stage.description) : stage.description == null;
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        if (description == null) return "Stage(" + startDate + " " + endDate + " " + title + ")";
        else return "Stage(" + startDate + " " + endDate + " " + title + " " + description + ")";
    }
}
