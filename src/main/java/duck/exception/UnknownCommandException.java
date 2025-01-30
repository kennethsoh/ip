package duck.exception;

/**
 * Exception thrown when the user enters an unrecognized command.
 */
public class UnknownCommandException extends Exception {

    /**
     * Constructs an UnknownCommandException with the specified error message.
     *
     * @param message The error message describing the unknown command.
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}
