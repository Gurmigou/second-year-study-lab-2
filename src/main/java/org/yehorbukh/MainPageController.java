package org.yehorbukh;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    @FXML
    public Button contextButton;
    @FXML
    public Button archiveButton;
    @FXML
    public TableColumn<ToDoItem, String> taskNameColumn;
    @FXML
    public TableColumn<ToDoItem, String> authorColumn;
    @FXML
    public TableColumn<ToDoItem, String> contextColumn;
    @FXML
    public TableColumn<ToDoItem, Date> deadlineDataColumn;
    @FXML
    public TableColumn<ToDoItem, Date> creationDate;

    @FXML
    public TextField taskField;
    @FXML
    public TextField authorField;
    @FXML
    public TextField contextField;
    @FXML
    public DatePicker dateField;
    @FXML
    public Button addTaskButton;

    @FXML
    public TableView<ToDoItem> tableView;

    @FXML
    public MenuItem menuItemAddToArchive;
    @FXML
    public MenuItem menuItemDelete;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        contextColumn.setCellValueFactory(new PropertyValueFactory<>("context"));
        deadlineDataColumn.setCellValueFactory(new PropertyValueFactory<>("deadlineDate"));
    }

    @FXML
    public void switchToContextButton(ActionEvent actionEvent) {
        System.out.println("1");
    }

    @FXML
    public void addTask(ActionEvent actionEvent) {
        ToDoItem toDoItem = new ToDoItem(taskField.getText(), authorField.getText(),
                contextField.getText(), dateField.getValue().toString());

        tableView.getItems().add(toDoItem);
        System.out.println("2");
    }

    @FXML
    public void switchToArchiveButton(ActionEvent actionEvent) {
        System.out.println("3");
    }

    @FXML
    public void doOnSort(SortEvent<ToDoItem> tableViewSortEvent) {

    }

    @FXML
    public void menuItemAddToArchiveClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void menuItemDeleteClicked(ActionEvent actionEvent) {
    }
}
