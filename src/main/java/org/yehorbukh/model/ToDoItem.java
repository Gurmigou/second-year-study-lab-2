package org.yehorbukh.model;

import java.util.Objects;

/**
 * Data transfer object which contains all information
 * about to-do item. It is used all around the application
 * and is also represents an entity which is stored in the
 * database.
 */
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

    /**
     * Builder class for {@link ToDoItem}
     */
    public static class ToDoItemBuilder {
        private Integer id;
        private String name;
        private String author;
        private String context;
        private String deadlineDate;
        private String creationDate;
        private ItemState itemState;

        public ToDoItemBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public ToDoItemBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ToDoItemBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public ToDoItemBuilder setContext(String context) {
            this.context = context;
            return this;
        }

        public ToDoItemBuilder setDeadlineDate(String deadlineDate) {
            this.deadlineDate = deadlineDate;
            return this;
        }

        public ToDoItemBuilder setCreationDate(String creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public ToDoItemBuilder setItemState(ItemState itemState) {
            this.itemState = itemState;
            return this;
        }

        public ToDoItem build() {
            return new ToDoItem(id, name, author, context, deadlineDate, creationDate, itemState);
        }
    }
}

