public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String Description, String from, String to) {
        super(Description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
