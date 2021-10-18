module org.yehorbukh {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;

    opens org.yehorbukh to javafx.fxml;
    exports org.yehorbukh;
}