package duck;
public class ToDo extends Task {
    public ToDo(Boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
