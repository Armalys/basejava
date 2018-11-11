package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private String name;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String link;
    private String position;
    private String content;

    public Organization(String name, LocalDate dateStart, LocalDate dateEnd, String link, String position, String content) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.link = link;
        this.position = position;
        this.content = content;
    }

    public Organization(String name, LocalDate dateStart, LocalDate dateEnd, String link, String content) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.link = link;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(dateStart, that.dateStart) &&
                Objects.equals(dateEnd, that.dateEnd) &&
                Objects.equals(link, that.link) &&
                Objects.equals(position, that.position) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateStart, dateEnd, link, position, content);
    }

    @Override
    public String toString() {
        return name + " " + dateStart + " " + dateEnd + " " + link + " " + position + " " + content;
    }
}
