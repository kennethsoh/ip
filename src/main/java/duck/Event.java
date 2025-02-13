package duck;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a start and end time.
 */
public class Event extends Task {
    static final int RANDOM_LIMIT = 10000;
    static final String DATETIME_VIEW_PATTERN = "dd MMM yyyy, hh:mma";
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task.
     *
     * @param isDone Whether the event is completed.
     * @param description The description of the event.
     * @param from The start time of the event in "yyyy-MM-dd HHmm" format.
     * @param to The end time of the event in "yyyy-MM-dd HHmm" format.
     */
    public Event(Boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = setTime(from);
        this.to = setTime(to);
    }


    /**
     * Retrieves the start time of the event.
     *
     * @return The start time as a LocalDateTime object.
     */
    public LocalDateTime fromTime() {
        return this.from;
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time as a LocalDateTime object.
     */
    public LocalDateTime toTime() {
        return this.to;
    }

    /**
     * Converts the Event task into a string representation.
     *
     * @return The formatted event task string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern(DATETIME_VIEW_PATTERN))
                + " to: " + to.format(DateTimeFormatter.ofPattern(DATETIME_VIEW_PATTERN)) + ")";
    }
}
