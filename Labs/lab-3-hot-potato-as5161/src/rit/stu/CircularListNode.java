package rit.stu;

import rit.cs.CircularList;

/**
 * @author RIT Aamir Sohail
 * A circular doubly linked list implementation with a cursor.
 *
 * @param <E> the type of elements in the list
 */
public class CircularListNode<E> implements CircularList<E> {
    private DLNode<E> list;
    private DLNode<E> head;
    private DLNode<E> cursor;
    private int size;
    /**
     * Initialize the list to be empty.  This means the head and cursor are both null
     * and the size is 0.
     */
    public CircularListNode() {
        this.list = null;
        this.size = 0;
        this.cursor = null;
    }
    /**
     * Adds an element to the end of the circular list.
     * The cursor is moved to the newly added element.
     *
     * @param element The element to add.
     */
    @Override
    public void append(E element) {
        DLNode <E> addedNode = new DLNode<>(element);
        if (this.size() == 0) {
            head = addedNode;
            addedNode.setPrev(addedNode);
            addedNode.setNext(addedNode);
            cursor = head;
            size++;
        }else{
            DLNode<E> back =  head.getPrev();
            back.setNext(addedNode);
            addedNode.setPrev(back);
            addedNode.setNext(head);
            head.setPrev(addedNode);
            size++;
        }
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return The size of the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the cursor is pointing to a valid element.
     *
     * @return true if the cursor is valid, false otherwise.
     */
    @Override
    public boolean valid() {
        return cursor != null;
    }

    /**
     * Resets the cursor to point at the head of the list.
     */
    @Override
    public void reset() {
            cursor = head;
    }

    /**
     * Moves the cursor forward in the list.
     * Assumes the list is non-empty.
     */
    @Override
    public void forward() {
        assert this.size > 0 : "can't forward cursor, the list is empty!";
        cursor = cursor.getNext();
    }

    /**
     * Moves the cursor backward in the list.
     * Assumes the list is non-empty.
     */
    @Override
    public void backward() {
        assert this.size > 0 : "can't backward cursor, the list is empty!";
        cursor = cursor.getPrev();
    }

    /**
     * Returns the element at the cursor's position.
     *
     * @return The element at the cursor.
     */
    @Override
    public E get() {
        assert this.cursor != null : "can't get, cursor is off the list!";
        return cursor.getData();
    }

    /**
     * Removes the element at the cursor and moves the cursor forward.
     * If the list becomes empty, the cursor and head are set to null.
     *
     * @return The removed element.
     */
    @Override
    public E removeForward() {
        assert valid() : "can't removeForward, cursor is off the list!";
        E atCursor = cursor.getData();
        if (size == 1) {
            head = null;
            cursor = null;
        } else {
            DLNode<E> back = cursor.getPrev();
            DLNode<E> next = cursor.getNext();
            back.setNext(next);
            next.setPrev(back);
            if (cursor == head) {
                head = next;
            }
        cursor = next;
        }
        size--;
        return atCursor;
    }

    /**
     * Removes the element at the cursor and moves the cursor backward.
     * If the list becomes empty, the cursor and head are set to null.
     *
     * @return The removed element.
     */
    @Override
    public E removeBackward() {
        assert valid() : "can't removeBackward, cursor is off the list!";
        E atCursor = cursor.getData();
        if (size == 1){
            head = null;
            cursor = null;
        }else{
        DLNode<E> back = cursor.getPrev();
        DLNode<E> next = cursor.getNext();
        back.setNext(next);
        next.setPrev(back);
        if (cursor == head){
            head = back;
        }
        cursor = back;
        }
        size--;
        return atCursor;
    }

    /**
     * Returns a string representation of the circular list.
     * Displays all elements, marking the cursor's position.
     *
     * @return A string representing the list.
     */
    @Override
    public String toString() {
        String result = "";
        if (size() == 0) {
            result = "Empty list!";
        } else {
            DLNode<E> current = head;
            for (int i = 0; i < size; ++i) {
                result += current.getData();
                if (current == cursor) {
                    result += " <-- CURSOR";
                }
                if (i != size() - 1) {
                    result += System.lineSeparator();
                }
                current = current.getNext();
            }
        }
        return result;
    }
}
