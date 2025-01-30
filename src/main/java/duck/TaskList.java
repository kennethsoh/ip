package duck;
import java.io.File;
import java.util.ArrayList;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves a specific task by index.
     *
     * @param i The index of the task.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Retrieves all tasks in the list.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to add.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Removes a task from the list.
     *
     * @param i The index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int i) {
        return this.tasks.remove(i);
    }

}
