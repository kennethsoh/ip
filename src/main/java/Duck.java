import java.util.Scanner;
import java.util.ArrayList;

public class Duck {
    public static void main(String[] args) {
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
                String[] input = userInput.split(" ");
                int number = Integer.parseInt(input[1]);
                Task task = list.get(number - 1);
                task.mark();
                String status = task.getStatusIcon();
                String description = task.getDescription();
                System.out.println("_________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("     ["+status+"] "+description);
                System.out.println("_________________________________________________");

            } else if (userInput.startsWith("unmark")) {
                String[] input = userInput.split(" ");
                int number = Integer.parseInt(input[1]);
                Task task = list.get(number - 1);
                task.unmark();
                String status = task.getStatusIcon();
                String description = task.getDescription();
                System.out.println("_________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("     [" + status + "] " + description);
                System.out.println("_________________________________________________");

            } else if (userInput.startsWith("todo")) {
                System.out.println("_________________________________________________");
                System.out.println("Got it. I've added this task:");
                Task task = new ToDo(userInput.substring(5));
                list.add(task);
                System.out.println("     " + task.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                System.out.println("_________________________________________________");

            } else if (userInput.startsWith("deadline")) {
                System.out.println("_________________________________________________");
                System.out.println("Got it. I've added this task:");
                String[] parts = userInput.split("/by");
                String description = parts[0].substring(9);
                String by = parts[1];
                Task task = new Deadline(description, by);
                list.add(task);
                System.out.println("     " + task.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                System.out.println("_________________________________________________");

            } else if (userInput.startsWith("event")) {
                System.out.println("_________________________________________________");
                System.out.println("Got it. I've added this task:");
                String[] parts = userInput.split(" /from | /to ");
                String description = parts[0].split(" ", 2)[1];
                String from = parts[1];
                String to = parts[2];
                Task task = new Event(description, from , to);
                list.add(task);
                System.out.println("     " + task.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                System.out.println("_________________________________________________");

            } else {
                System.out.println("_________________________________________________");
                System.out.println("Invalid Input");
                System.out.println("_________________________________________________");
            }
            userInput = scan.nextLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("_________________________________________________");
    }
}
