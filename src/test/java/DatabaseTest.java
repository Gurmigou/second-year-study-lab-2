import org.yehorbukh.dao.Database;
import org.yehorbukh.model.ItemState;
import org.yehorbukh.model.ToDoItem;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {
    private final Database db = Database.getInstance();

    private final String testName = "localhost:3306";
    private final String testPassword = "root";

    // test delete all works
    public void testDeleteAll() throws SQLException {
        db.deleteAllTasks();
        assertEquals(0, db.getSize());
    }

    // test select all tasks
    public void testSelectAll() throws SQLException {
        ToDoItem item = new ToDoItem.ToDoItemBuilder()
                .setId(1)
                .setAuthor("Person")
                .setContext("Milk for baby")
                .setItemState(ItemState.ARCHIVED)
                .setName("Buy milk")
                .build();

        db.addToDoItem(item);

        db.selectToDoItems("where item.state = 'ARCHIVED'");
        assertEquals(1, db.getSize());
    }
}
