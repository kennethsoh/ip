package duck;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Handles saving and loading tasks from a file.
 */
public class Storage {
    protected String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the task list to a file.
     *
     * @param filePath The file path to save the tasks.
     * @param list The list of tasks to save.
     */
    public void save(String filePath, TaskList list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : list.getAllTasks()) {
                writer.write(taskToString(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
            System.out.println("_________________________________________________");
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks retrieved from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> list = new ArrayList<Task>();
        File saveFile = new File(this.filePath);
        if (!saveFile.exists()) {
            saveFile.getParentFile().mkdirs();
            return list;
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();

                if (type.equals("T")) {
                    list.add(new ToDo(isDone, description));
                } else if (type.equals("D")) {
                    String deadline = parts[3].trim();
                    LocalDateTime by = LocalDateTime.parse(deadline, dateTimeFormat);
                    list.add(new Deadline(isDone, description, by.format(dateTimeFormat)));
                } else if (type.equals("E")) {
                    String[] details = parts[3].trim().split(" to ");
                    String from = details[0].trim();
                    String to = details[1].trim();
                    LocalDateTime fromTime = LocalDateTime.parse(from, dateTimeFormat);
                    LocalDateTime toTime = LocalDateTime.parse(to, dateTimeFormat);
                    list.add(new Event(isDone, description,
                            fromTime.format(dateTimeFormat), toTime.format(dateTimeFormat)));
                }
            }
            return list;
        }
    }

    /**
     * Converts a task to a formatted string for storage.
     *
     * @param task The task to convert.
     * @return The formatted string representing the task.
     */
    private static String taskToString(Task task) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (task instanceof ToDo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline d) {
            return "D | " + (d.isDone() ? "1" : "0") + " | " + d.getDescription()
                    + " | " + d.deadline().format(dateTimeFormat);
        } else if (task instanceof Event e) {
            return "E | " + (e.isDone() ? "1" : "0") + " | " + e.getDescription()
                    + " | " + e.fromTime().format(dateTimeFormat)
                    + " to " + e.toTime().format(dateTimeFormat);
        }
        return "";
    }
}
