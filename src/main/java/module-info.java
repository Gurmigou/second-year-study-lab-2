module org.yehorbukh {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;

    opens org.yehorbukh to javafx.fxml;
    exports org.yehorbukh;
    exports org.yehorbukh.dao;
    opens org.yehorbukh.dao to javafx.fxml;
    exports org.yehorbukh.model;
    opens org.yehorbukh.model to javafx.fxml;
    exports org.yehorbukh.view;
    opens org.yehorbukh.view to javafx.fxml;
}