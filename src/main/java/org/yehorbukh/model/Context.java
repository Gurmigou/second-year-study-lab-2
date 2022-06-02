package org.yehorbukh.model;

import java.util.Objects;

/**
 * Represents a data transfer object which carries information
 * about context name and number of tasks it contains.
 */
public class Context {
    private String context;
    private int numOfTasks;

    public Context(String context, int numOfTasks) {
        this.context = context;
        this.numOfTasks = numOfTasks;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }

    public void setNumOfTasks(int numOfTasks) {
        this.numOfTasks = numOfTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Context context1 = (Context) o;
        return numOfTasks == context1.numOfTasks && Objects.equals(context, context1.context);
    }

    @Override
    public int hashCode() {
        return Objects.hash(context, numOfTasks);
    }

    @Override
    public String toString() {
        return "Context{" +
                "context='" + context + '\'' +
                ", numOfTasks=" + numOfTasks +
                '}';
    }
}
