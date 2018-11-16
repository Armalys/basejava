package ru.javawebinar.basejava.model;

import java.util.Objects;

public class TextAbstractSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final String content;

    public TextAbstractSection(String content) {
        Objects.requireNonNull(content, " content must not be null");
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextAbstractSection that = (TextAbstractSection) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return content;
    }
}
