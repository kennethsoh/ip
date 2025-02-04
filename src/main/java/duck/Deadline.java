package duck;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Represents a Deadline task with a due date.
 */
public class Deadline extends Task {

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Random r = new Random();
        this.by = by.equalsIgnoreCase("now") ? LocalDateTime.now()
                : by.equalsIgnoreCase("later") ? LocalDateTime.now().plusMinutes(r.nextInt(10000))
                : LocalDateTime.parse(by.trim(), formatter);
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
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma")) + ")";
    }
}
