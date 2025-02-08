package duck;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Represents an Event task with a start and end time.
 */
public class Event extends Task {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Random r = new Random();
        this.from = from.equalsIgnoreCase("now") ? LocalDateTime.now()
                : from.equalsIgnoreCase("later") ? LocalDateTime.now().plusMinutes(r.nextInt(10000))
                : LocalDateTime.parse(from.trim(), formatter);
        this.to = to.equalsIgnoreCase("now") ? LocalDateTime.now()
                : to.equalsIgnoreCase("later") ? LocalDateTime.now().plusMinutes(r.nextInt(10000))
                : LocalDateTime.parse(to.trim(), formatter);
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
                + " (from: " + from.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma")) + ")";
    }
}
