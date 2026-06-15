package rit.stu;

/**
 * A doubly linked node that holds a String as its data type.
 *
 * @author RIT CS
 * @author Aamir Sohail
 */
public class DLNode<E> {
    /** User data */
    private E data;

    /** Previous node link */
    private DLNode<E> prev;

    /** Next node link */
    private DLNode<E> next;

    /**
     * Create a new node with no predecessor or successor.
     * @param data the user data to be stored
     */
    public DLNode(E data) {
        this(data, null, null);
    }

    /**
     * Construct a new node with pointers to the previous and next node.
     * @param data The user data to be stored
     * @param prev The link to the previous node (null if none)
     * @param next The link to the next node (null if none)
     */
    public DLNode(E data, DLNode<E> prev, DLNode<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Get the predecessor node.
     * @return the previous node (null if none)
     */
    public DLNode<E> getPrev() {
        return this.prev;
    }

    /**
     * Get the successor node.
     * @return the next node (null if none)
     */
    public DLNode<E> getNext() {
        return this.next;
    }

    /**
     * Get the Node's data.
     * @return the user data
     */
    public E getData() {
        return this.data;
    }

    /**
     * Change the node's successor.
     * @param prev the node's new next link
     */
    public void setPrev(DLNode<E> prev) {
        this.prev = prev;
    }

    /**
     * Change the node's successor.
     * @param next the node's new next link
     */
    public void setNext(DLNode<E> next) {
        this.next = next;
    }

    /**
     * Change the node's data.
     * @param data the node's new data
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Returns a string representation of the node in the format:
     * <pre>
     *     DLNode{data=XXX, prev=YYY, next=YYY}
     * </pre>
     * <ul>
     *     <li>
     *         XXX: The data element of the current node.
     *     </li>
     *     <li>
     *         YYY: The data element of the previous node.  If no node, "null".
     *     </li>
     *     <li>
     *         ZZZ: The data element of the next node.  If no node, "null".
     *     </li>
     * </ul>
     * @return the string described above
     */
    @Override
    public String toString() {
        return "DLNode{" +
                "data='" + this.data + '\'' +
                ", prev=" + (this.prev != null ? this.prev.getData() : "null") +
                ", next=" + (this.next != null ? this.next.getData() : "null") +
                '}';
    }
}