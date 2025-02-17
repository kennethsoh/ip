import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duck.Deadline;

public class DeadlineTest {

    @Test
    public void createDeadlineTest() {
        Deadline deadline = new Deadline(false, "Submit assignment", "2025-02-20 2359");
        assertEquals("[D][ ] Submit assignment (by: 20 Feb 2025, 11:59pm)", deadline.toString());
    }

    @Test
    public void markDeadlineTest() {
        Deadline deadline = new Deadline(false, "Submit assignment", "2025-02-20 2359");
        deadline.mark();
        assertEquals("[D][X] Submit assignment (by: 20 Feb 2025, 11:59pm)", deadline.toString());
    }

    @Test
    public void snoozeDeadlineTest() {
        Deadline deadline = new Deadline(false, "Submit assignment", "2025-02-20 2359");
        deadline.snooze();
        assertTrue(deadline.deadline().isAfter(deadline.setTime("2025-02-20 2359"))); // Ensure it moves to a later time
    }
}
