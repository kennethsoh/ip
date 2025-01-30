package duck.exception;

/**
 * Exception thrown when a task is created without sufficient details.
 */
public class EmptyDetailsException extends Exception {

    /**
     * Constructs an EmptyDetailsException with the specified error message.
     *
     * @param message The error message describing the missing details.
     */
    public EmptyDetailsException(String message) {
        super(message);
    }
}
