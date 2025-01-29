package duck;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }
    public String readCommand() {
        return scanner.nextLine();
    }
    public void showWelcome() {
        System.out.println("_________________________________________________");
        System.out.println("Hello! I'm DUCK");
        System.out.println("What can I do for you?");
        System.out.println("Enter command 'list' to see all saved tasks (if any)");
        System.out.println("_________________________________________________");
    }

    public void showList(TaskList list) {
        System.out.println("_________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("     " + (i + 1) + "."+ list.get(i).toString());
        }
        System.out.println("_________________________________________________");
    }

    public void mark(Task t) {
        System.out.println("_________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("     [" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println("_________________________________________________");
    }

    public void unmark(Task t) {
        System.out.println("_________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("     [" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println("_________________________________________________");
    }

    public void addTaskMessage(Task t, TaskList list) {
        System.out.println("_________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("     " + t.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println("_________________________________________________");
    }

    public void removeTaskMessage(Task t, TaskList list) {
        System.out.println("_________________________________________________");
        System.out.println("Noted I've removed this task:");
        System.out.println("     " + t.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println("_________________________________________________");
    }

    public void findMessage(TaskList list) {
        System.out.println("_________________________________________________");
        if (list.size() < 1) {
            System.out.println("There are no matching tasks in your list:");
            System.out.println("_________________________________________________");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("     " + (i + 1) + "."+ list.get(i).toString());
            }
            System.out.println("_________________________________________________");
        }
    }

    public void showCommandErrorMessage() {
        System.out.println("_________________________________________________");
        System.out.println("Enter a 'todo', 'deadline' or 'event' task, or 'mark' or 'unmark' action");
        System.out.println("_________________________________________________");
    }

    public void showErrorMessage(String m) {
        System.out.println("_________________________________________________");
        System.out.println(m);
        System.out.println("_________________________________________________");

    }

    public void showByeMessage() {
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("_________________________________________________");
    }
}
