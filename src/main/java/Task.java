public class Task {
    protected String description;
    protected boolean isDone;

    public Task(Boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean isDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "["+this.getStatusIcon()+"] "+this.getDescription();
    }
}