import org.junit.jupiter.api.Test;
import org.yehorbukh.model.ItemState;
import org.yehorbukh.model.ToDoItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoItemTest {

   @Test
    public void testToDoItemBuilder() {
        ToDoItem item = new ToDoItem.ToDoItemBuilder()
                .setId(1)
                .setAuthor("Person")
                .setContext("Milk for baby")
                .setItemState(ItemState.ARCHIVED)
                .setName("Buy milk")
                .build();

        assertEquals(1, item.getId());
        assertEquals("Person", item.getAuthor());
        assertEquals("Milk for baby", item.getContext());
        assertEquals(ItemState.ARCHIVED, item.getItemState());
        assertEquals("Buy milk", item.getName());
    }
}