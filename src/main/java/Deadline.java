public class Deadline extends Task {

    protected String by;

    public Deadline(Boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }


    public String deadline() {
        return this.by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
