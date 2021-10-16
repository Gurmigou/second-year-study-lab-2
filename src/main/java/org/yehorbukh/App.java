package org.yehorbukh;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

//        // создаем список объектов
//        ObservableList<ToDoItem> people = FXCollections.observableArrayList(
//                new ToDoItem("Buy some milk", "Yehor", "Home tasks", new Date(TimeUnit.DAYS.toDays(1))),
//                new ToDoItem("Repair something", "Yehor", "Task", new Date(TimeUnit.DAYS.toDays(3)))
//        );

//        // определяем таблицу и устанавливаем данные
//        TableView<ToDoItem> table = new TableView<>(people);
//        table.setPrefWidth(250);
//        table.setPrefHeight(200);
//
//        // столбец для вывода имени
//        TableColumn<ToDoItem, String> nameColumn = new TableColumn<>("Name");
//        // определяем фабрику для столбца с привязкой к свойству name
//        nameColumn.setCellValueFactory(new PropertyValueFactory<ToDoItem, String>("name"));
//        // добавляем столбец
//        table.getColumns().add(nameColumn);
//
//        // столбец для вывода возраста
//        TableColumn<ToDoItem, Integer> ageColumn = new TableColumn<ToDoItem, Integer>("Age");
//        ageColumn.setCellValueFactory(new PropertyValueFactory<ToDoItem, Integer>("age"));
//        table.getColumns().add(ageColumn);
//
//        FlowPane root = new FlowPane(10, 10, table);
//
//        Scene scene = new Scene(root, 300, 250);
//
//        stage.setScene(scene);
//        stage.setTitle("TableView in JavaFX");
//        stage.show();

        
        scene = new Scene(loadFXML("main_page"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}