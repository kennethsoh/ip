package duck;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Represents a Deadline task with a due date.
 */
public class Deadline extends Task {
    static final String DATETIME_VIEW_PATTERN = "dd MMM yyyy, hh:mma";
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task.
     *
     * @param isDone Whether the task is completed.
     * @param description The description of the task.
     * @param by The due date of the task in "yyyy-MM-dd HHmm" format.
     */
    public Deadline(Boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = setTime(by);
    }

    @Override
    public void snooze() {
        Random r = new Random();
        this.by = this.by.plusMinutes(1 + r.nextInt(RANDOM_LIMIT));
    }

    /**
     * Retrieves the deadline of the task.
     *
     * @return The deadline as a LocalDateTime object.
     */
    public LocalDateTime deadline() {
        return this.by;
    }

    /**
     * Converts the Deadline task into a string representation.
     *
     * @return The formatted deadline task string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern(DATETIME_VIEW_PATTERN)) + ")";
    }
}
