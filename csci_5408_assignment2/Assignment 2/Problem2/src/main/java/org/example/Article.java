package org.example;

public class Article {
    String title;
    String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nContent: " + content;
    }
}
