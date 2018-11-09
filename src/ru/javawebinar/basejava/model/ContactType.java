package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Телефон"),
    SKYPE("Скайп"),
    EMAIL("Почта"),
    LINKEDIN("Профиль"),
    GITHUB("Профиль"),
    STACKOVERFLOW("Профиль"),
    HOMEPAGE("Домашняя страница");

    private String contact;

    ContactType(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }
}
