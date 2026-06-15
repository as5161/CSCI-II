package rit.cs;

/**
 * An interface for a cursor based circular list.
 *
 * @param <E> the data type the collection uses
 * @author RIT CS
 */
public interface CircularList<E> {
    /**
     * Append a new element to the end of the list.
     *
     * @param element the new element to append
     */
    void append(E element);

    /**
     * Returns the size of the list.
     *
     * @return the number of elements in the list
     */
    int size();

    /**
     * Is the cursor pointing to a valid element in the list?
     *
     * @return whether the cursor is valid or not
     */
    boolean valid();

    /**
     * Reset the cursor to point to the head of the list
     * (if one exists), otherwise set it to be off the list (invalid)
     */
    void reset();

    /**
     * Move the cursor forward to the next element.
     *
     * @throws AssertionError if the cursor is off the list,
     * "can't forward cursor, the list is empty!"
     */
    void forward();

    /**
     * Move the cursor backward to the next element.
     *
     * @throws AssertionError if the cursor is off the list,
     * "can't backward cursor, the list is empty!"
     */
    void backward();

    /**
     * Get the element at the cursor position.
     *
     * @throws AssertionError if the cursor is off the list,
     * "can't get, cursor is off the list!"
     * @return the element at the cursor
     */
    E get();

    /**
     * Remove the element at the cursor and then advance it forward
     * to the next element.  In the case the head is removed, the head
     * moves forward to the next element, if possible.
     *
     * @throws AssertionError if the cursor is off the list,
     * "can't removeForward, cursor is off the list!"
     * @return the element at the cursor
     */
    E removeForward();

    /**
     * Remove the element at the cursor and then advance it forward
     * to the next element.  In the case the head is removed, the head
     * moves forward to the next element, if possible.
     *
     * @throws AssertionError if the cursor is off the list,
     * "can't removeBackward, cursor is off the list!"
     * @return the element at the cursor
     */
    E removeBackward();

    /**
     * Returns a string in the format:
     * <pre>
     *     Player &lt;-- CURSOR
     *     Player
     *     Player
     *     ...
     * </pre>
     * Where "&lt;-- CURSOR" points to the player at the cursor.
     * <br>
     * If the list is empty:
     * <pre>
     *     Empty list!
     * </pre>
     *
     * Very important!  Use System.lineSeparator() instead of "\n" when adding
     * a new line!!!
     */
    String toString();
}
