import duck.ToDo;
import duck.Task;
import duck.TaskList;
import duck.Storage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;;

public class StorageTest {
    @Test
    public void saveTaskTest() throws IOException {
        Storage storage = new Storage("data/testfile.txt");
        ArrayList<Task> list = new ArrayList<Task>();
        TaskList taskList = new TaskList(list);
        taskList.add(new ToDo(false, "sleep"));
        storage.save("data/testfile.txt", taskList);
        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertEquals("[T][ ] sleep", loadedTasks.get(0).toString());
    }
}
