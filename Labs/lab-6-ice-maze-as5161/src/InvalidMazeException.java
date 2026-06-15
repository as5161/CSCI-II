/**
 * Exception thrown when an invalid maze file is encountered.
 * This exception is used to signal errors related to the maze file,
 * such as malformed content or incorrect data.
 */
public class InvalidMazeException extends Exception {

    /**
     * Constructs a new InvalidMazeException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public InvalidMazeException(String message) {
        super(message);
    }
}
