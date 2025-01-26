public class Event extends Task {
    protected String from;
    protected String to;
    public Event(Boolean isDone, String Description, String from, String to) {
        super(isDone, Description);
        this.from = from;
        this.to = to;
    }

    public String fromTime() {
        return this.from;
    }

    public String toTime() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
