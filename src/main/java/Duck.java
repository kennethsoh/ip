import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class Duck {

    private static final String FILE_PATH = "./data/duck.txt";

    public static void main(String[] args) throws EmptyDetailsException, UnknownCommandException, IOException {
        Scanner scan = new Scanner(System.in);

        Storage storage = new Storage(FILE_PATH); // Create Storage instance
        ArrayList<Task> list = storage.load(); // Load tasks from file

        System.out.println("_________________________________________________");
        System.out.println("Hello! I'm DUCK");
        System.out.println("What can I do for you?");
        System.out.println("Enter command 'list' to see all saved tasks (if any)");
        System.out.println("_________________________________________________");
        String userInput = scan.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            if (userInput.equalsIgnoreCase("list")) {
                list(userInput, list);
            } else if (userInput.startsWith("mark")) {
                mark(userInput, list);
            } else if (userInput.startsWith("unmark")) {
                unmark(userInput, list);
            } else if (userInput.startsWith("todo")) {
                todo(userInput, list);
            } else if (userInput.startsWith("deadline")) {
                deadline(userInput, list);
            } else if (userInput.startsWith("event")) {
                event(userInput, list);
            } else if (userInput.startsWith("delete")) {
                delete(userInput, list);
            } else {
                try {
                    throw new UnknownCommandException("Invalid Command");
                } catch (UnknownCommandException e) {
                    System.out.println("_________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("Enter a 'todo', 'deadline' or 'event' task, or 'mark' or 'unmark' action");
                    System.out.println("_________________________________________________");
                }
            }
            storage.save(FILE_PATH, list);
            userInput = scan.nextLine();
        }

        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("_________________________________________________");
    }


    public static void list(String userInput, ArrayList<Task> list){
        System.out.println("_________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("     " + (i + 1) + "."+ list.get(i).toString());
        }
        System.out.println("_________________________________________________");
    }
    public static void mark(String userInput, ArrayList<Task> list){
        try {
            String[] input = userInput.split(" ");
            int number = Integer.parseInt(input[1]);
            if (number <= 0 || number > list.size()) {
                throw new IndexOutOfBoundsException("Task number out of range.");
            }
            Task task = list.get(number - 1);
            task.mark();
            String status = task.getStatusIcon();
            String description = task.getDescription();
            System.out.println("_________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("     [" + status + "] " + description);
            System.out.println("_________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("_________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("_________________________________________________");
        }
    }
    public static void unmark(String userInput, ArrayList<Task> list){
        try {
            String[] input = userInput.split(" ");
            int number = Integer.parseInt(input[1]);
            if (number <= 0 || number > list.size()) {
                throw new IndexOutOfBoundsException("Task number out of range.");
            }
            Task task = list.get(number - 1);
            task.unmark();
            String status = task.getStatusIcon();
            String description = task.getDescription();
            System.out.println("_________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("     [" + status + "] " + description);
            System.out.println("_________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("_________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("_________________________________________________");
        }
    }
    public static void todo(String userInput, ArrayList<Task> list) {
        try {
            String[] parts = userInput.split("todo ", 2);
            String description = parts.length > 1 ? parts[1] : "";
            if (description.isBlank()) {
                throw new EmptyDetailsException("No description provided");
            }
            Task task = new ToDo(false, description);
            list.add(task);
            System.out.println("_________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("     " + task.toString());
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            System.out.println("_________________________________________________");
        } catch (EmptyDetailsException e) {
            System.out.println("_________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("_________________________________________________");
        }
    }
    public static void deadline(String userInput, ArrayList<Task> list) {
        try {
            String[] parts = userInput.split("/by", 2);
            String[] firstPart = parts[0].split("deadline ", 2);
            String description = firstPart.length > 1 ? firstPart[1] : "";
            String by = parts.length > 1 ? parts[1] : "";
            if (description.isBlank() || by.isBlank()) {
                throw new EmptyDetailsException("No description or 'by' deadline time provided");
            }
            Task task = new Deadline(false, description, by);
            list.add(task);
            System.out.println("_________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("     " + task.toString());
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            System.out.println("_________________________________________________");
        } catch (EmptyDetailsException e) {
            System.out.println("_________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("_________________________________________________");
        } catch (Exception e) {
            System.out.println("_________________________________________________");
            System.out.println("Invalid date format! Use yyyy-MM-dd HHmm (e.g., 2025-01-27 2359).");
            System.out.println("_________________________________________________");
        }

    }
    public static void event(String userInput, ArrayList<Task> list) {
        try {
            String[] parts = userInput.split(" /from | /to ");
            String[] firstPart = parts[0].split("event ", 2);
            String description = firstPart.length > 1 ? firstPart[1] : "";
            String from = parts.length > 1 ? parts[1] : "";
            String to = parts.length > 2 ? parts[2] : "";
            if (description.isBlank() || from.isBlank() || to.isBlank()) {
                throw new EmptyDetailsException("No description or 'from' or 'to' time provided");
            }
            Task task = new Event(false, description, from, to);
            list.add(task);
            System.out.println("_________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("     " + task.toString());
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            System.out.println("_________________________________________________");
        } catch (EmptyDetailsException e) {
            System.out.println("_________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("_________________________________________________");
        } catch (Exception e) {
            System.out.println("_________________________________________________");
            System.out.println("Invalid date format! Use yyyy-MM-dd HHmm (e.g., 2025-01-27 2359).");
            System.out.println("_________________________________________________");
        }
    }
    public static void delete(String userInput, ArrayList<Task> list) {
        try {
            String[] input = userInput.split(" ");
            int number = Integer.parseInt(input[1]);
            if (number <= 0 || number > list.size()) {
                throw new IndexOutOfBoundsException("Task number out of range.");
            }
            Task task = list.remove(number - 1);
            System.out.println("_________________________________________________");
            System.out.println("Noted I've removed this task:");
            System.out.println("     " + task.toString());
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            System.out.println("_________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("_________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("_________________________________________________");
        }
    }
}
