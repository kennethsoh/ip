package duck;

import java.util.Stack;

/**
 * Manages task command history
 */
public class TaskHistory {

    private Stack<String> history;

    public TaskHistory() {
        this.history = new Stack<>();
    }

    public String getLast() {
        return history.pop();
    }

    public void push(String command) {
        history.push(command);
    }

    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     * @param s TaskHistory Stack
     * @return Task at the top of the stack
     */
    public String peek(Stack<String> s) {
        return s.peek();
    }

    /**
     * Checks if TaskHistory Stack is empty
     * @param s TaskHistory Stack
     * @return True or False
     */
    public boolean isEmpty(Stack<Task> s) {
        return s.isEmpty();
    }

}
