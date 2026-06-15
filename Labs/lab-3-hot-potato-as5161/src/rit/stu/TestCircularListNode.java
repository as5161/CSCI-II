package rit.stu;

import org.junit.jupiter.api.*;
import rit.cs.CircularList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5.8 tester for CircularListNode
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCircularListNode {
    /** standard output capturer */
    public final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @Order(1)
    public void testAppend() {
        // tests append, isEmpty, size and toString
        CircularList<String> list = new CircularListNode<>();
        assertEquals(0, list.size());

        String expected = "Empty list!";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();

        list.append("A");                               // A*
        assertEquals(1, list.size());
        expected = "A <-- CURSOR";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();

        list.append("B");                               // A* -> B
        assertEquals(2, list.size());
        expected += System.lineSeparator() + "B";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();

        list.append("C");                               // A* -> B -> C
        assertEquals(3, list.size());
        expected += System.lineSeparator() + "C";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();
    }

    @Test
    @Order(2)
    public void testCursorBasics() {
        // tests get, reset, valid
        CircularList<String> list = new CircularListNode<>();
        assertFalse(list.valid());
        AssertionError error = Assertions.assertThrows(AssertionError.class, list::get);
        Assertions.assertEquals("can't get, cursor is off the list!", error.getMessage());

        list.reset();
        assertFalse(list.valid());
        error = Assertions.assertThrows(AssertionError.class, list::get);
        Assertions.assertEquals("can't get, cursor is off the list!", error.getMessage());

        list.append("A");                           // A*
        assertTrue(list.valid());
        assertEquals("A", list.get());

        list.append("B");                          // A* -> B
        assertTrue(list.valid());
        assertEquals("A", list.get());

        String expected = "A <-- CURSOR" + System.lineSeparator() +
                "B";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();
    }

    @Test
    @Order(3)
    public void testCursorMovement() {
        // tests backward, get, forward, reset, valid
        CircularList<String> list = new CircularListNode<>();

        AssertionError error = Assertions.assertThrows(AssertionError.class, list::forward);
        Assertions.assertEquals("can't forward cursor, the list is empty!", error.getMessage());
        error = Assertions.assertThrows(AssertionError.class, list::backward);
        Assertions.assertEquals("can't backward cursor, the list is empty!", error.getMessage());

        list.append("A");                           // A*
        assertEquals("A", list.get());
        list.forward();                             // A*
        assertEquals("A", list.get());
        list.backward();                            // A*
        assertEquals("A", list.get());

        list.append("B");                           // A* -> B
        assertEquals("A", list.get());
        list.forward();                             // A -> B*
        assertEquals("B", list.get());
        String expected = "A" + System.lineSeparator() +
                "B <-- CURSOR";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();

        list.backward();                            // A* -> B
        assertEquals("A", list.get());
        list.backward();                            // A -> B*
        assertEquals("B", list.get());
        list.reset();                               // A* -> B
        assertEquals("A", list.get());

        list.append("C");                           // A* -> B -> C
        list.backward();                            // A -> B -> C*
        assertEquals("C", list.get());
        expected = "A" + System.lineSeparator() +
                "B" + System.lineSeparator() +
                "C <-- CURSOR";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();
        list.forward();                             // A* -> B -> C
        assertEquals("A", list.get());
        list.forward();                             // A -> B* -> C
        assertEquals("B", list.get());
        expected = "A" + System.lineSeparator() +
                "B <-- CURSOR" + System.lineSeparator() +
                "C";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();
        list.forward();                             // A -> B -> C*
        assertEquals("C", list.get());
        list.forward();                             // A* -> B -> C
        assertEquals("A", list.get());
    }

    @Test
    @Order(4)
    public void testRemoveForward() {
        CircularList<String> list = new CircularListNode<>();
        AssertionError error = Assertions.assertThrows(AssertionError.class, list::removeForward);
        Assertions.assertEquals("can't removeForward, cursor is off the list!", error.getMessage());

        list.append("A");                          // A*
        String result = list.removeForward();      // empty, cursor off
        assertEquals("A", result);
        assertEquals(0, list.size());
        assertFalse(list.valid());
        String expected = "Empty list!";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();
        error = Assertions.assertThrows(AssertionError.class, list::get);
        Assertions.assertEquals("can't get, cursor is off the list!", error.getMessage());
        error = Assertions.assertThrows(AssertionError.class, list::forward);
        Assertions.assertEquals("can't forward cursor, the list is empty!", error.getMessage());
        error = Assertions.assertThrows(AssertionError.class, list::backward);
        Assertions.assertEquals("can't backward cursor, the list is empty!", error.getMessage());

        list.append("A");                          // A*
        list.append("B");                          // A* -> B
        result = list.removeForward();             // B*
        assertEquals("A", result);
        assertEquals(1, list.size());
        assertTrue(list.valid());
        assertEquals("B", list.get());
        expected = "B <-- CURSOR";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();
        result = list.removeForward();             // empty, cursor off
        assertEquals("B", result);
        assertEquals(0, list.size());
        assertFalse(list.valid());
        expected = "Empty list!";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();
        error = Assertions.assertThrows(AssertionError.class, list::get);
        Assertions.assertEquals("can't get, cursor is off the list!", error.getMessage());
        error = Assertions.assertThrows(AssertionError.class, list::forward);
        Assertions.assertEquals("can't forward cursor, the list is empty!", error.getMessage());
        error = Assertions.assertThrows(AssertionError.class, list::backward);
        Assertions.assertEquals("can't backward cursor, the list is empty!", error.getMessage());

        list.append("A");                          // A*
        list.append("B");                          // A* -> B
        list.forward();                            // A -> B*
        result = list.removeForward();             // A*
        assertEquals("B", result);
        assertEquals(1, list.size());
        assertTrue(list.valid());
        assertEquals("A", list.get());
        expected = "A <-- CURSOR";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();
        result = list.removeForward();             // empty, cursor off
        assertEquals("A", result);
        assertEquals(0, list.size());
        assertFalse(list.valid());
        error = Assertions.assertThrows(AssertionError.class, list::get);
        Assertions.assertEquals("can't get, cursor is off the list!", error.getMessage());

        list.append("A");                        // A*
        list.append("B");                        // A* -> B
        list.append("C");                        // A* -> B -> C
        list.forward();                          // A -> B* -> C
        result = list.removeForward();           // A -> C*
        assertEquals("B", result);
        assertEquals("C", list.get());
        expected = "A" + System.lineSeparator() +
                "C <-- CURSOR";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();
        list.append("D");                        // A -> C* -> D
        result = list.removeForward();           // A -> D*
        assertEquals("C", result);
        assertEquals("D", list.get());
        expected = "A" + System.lineSeparator() +
                "D <-- CURSOR";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();
        result = list.removeForward();           // A*
        assertEquals("D", result);
        assertEquals("A", list.get());
        result = list.removeForward();           // empty, cursor off
        assertEquals("A", result);
        error = Assertions.assertThrows(AssertionError.class, list::get);
        Assertions.assertEquals("can't get, cursor is off the list!", error.getMessage());
        error = Assertions.assertThrows(AssertionError.class, list::forward);
        Assertions.assertEquals("can't forward cursor, the list is empty!", error.getMessage());
        error = Assertions.assertThrows(AssertionError.class, list::backward);
        Assertions.assertEquals("can't backward cursor, the list is empty!", error.getMessage());

    }

    @Test
    @Order(5)
    public void testRemoveBackward() {
        CircularList<String> list = new CircularListNode<>();
        AssertionError error = Assertions.assertThrows(AssertionError.class, list::removeBackward);
        Assertions.assertEquals("can't removeBackward, cursor is off the list!", error.getMessage());

        list.append("A");                          // A*
        String result = list.removeBackward();     // empty, cursor off
        assertEquals("A", result);
        assertEquals(0, list.size());
        assertFalse(list.valid());
        error = Assertions.assertThrows(AssertionError.class, list::get);
        Assertions.assertEquals("can't get, cursor is off the list!", error.getMessage());
        error = Assertions.assertThrows(AssertionError.class, list::forward);
        Assertions.assertEquals("can't forward cursor, the list is empty!", error.getMessage());
        error = Assertions.assertThrows(AssertionError.class, list::backward);
        Assertions.assertEquals("can't backward cursor, the list is empty!", error.getMessage());

        list.append("A");                          // A*
        list.append("B");                          // A* -> B
        result = list.removeBackward();            // B*
        assertEquals("A", result);
        assertEquals(1, list.size());
        assertTrue(list.valid());
        assertEquals("B", list.get());
        String expected = "B <-- CURSOR";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();
        result = list.removeForward();             // empty, cursor off
        assertEquals("B", result);
        assertEquals(0, list.size());
        assertFalse(list.valid());
        expected = "Empty list!";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();
        error = Assertions.assertThrows(AssertionError.class, list::get);
        Assertions.assertEquals("can't get, cursor is off the list!", error.getMessage());

        list.append("A");                          // A*
        list.append("B");                          // A* -> B
        list.backward();                           // A -> B*
        result = list.removeBackward();            // A*
        assertEquals("B", result);
        assertEquals(1, list.size());
        assertTrue(list.valid());
        assertEquals("A", list.get());
        result = list.removeForward();             // empty, cursor off
        assertEquals("A", result);
        assertEquals(0, list.size());
        assertFalse(list.valid());
        error = Assertions.assertThrows(AssertionError.class, list::get);
        Assertions.assertEquals("can't get, cursor is off the list!", error.getMessage());

        list.append("A");                         // A*
        list.append("B");                         // A* -> B
        list.append("C");                         // A* -> B -> C
        list.backward();                          // A -> B -> C*
        result = list.removeBackward();           // A -> B*
        assertEquals("C", result);
        assertEquals("B", list.get());
        list.append("D");                         // A -> B* -> D
        expected = "A" + System.lineSeparator() +
                "B <-- CURSOR" + System.lineSeparator() +
                "D";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();

        result = list.removeBackward();           // A* -> D
        assertEquals("B", result);
        assertEquals("A", list.get());
        expected = "A <-- CURSOR" + System.lineSeparator() +
                "D";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();
        result = list.removeBackward();           // D*
        assertEquals("A", result);
        assertEquals("D", list.get());
        expected = "D <-- CURSOR";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();
        result = list.removeForward();            // empty, cursor off
        assertEquals("D", result);
        expected = "Empty list!";
        assertEquals(expected, list.toString().trim());
        outputStreamCaptor.reset();
        error = Assertions.assertThrows(AssertionError.class, list::get);
        Assertions.assertEquals("can't get, cursor is off the list!", error.getMessage());
        error = Assertions.assertThrows(AssertionError.class, list::forward);
        Assertions.assertEquals("can't forward cursor, the list is empty!", error.getMessage());
        error = Assertions.assertThrows(AssertionError.class, list::backward);
        Assertions.assertEquals("can't backward cursor, the list is empty!", error.getMessage());
    }
}
