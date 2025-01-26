import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;

public class Storage {
    protected String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(String filePath, ArrayList<Task> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : list) {
                writer.write(taskToString(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
            System.out.println("_________________________________________________");
        }
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> list = new ArrayList<Task>();
        File saveFile = new File(this.filePath);
        if (!saveFile.exists()) {
            saveFile.getParentFile().mkdirs();
            return list;
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
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
                    list.add(new Deadline(isDone, description, deadline));
                } else if(type.equals("E")) {
                    String[] details = parts[3].trim().split(" to ");
                    String fromTime = details[0].trim();
                    String toTime = details[1].trim();
                    list.add(new Event(isDone, description, fromTime, toTime));
                }
            }
            return list;
        }
    }

    private static String taskToString(Task task) {
        if (task instanceof ToDo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline d) {
            return "D | " + (d.isDone() ? "1" : "0") + " | " + d.getDescription() + " | " + d.deadline();
        } else if (task instanceof Event e) {
            return "E | " + (e.isDone() ? "1" : "0") + " | " + e.getDescription() + " | " + e.fromTime() + " to " + e.toTime();
        }
        return "";
    }
}
