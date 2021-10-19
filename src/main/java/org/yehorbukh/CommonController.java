package org.yehorbukh;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.List;

public class CommonController {
    private static final List<String> todoFields =
            List.of("name", "author", "context", "deadlineDate", "creationDate");

    private static final String PATH = "export_data.txt";

    public static void initToDoTableView(List<TableColumn<?, ?>> tableColumns) {
        for (int i = 0; i < todoFields.size(); i++) {
            tableColumns.get(i)
                    .setCellValueFactory(new PropertyValueFactory<>(todoFields.get(i)));
        }
    }

    public static void exportData() {
        try {
            List<ToDoItem> toDoItems = Database
                    .getInstance()
                    .selectToDoItems("");

            StringBuilder sb = new StringBuilder();
            toDoItems.forEach(s -> sb.append(s).append('\n'));

            Files.writeString(Path.of(PATH), sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
