package duck;
import java.util.Scanner;

/**
 * Handles user interactions, including reading input and displaying messages.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Constructs a Ui instance and initializes the Scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next command entered by the user.
     *
     * @return The user input as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("_________________________________________________");
        System.out.println("Hello! I'm DUCK");
        System.out.println("What can I do for you?");
        System.out.println("Enter command 'list' to see all saved tasks (if any)");
        System.out.println("_________________________________________________");
    }

    /**
     * Displays the list of tasks.
     *
     * @param list The task list to be displayed.
     */
    public void showList(TaskList list) {
        System.out.println("_________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("     " + (i + 1) + "." + list.get(i).toString());
        }
        System.out.println("_________________________________________________");
    }

    /**
     * Displays a message indicating a task has been marked as completed.
     *
     * @param t The task that was marked as completed.
     */
    public void mark(Task t) {
        System.out.println("_________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("     [" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println("_________________________________________________");
    }

    /**
     * Displays a message indicating a task has been marked as not completed.
     *
     * @param t The task that was unmarked.
     */
    public void unmark(Task t) {
        System.out.println("_________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("     [" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println("_________________________________________________");
    }

    /**
     * Displays a message confirming a task has been added to the list.
     *
     * @param t The task that was added.
     * @param list The task list where the task was added.
     */
    public void addTaskMessage(Task t, TaskList list) {
        System.out.println("_________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("     " + t.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println("_________________________________________________");
    }

    /**
     * Displays an error message for unrecognized commands.
     */
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

    /**
     * Displays an error message.
     *
     * @param m The error message to be displayed.
     */
    public void showErrorMessage(String m) {
        System.out.println("_________________________________________________");
        System.out.println(m);
        System.out.println("_________________________________________________");

    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showByeMessage() {
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("_________________________________________________");
    }
}
