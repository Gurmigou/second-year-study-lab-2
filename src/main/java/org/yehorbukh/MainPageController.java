package org.yehorbukh;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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

    private final Database database = new Database();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        contextColumn.setCellValueFactory(new PropertyValueFactory<>("context"));
        deadlineDataColumn.setCellValueFactory(new PropertyValueFactory<>("deadlineDate"));
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

        try {
            List<ToDoItem> toDoItems = database.selectToDoItems("WHERE itemState != 'ARCHIVED'");
            tableView.getItems().addAll(toDoItems);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToContextButton(ActionEvent actionEvent) {
        System.out.println("1");
    }

    private boolean objectsRequireNonNull(Object... objects) {
        for (Object o : objects) {
            if (o == null)
                return false;
        }
        return true;
    }

    private boolean allFieldAreFilled() {
        return objectsRequireNonNull(taskField.getText(), authorField.getText(),
                                     contextField.getText(), dateField.getValue());
    }

    @FXML
    public void addTask(ActionEvent actionEvent) {
        if (allFieldAreFilled() && dateField.getValue().compareTo(LocalDate.now()) >= 0) {
            ToDoItem toDoItem = new ToDoItem(
                    taskField.getText(), authorField.getText(),
                    contextField.getText(), dateField.getValue().toString(),
                    LocalDate.now().toString(), ItemState.AVAILABLE);
            tableView.getItems().add(toDoItem);
            try {
                database.addToDoItem(toDoItem);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
        ToDoItem toDoItem = tableView.getSelectionModel().getSelectedItem();
        if (toDoItem != null) {
            toDoItem.setItemState(ItemState.ARCHIVED);
            tableView.getItems().remove(toDoItem);
            try {
                database.updateToDoItemState(toDoItem.getId(), ItemState.ARCHIVED);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void menuItemDeleteClicked(ActionEvent actionEvent) {
        ToDoItem toDoItem = tableView.getSelectionModel().getSelectedItem();
        if (toDoItem != null) {
            tableView.getItems().remove(toDoItem);
            try {
                database.deleteToDoItem(toDoItem.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
