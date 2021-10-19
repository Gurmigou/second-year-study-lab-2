package org.yehorbukh;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
    @FXML
    public Button allTasks;

    @FXML
    public Button contextButton;

    @FXML
    public Button archiveButton;

    @FXML
    public TableView<ToDoItem> tableView;

    @FXML
    public TableColumn<ToDoItem, String> taskNameColumn;

    @FXML
    public TableColumn<ToDoItem, String> authorColumn;

    @FXML
    public TableColumn<ToDoItem, String> contextColumn;

    @FXML
    public TableColumn<ToDoItem, String> deadlineDataColumn;

    @FXML
    public TableColumn<ToDoItem, String> creationDateColumn;

    @FXML
    public MenuItem export;

    @FXML
    public MenuItem exit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CommonController.initToDoTableView(
                List.of(taskNameColumn, authorColumn,
                        contextColumn, deadlineDataColumn,
                        creationDateColumn));

        if (DataHolder.getInstance() != null) {
            String value = DataHolder.getInstance().getValue();
            String whereFilter = (value.equals("ARCHIVED")) ?
                    "WHERE itemState = 'ARCHIVED'" : "WHERE context = '" + value + "'";
            try {
                List<ToDoItem> toDoItems = Database.getInstance().selectToDoItems(whereFilter);
                tableView.getItems().addAll(toDoItems);
                DataHolder.getInstance().clear();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void switchToAllTasks(ActionEvent actionEvent) throws IOException {
        App.setRoot("main-page");
    }

    public void switchToContextButton(ActionEvent actionEvent) throws IOException {
        App.setRoot("context-list-page");
    }

    public void switchToArchiveButton(ActionEvent actionEvent) throws IOException {
        DataHolder.getInstance().setValue("ARCHIVED");
        App.setRoot("view-page");
    }

    public void doOnSort(SortEvent<TableView> tableViewSortEvent) {
    }

    @FXML
    public void onExportAction(ActionEvent actionEvent) {
        CommonController.exportData();
    }

    @FXML
    public void onExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }
}
