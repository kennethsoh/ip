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

    private String[] parseInput(String input, String regex) {
        return input.split(regex);
    }

    private String[] parseInput(String input, String regex, int numOfParts) {
        return input.split(regex, numOfParts);
    }

    /**
     * Parses and executes a command based on user input.
     *
     * @param input The user input command.
     * @param list The task list to modify or display.
     * @param ui The UI instance to interact with the user.
     * @throws UnknownCommandException If the command is not recognized.
     */
    public String parseCommand(String input, TaskList list, Ui ui, TaskHistory history) throws UnknownCommandException {
        // TODO: CHECK if should push to Stack if input is 'undo'
        String command = parseInput(input, " ", 2)[0].toLowerCase();
        switch (command) {
        case "list":
            return list(list, ui);
        case "mark":
            history.push(input);
            return mark(input, list, ui);
        case "unmark":
            return unmark(input, list, ui);
        case "todo":
            return toDo(input, list, ui);
        case "deadline":
            return deadline(input, list, ui);
        case "event":
            return event(input, list, ui);
        case "delete":
            return delete(input, list, ui);
        case "find":
            return find(input, list, ui);
        case "undo":
            return undo(list, ui, history);
        case "snooze":
            return snooze(input, list, ui);
        case "bye":
            return bye(ui);
        default:
            return unknown(ui);
        }
    }

    /**
     * Displays the task list.
     *
     * @param list The task list to display.
     * @param ui The UI instance to handle output.
     */
    public static String list(TaskList list, Ui ui) {
        return ui.showList(list);
    }

    /**
     * Marks a task as completed.
     *
     * @param userInput The user input containing task number.
     * @param list The task list containing the task.
     * @param ui The UI instance to display feedback.
     */
    public String mark(String userInput, TaskList list, Ui ui) {
        try {
            int number = Integer.parseInt(parseInput(userInput, " ")[1]);
            if (number <= 0 || number > list.size()) {
                throw new IndexOutOfBoundsException("Task number out of range.");
            }
            Task task = list.get(number - 1);
            task.mark();

            return ui.mark(task);
        } catch (IndexOutOfBoundsException e) {
            return ui.showErrorMessage(e.getMessage());
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
    public String unmark(String userInput, TaskList list, Ui ui) {
        try {
            int number = Integer.parseInt(parseInput(userInput, " ")[1]);
            if (number <= 0 || number > list.size()) {
                throw new IndexOutOfBoundsException("Task number out of range.");
            }
            Task task = list.get(number - 1);
            task.unmark();

            return ui.unmark(task);

        } catch (IndexOutOfBoundsException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Adds a ToDo task to the list.
     *
     * @param userInput The user input containing task description.
     * @param list The task list to add the task to.
     * @param ui The UI instance to display feedback.
     */
    public String toDo(String userInput, TaskList list, Ui ui) {
        try {
            String[] parts = parseInput(userInput, "todo", 2);
            String description = parts.length > 1 ? parts[1] : "";
            if (description.isBlank()) {
                throw new EmptyDetailsException("No description provided");
            }
            Task task = new ToDo(false, description);
            list.add(task);

            return ui.addTaskMessage(task, list);

        } catch (EmptyDetailsException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param userInput The user input containing task details and deadline.
     * @param list The task list to add the task to.
     * @param ui The UI instance to display feedback.
     */
    public String deadline(String userInput, TaskList list, Ui ui) {
        try {
            String[] parts = parseInput(userInput, "/by", 2);
            String[] firstPart = parseInput(parts[0], "deadline", 2);
            String description = firstPart.length > 1 ? firstPart[1] : "";
            String by = parts.length > 1 ? parts[1].trim() : "";
            if (description.isBlank() || by.isBlank()) {
                throw new EmptyDetailsException("No description or 'by' deadline time provided");
            }
            Task task = new Deadline(false, description, by);
            list.add(task);

            return ui.addTaskMessage(task, list);

        } catch (EmptyDetailsException e) {
            return ui.showErrorMessage(e.getMessage());
        } catch (Exception e) {
            return ui.showErrorMessage("Invalid date format! Use yyyy-MM-dd HHmm (e.g., 2025-01-27 2359).");
        }

    }

    /**
     * Adds an Event task to the list.
     *
     * @param userInput The user input containing event details and time range.
     * @param list The task list to add the task to.
     * @param ui The UI instance to display feedback.
     */
    public String event(String userInput, TaskList list, Ui ui) {
        try {
            String[] parts = parseInput(userInput, "/from | /to");
            String[] firstPart = parseInput(parts[0], "event", 2);
            String description = firstPart.length > 1 ? firstPart[1] : "";
            String from = parts.length > 1 ? parts[1].trim() : "";
            String to = parts.length > 2 ? parts[2].trim() : "";
            if (description.isBlank() || from.isBlank() || to.isBlank()) {
                throw new EmptyDetailsException("No description or 'from' or 'to' time provided");
            }
            Task task = new Event(false, description, from, to);
            list.add(task);
            return ui.addTaskMessage(task, list);

        } catch (EmptyDetailsException e) {
            return ui.showErrorMessage(e.getMessage());
        } catch (Exception e) {
            return ui.showErrorMessage("Invalid date format! Use yyyy-MM-dd HHmm (e.g., 2025-01-27 2359).");
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param userInput The user input containing task number.
     * @param list The task list containing the task.
     * @param ui The UI instance to display feedback.
     */
    public String delete(String userInput, TaskList list, Ui ui) {
        try {
            int number = Integer.parseInt(parseInput(userInput, " ")[1]);
            if (number <= 0 || number > list.size()) {
                throw new IndexOutOfBoundsException("Task number out of range.");
            }
            Task task = list.remove(number - 1);
            return ui.removeTaskMessage(task, list);
        } catch (IndexOutOfBoundsException e) {
            return ui.showErrorMessage(e.getMessage());
        } catch (Exception e) {
            return ui.showErrorMessage("Invalid Input, Try again!");
        }
    }

    /**
     * Displays a matching list of tasks based on keyword
     * The keyword is the search term entered by the user after the command 'find'
     *
     * @param userInput The user input containing keyword
     * @param list The task list containing the task.
     * @param ui The UI instance to display feedback.
     */
    public String find(String userInput, TaskList list, Ui ui) {
        try {
            String keyword = userInput.substring(userInput.indexOf(" ") + 1).trim();
            if (keyword.isBlank()) {
                throw new EmptyDetailsException("No keyword provided");
            }
            TaskList match = list.find(keyword);
            return ui.findMessage(match);
        } catch (Exception e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }


    /**
     * Undoes last command based on Task History
     *
     * @param history TaskHistory stack
     */
    public String undo(TaskList list, Ui ui, TaskHistory history) throws UnknownCommandException {
        String prevCommand = history.getLast();
        return parseCommand(prevCommand, list, ui, history);
    }

    /**
     * Sets the deadline 'by' time to a random time in the future
     * @param userInput
     * @param list
     * @param ui
     */
    public String snooze(String userInput, TaskList list, Ui ui) {
        try {
            int number = Integer.parseInt(parseInput(userInput, " ")[1]);
            if (number <= 0 || number > list.size()) {
                throw new IndexOutOfBoundsException("Task number out of range.");
            }
            Task task = list.get(number - 1);
            if (!(task instanceof Deadline)) {
                return ui.showErrorMessage("Snooze only applicable to deadline");
            }
            task.snooze();
            return ui.snoozeMessage(task);

        } catch (IndexOutOfBoundsException e) {
            return ui.showErrorMessage(e.getMessage());
        }

    }


    /**
     * Displays the goodbye message.
     *
     * @param ui The UI instance to display the message.
     */
    public String bye(Ui ui) {
        return ui.showByeMessage();
    }

    /**
     * Displays an error message for unknown commands.
     *
     * @param ui The UI instance to display the message.
     */
    public String unknown(Ui ui) {
        return ui.showCommandErrorMessage();
    }

}
