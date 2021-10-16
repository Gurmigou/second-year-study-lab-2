module org.yehorbukh {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.yehorbukh to javafx.fxml;
    exports org.yehorbukh;
}