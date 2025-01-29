package duck;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
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

    public Task remove(int i) {
        return this.tasks.remove(i);
    }

}
