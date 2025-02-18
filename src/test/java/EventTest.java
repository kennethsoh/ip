import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duck.Event;

public class EventTest {

    @Test
    public void createEventTest() {
        Event event = new Event(false, "CS2103 Tutorial", "2025-02-21 1000", "2025-02-21 1100");
        assertEquals("[E][ ] CS2103 Tutorial (from: 21 FEB 2025, 10:00AM to: 21 FEB 2025, 11:00AM)", event.toString());
    }

    @Test
    public void markEventTest() {
        Event event = new Event(false, "CS2103 Lecture", "2025-02-21 1600", "2025-02-21 1800");
        event.mark();
        assertEquals("[E][X] CS2103 Lecture (from: 21 FEB 2025, 04:00PM to: 21 FEB 2025, 06:00PM)", event.toString());
    }

    @Test
    public void unmarkEventTest() {
        Event event = new Event(true, "CS2103 Lecture", "2025-02-21 1600", "2025-02-21 1800");
        event.unmark();
        assertEquals("[E][ ] CS2103 Lecture (from: 21 FEB 2025, 04:00PM to: 21 FEB 2025, 06:00PM)", event.toString());
    }
}
