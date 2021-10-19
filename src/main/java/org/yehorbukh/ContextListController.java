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

public class ContextListController implements Initializable {
    @FXML
    public Button allTasksButton;

    @FXML
    public Button archiveButton;

    @FXML
    public TableView<Context> tableView;

    @FXML
    public TableColumn<Context, String> contextColumn;

    @FXML
    public TableColumn<Context, Integer> numOfTasks;

    @FXML
    public MenuItem viewTasks;

    @FXML
    public MenuItem deleteContext;

    @FXML
    public MenuItem export;

    @FXML
    public MenuItem exit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contextColumn.setCellValueFactory(new PropertyValueFactory<>("context"));
        numOfTasks.setCellValueFactory(new PropertyValueFactory<>("numOfTasks"));

        try {
            List<Context> contextList = Database.getInstance().selectContextList();
            tableView.getItems().addAll(contextList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToAllTasksButton(ActionEvent actionEvent) throws IOException {
        App.setRoot("main-page");
    }

    @FXML
    public void switchToArchiveButton(ActionEvent actionEvent) throws IOException {
        DataHolder.getInstance().setValue("ARCHIVED");
        App.setRoot("view-page");
    }

    @FXML
    public void doOnSort(SortEvent<TableView> tableViewSortEvent) {
    }

    @FXML
    public void deleteContextClicked(ActionEvent actionEvent) {
        Context context = tableView.getSelectionModel().getSelectedItem();
        if (context != null) {
            tableView.getItems().remove(context);
            try {
                Database.getInstance().deleteContextWithName(context.getContext());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void viewTasksClicked(ActionEvent actionEvent) throws IOException {
        Context context = tableView.getSelectionModel().getSelectedItem();
        if (context != null) {
            DataHolder.getInstance().setValue(context.getContext());
            App.setRoot("view-page");
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
