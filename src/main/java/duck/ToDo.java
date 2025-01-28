package duck;
public class ToDo extends Task {
    public ToDo (Boolean isDone, String Description) {
        super(isDone, Description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
