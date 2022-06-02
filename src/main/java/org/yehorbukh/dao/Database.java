package org.yehorbukh.dao;

import org.yehorbukh.model.Context;
import org.yehorbukh.model.ItemState;
import org.yehorbukh.model.ToDoItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an abstraction to work with the database correlated with this application.
 * It provides ease-to-use methods to execute create, read, update, delete operations.
 * Internally this class uses MySQL as an instance of relational-data-base. You can easily
 * change RDBMS provider by changing the JDBC driver.
 */
public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/lab2_ui_todo_list";
    private static final String username = "root";
    private static final String password = "root";

    private static Database database;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Database() {
    }

    public static Database getInstance() {
        if (database == null)
            database = new Database();
        return database;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, username, password);
    }

    private ToDoItem mapToTodoItem(ResultSet rs) throws SQLException {
        return new ToDoItem(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("author"),
                rs.getString("context"),
                rs.getString("deadlineDate"),
                rs.getString("creationDate"),
                ItemState.valueOf(rs.getString("itemState"))
        );
    }

    public List<ToDoItem> selectToDoItems(String whereFilter) throws SQLException {
        String query = "SELECT * FROM todo_list" +
                ((whereFilter.isBlank()) ? ";" : " " + whereFilter + ";");

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<ToDoItem> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(mapToTodoItem(resultSet));
        }

        return list;
    }

    public void addToDoItem(ToDoItem toDoItem) throws SQLException {
        String query = "INSERT INTO todo_list (name, author, context, " +
                "deadlineDate, creationDate, itemState)\n" +
                "values (?, ?, ?, ?, ?, ?)";

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                query, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, toDoItem.getName());
        preparedStatement.setString(2, toDoItem.getAuthor());
        preparedStatement.setString(3, toDoItem.getContext());
        preparedStatement.setString(4, toDoItem.getDeadlineDate());
        preparedStatement.setString(5, toDoItem.getCreationDate());
        preparedStatement.setString(6, toDoItem.getItemState().name());

        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        toDoItem.setId((int) generatedKeys.getLong(1));
    }

    public void updateToDoItemState(int id, ItemState newState) throws SQLException {
        String query = String.format(
                "UPDATE todo_list SET itemState = '%s' WHERE id = %d", newState.name(), id);

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    public void deleteToDoItem(int id) throws SQLException {
        Connection connection = getConnection();
        String query = "DELETE FROM todo_list WHERE id = " + id + ";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    public List<Context> selectContextList() throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT context, COUNT(*) AS num_of_tasks FROM todo_list WHERE itemState != 'ARCHIVED' GROUP BY context";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Context> list = new ArrayList<>();

        while (resultSet.next()) {
            Context context = new Context (
                    resultSet.getString("context"),
                    resultSet.getInt("num_of_tasks")
            );
            list.add(context);
        }

        return list;
    }

    public void deleteContextWithName(String name) throws SQLException {
        Connection connection = getConnection();
        String query = "DELETE FROM todo_list WHERE context = '" + name + "';";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }
}
