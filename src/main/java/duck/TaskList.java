package duck;
import java.io.File;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    public int size() {
        return this.tasks.size();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public TaskList find(String keyword) {
        TaskList matchingList = new TaskList(new ArrayList<>());
        for (Task task : this.tasks) {
            if (keyword.equalsIgnoreCase(task.getDescription())) {
                matchingList.add(task);
            }
        }
        return matchingList;
    }

    public Task remove(int i) {
        return this.tasks.remove(i);
    }

}
