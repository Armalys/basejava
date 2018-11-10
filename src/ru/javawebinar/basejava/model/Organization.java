package ru.javawebinar.basejava.model;

public class Organization {
    private String name;
    private String date;
    private String link;
    private String position;
    private String content;

    public Organization(String name, String date, String link, String position, String content) {
        this.name = name;
        this.date = date;
        this.link = link;
        this.position = position;
        this.content = content;
    }

    public Organization(String name, String date, String link, String content) {
        this.name = name;
        this.date = date;
        this.link = link;
        this.content = content;
    }

    @Override
    public String toString() {
        return name + " " + date + " " + link + " " + position + " " + content;
    }
}
