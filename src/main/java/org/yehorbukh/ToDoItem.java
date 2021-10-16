package org.yehorbukh;

import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class ToDoItem {
    private String name;
    private String author;
    private String context;
    private String deadlineDate;

//    public ToDoItem(String name, String author, String context, String deadlineDate) {
//        this.name = new SimpleStringProperty(name);
//        this.author = new SimpleStringProperty(author);
//        this.context = new SimpleStringProperty(context);
//        this.deadlineDate = new SimpleStringProperty(deadlineDate);
//    }

    public ToDoItem(String name, String author, String context, String deadlineDate) {
        this.name = name;
        this.author = author;
        this.context = context;
        this.deadlineDate = deadlineDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", context='" + context + '\'' +
                ", deadlineDate=" + deadlineDate +
                '}';
    }
}

