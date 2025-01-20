import java.util.Scanner;
import java.util.ArrayList;

public class Duck {
    public static void main(String[] args) throws EmptyDetailsException, UnknownCommandException {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();
        System.out.println("_________________________________________________");
        System.out.println("Hello! I'm DUCK");
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________________");
        String userInput = scan.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("_________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("     " + (i + 1) + "."+ list.get(i).toString());
                }
                System.out.println("_________________________________________________");
            } else if (userInput.startsWith("mark")) {
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

            } else if (userInput.startsWith("unmark")) {
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

            } else if (userInput.startsWith("todo")) {
                try {
                    String[] parts = userInput.split("todo ", 2);
                    String description = parts.length > 1 ? parts[1] : "";
                    if (description.isBlank()) {
                        throw new EmptyDetailsException("No description provided");
                    }
                    Task task = new ToDo(description);
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

            } else if (userInput.startsWith("deadline")) {
                try {
                    String[] parts = userInput.split("/by", 2);
                    String[] firstPart = parts[0].split("deadline ", 2);
                    String description = firstPart.length > 1 ? firstPart[1] : "";
                    String by = parts.length > 1 ? parts[1] : "";
                    if (description.isBlank() || by.isBlank()) {
                        throw new EmptyDetailsException("No description or 'by' deadline time provided");
                    }
                    Task task = new Deadline(description, by);
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

            } else if (userInput.startsWith("event")) {
                try {
                    String[] parts = userInput.split(" /from | /to ");
                    String[] firstPart = parts[0].split("event ", 2);
                    String description = firstPart.length > 1 ? firstPart[1] : "";
                    String from = parts.length > 1 ? parts[1] : "";
                    String to = parts.length > 2 ?  parts[2] : "";
                    if (description.isBlank() || from.isBlank() || to.isBlank()){
                        throw new EmptyDetailsException("No description or 'from' or 'to' time provided");
                    }
                    Task task = new Event(description, from, to);
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
            userInput = scan.nextLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("_________________________________________________");
    }
}
