package duck;
import java.util.ArrayList;

/**
 * Manages a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

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
     * Finds task(s) in the list
     *
     * @param keyword The description of the task(s) to find.
     * @return List of matching tasks.
     */
    public TaskList find(String keyword) {
        TaskList matchingList = new TaskList(new ArrayList<>());
        for (Task task : this.tasks) {
            if (keyword.equalsIgnoreCase(task.getDescription())) {
                matchingList.add(task);
            }
        }
        return matchingList;
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
