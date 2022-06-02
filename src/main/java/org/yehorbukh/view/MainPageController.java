package org.yehorbukh.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.yehorbukh.App;
import org.yehorbukh.dao.Database;
import org.yehorbukh.model.DataHolder;
import org.yehorbukh.model.ItemState;
import org.yehorbukh.model.ToDoItem;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
 * This controller is a start point of the application.
 * It represents a main page (or main window in terminology
 * of JavaFX application).
 * Moreover, this class carries
 * information of different buttons, table columns, text fields,
 * menu items and so on.
 * It contains logic which handles events such as mouse click.
 */
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

    @FXML
    public MenuItem export;

    @FXML
    public MenuItem exit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CommonController.initToDoTableView(
                List.of(taskNameColumn, authorColumn,
                        contextColumn, deadlineDataColumn,
                        creationDate));
        try {
            List<ToDoItem> toDoItems = Database.getInstance().selectToDoItems("WHERE itemState != 'ARCHIVED'");
            tableView.getItems().addAll(toDoItems);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToContextButton(ActionEvent actionEvent) throws IOException {
        App.setRoot("context-list-page");
    }

    @FXML
    public void switchToArchiveButton(ActionEvent actionEvent) throws IOException {
        DataHolder.getInstance().setValue("ARCHIVED");
        App.setRoot("view-page");
    }

    private boolean objectsRequireNonNull(Object... objects) {
        return Arrays.stream(objects).noneMatch(Objects::isNull);
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
                Database.getInstance().addToDoItem(toDoItem);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
                Database.getInstance().updateToDoItemState(toDoItem.getId(), ItemState.ARCHIVED);
                DataHolder.getInstance().setValue("ARCHIVED");
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
                Database.getInstance().deleteToDoItem(toDoItem.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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