package org.yehorbukh;

import java.util.Objects;

public class ToDoItem {
    private Integer id;
    private final String name;
    private final String author;
    private final String context;
    private final String deadlineDate;
    private final String creationDate;
    private ItemState itemState;

    public ToDoItem(Integer id, String name,
                    String author, String context,
                    String deadlineDate, String creationDate,
                    ItemState itemState)
    {
        this.id = id;
        this.name = name;
        this.author = author;
        this.context = context;
        this.deadlineDate = deadlineDate;
        this.creationDate = creationDate;
        this.itemState = itemState;
    }

    public ToDoItem(String name, String author,
                    String context, String deadlineDate,
                    String creationDate, ItemState itemState)
    {
        this.name = name;
        this.author = author;
        this.context = context;
        this.deadlineDate = deadlineDate;
        this.creationDate = creationDate;
        this.itemState = itemState;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getContext() {
        return context;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public ItemState getItemState() {
        return itemState;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setItemState(ItemState itemState) {
        this.itemState = itemState;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoItem toDoItem = (ToDoItem) o;
        return Objects.equals(id, toDoItem.id) && Objects.equals(name, toDoItem.name) && Objects.equals(author, toDoItem.author) && Objects.equals(context, toDoItem.context) && Objects.equals(deadlineDate, toDoItem.deadlineDate) && Objects.equals(creationDate, toDoItem.creationDate) && itemState == toDoItem.itemState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, context, deadlineDate, creationDate, itemState);
    }
}

