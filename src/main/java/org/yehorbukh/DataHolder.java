package org.yehorbukh;

public class DataHolder {
    private static final DataHolder dataHolder = new DataHolder();

    private String value;

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        return dataHolder;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void clear() {
        value = null;
    }
}
