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
    public String showWelcome() {
        return """
            Hello! I'm DUCK
            What can I do for you?");
            Enter command 'list' to see all saved tasks (if any)""";
    }

    /**
     * Displays the list of tasks.
     *
     * @param list The task list to be displayed.
     */
    public String showList(TaskList list) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            output.append("  ").append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
        }
        return output.toString();
    }

    /**
     * Displays a message indicating a task has been marked as completed.
     *
     * @param t The task that was marked as completed.
     */
    public String mark(Task t) {
        return "Nice! I've marked this task as done:\n"
               + "     [" + t.getStatusIcon() + "] " + t.getDescription() + "\n";
    }

    /**
     * Displays a message indicating a task has been marked as not completed.
     *
     * @param t The task that was unmarked.
     */
    public String unmark(Task t) {
        return "Nice! I've marked this task as not done yet:\n"
               + "     [" + t.getStatusIcon() + "] " + t.getDescription() + "\n";
    }

    /**
     * Displays a message confirming a task has been added to the list.
     *
     * @param t The task that was added.
     * @param list The task list where the task was added.
     */
    public String addTaskMessage(Task t, TaskList list) {
        return "I've added this task:\n"
               + t.toString() + "\n"
               + "Now you have " + list.size() + " tasks in the list\n";
    }

    /**
     * Displays an error message for unrecognized commands.
     */
    public String removeTaskMessage(Task t, TaskList list) {
        return "I've removed this task:\n"
               + t.toString() + "\n"
               + "Now you have " + list.size() + " tasks in the list\n";
    }

    /**
     * Displays the list of matching tasks
     * @param list The task list to be displayed
     */
    public String findMessage(TaskList list) {
        if (list.size() < 1) {
            return "There are no matching tasks in your list:";
        } else {
            return showList(list);
        }
    }


    /**
     * Displays an error message for when a command is unknown
     *
     */
    public String showCommandErrorMessage() {
        return "Enter a 'todo', 'deadline' or 'event' task, or 'mark' or 'unmark' action";
    }

    /**
     * Displays an error message.
     *
     * @param m The error message to be displayed.
     */
    public String showErrorMessage(String m) {
        return m;
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showByeMessage() {
        return "Quack. Hope to see you again soon!";
    }
}
