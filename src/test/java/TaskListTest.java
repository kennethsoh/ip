import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duck.Task;
import duck.TaskList;

public class TaskListTest {

    @Test
    public void addTaskTest() {
        ArrayList<Task> list = new ArrayList<Task>();
        TaskList taskList = new TaskList(list);
        Task task = new Task(false, "sleep");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }
    @Test
    public void deleteTaskTest() {
        ArrayList<Task> list = new ArrayList<Task>();
        TaskList taskList = new TaskList(list);
        Task task = new Task(false, "sleep");
        taskList.add(task);
        taskList.remove(0);
        assertEquals(0, taskList.size());
    }

}
