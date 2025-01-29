package duck;

import duck.exception.EmptyDetailsException;
import duck.exception.UnknownCommandException;

public class Parser {
    public Parser() {
    }

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

    public static void list(TaskList list, Ui ui) {
        ui.showList(list);
    }
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
    public void delete(String userInput, TaskList list, Ui ui) {
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
            ui.showErrorMessage(e.getMessage());
        }
    }
    public void bye(Ui ui) {
        ui.showByeMessage();
    }

    public void unknown(Ui ui) {
        ui.showCommandErrorMessage();
    }

}
