import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(Boolean isDone, String description, String by) {
        super(isDone, description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by.trim(), formatter);
    }


    public LocalDateTime deadline() {
        return this.by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma")) + ")";
    }
}
