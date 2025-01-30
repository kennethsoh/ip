package duck;

/**
 * Represents a simple ToDo task.
 */

public class ToDo extends Task {

    /**
     * Constructs a ToDo task.
     *
     * @param isDone Whether the task is completed.
     * @param description The description of the task.
     */
    public ToDo (Boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Converts the ToDo task into a string representation.
     *
     * @return The formatted ToDo task string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
