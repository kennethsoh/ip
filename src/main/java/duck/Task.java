package duck;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task instance.
     *
     * @param isDone Whether the task is completed.
     * @param description The description of the task.
     */
    public Task(Boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is completed, false otherwise.
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the status icon representing task completion.
     *
     * @return "X" if done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Converts the task into a string representation.
     *
     * @return The formatted task string.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
