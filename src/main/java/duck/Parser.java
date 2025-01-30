package duck;

import duck.exception.EmptyDetailsException;
import duck.exception.UnknownCommandException;

/**
 * Parses user input and executes the corresponding command.
 */
public class Parser {

    /**
     * Constructs a Parser instance.
     */
    public Parser() {
    }

    /**
     * Parses and executes a command based on user input.
     *
     * @param input The user input command.
     * @param list The task list to modify or display.
     * @param ui The UI instance to interact with the user.
     * @throws UnknownCommandException If the command is not recognized.
     */
    public void parseCommand(String input, TaskList list, Ui ui) throws UnknownCommandException {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        switch (command) {
        case "list":
            list(list, ui);
            break;
        case "mark":
            mark(input, list, ui);
            break;
        case "unmark":
            unmark(input, list, ui);
            break;
        case "todo":
            toDo(input, list, ui);
            break;
        case "deadline":
            deadline(input, list, ui);
            break;
        case "event":
            event(input, list, ui);
            break;
        case "delete":
            delete(input, list, ui);
            break;
        case "bye":
            bye(ui);
            break;
        default:
            unknown(ui);
        }
    }

    /**
     * Displays the task list.
     *
     * @param list The task list to display.
     * @param ui The UI instance to handle output.
     */
    public static void list(TaskList list, Ui ui) {
        ui.showList(list);
    }

    /**
     * Marks a task as completed.
     *
     * @param userInput The user input containing task number.
     * @param list The task list containing the task.
     * @param ui The UI instance to display feedback.
     */
    public void mark(String userInput, TaskList list, Ui ui) {
        try {
            String[] input = userInput.split(" ");
            int number = Integer.parseInt(input[1]);
            if (number <= 0 || number > list.size()) {
                throw new IndexOutOfBoundsException("Task number out of range.");
            }
            Task task = list.get(number - 1);
            task.mark();

            ui.mark(task);
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Unmarks a task as completed.
     * Marks a task as not completed.
     *
     * @param userInput The user input containing task number.
     * @param list The task list containing the task.
     * @param ui The UI instance to display feedback.
     */
    public void unmark(String userInput, TaskList list, Ui ui) {
        try {
            String[] input = userInput.split(" ");
            int number = Integer.parseInt(input[1]);
            if (number <= 0 || number > list.size()) {
                throw new IndexOutOfBoundsException("Task number out of range.");
            }
            Task task = list.get(number - 1);
            task.unmark();

            ui.unmark(task);

        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Adds a ToDo task to the list.
     *
     * @param userInput The user input containing task description.
     * @param list The task list to add the task to.
     * @param ui The UI instance to display feedback.
     */
    public void toDo(String userInput, TaskList list, Ui ui) {
        try {
            String[] parts = userInput.split("todo ", 2);
            String description = parts.length > 1 ? parts[1] : "";
            if (description.isBlank()) {
                throw new EmptyDetailsException("No description provided");
            }
            Task task = new ToDo(false, description);
            list.add(task);

            ui.addTaskMessage(task, list);

        } catch (EmptyDetailsException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param userInput The user input containing task details and deadline.
     * @param list The task list to add the task to.
     * @param ui The UI instance to display feedback.
     */
    public void deadline(String userInput, TaskList list, Ui ui) {
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

            ui.addTaskMessage(task, list);

        } catch (EmptyDetailsException e) {
            ui.showErrorMessage(e.getMessage());
        } catch (Exception e) {
            ui.showErrorMessage("Invalid date format! Use yyyy-MM-dd HHmm (e.g., 2025-01-27 2359).");
        }

    }

    /**
     * Adds an Event task to the list.
     *
     * @param userInput The user input containing event details and time range.
     * @param list The task list to add the task to.
     * @param ui The UI instance to display feedback.
     */
    public void event(String userInput, TaskList list, Ui ui) {
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
            ui.addTaskMessage(task, list);

        } catch (EmptyDetailsException e) {
            ui.showErrorMessage(e.getMessage());
        } catch (Exception e) {
            ui.showErrorMessage("Invalid date format! Use yyyy-MM-dd HHmm (e.g., 2025-01-27 2359).");
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param userInput The user input containing task number.
     * @param list The task list containing the task.
     * @param ui The UI instance to display feedback.
     */
    public void delete(String userInput, TaskList list, Ui ui) {
        try {
            String[] input = userInput.split(" ");
            int number = Integer.parseInt(input[1]);
            if (number <= 0 || number > list.size()) {
                throw new IndexOutOfBoundsException("Task number out of range.");
            }
            Task task = list.remove(number - 1);
            ui.removeTaskMessage(task, list);
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Displays the goodbye message.
     *
     * @param ui The UI instance to display the message.
     */
    public void find(String userInput, TaskList list, Ui ui) {
        // TODO
        try {
            String[] input = userInput.split(" ");
            String keyword = input.length > 1 ? input[1] : "";
            if (keyword.isBlank()) {
                throw new EmptyDetailsException("No keyword provided");
            }
            TaskList match = list.find(keyword);
            ui.findMessage(match);
        } catch (Exception e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    public void bye(Ui ui) {
        ui.showByeMessage();
    }

    /**
     * Displays an error message for unknown commands.
     *
     * @param ui The UI instance to display the message.
     */
    public void unknown(Ui ui) {
        ui.showCommandErrorMessage();
    }

}
