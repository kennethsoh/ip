package duck;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    public Event(Boolean isDone, String description, String from, String to) {
        super(isDone, description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from.trim(), formatter);
        this.to = LocalDateTime.parse(to.trim(), formatter);
    }

    public LocalDateTime fromTime() {
        return this.from;
    }

    public LocalDateTime toTime() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma")) + ")";
    }
}
