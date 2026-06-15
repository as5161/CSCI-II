package rit.stu;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * JUnit 5.8 tester for a non-generic, String based DLNode.
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDLNode {
    /**
     * standard oautput capturer
     */
    public final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @Order(1)
    public void testDLNodeString() {
        // null <-> A <-> B <-> C <-> D <-> null
        DLNode<String> sNodeA = new DLNode<>("A");
        DLNode<String> sNodeB = new DLNode<>("B", sNodeA, null);
        DLNode<String> sNodeC = new DLNode<>("C", sNodeB, null);
        DLNode<String> sNodeD = new DLNode<>("D", sNodeC, null);

        sNodeA.setNext(sNodeB);
        sNodeB.setNext(sNodeC);
        sNodeC.setNext(sNodeD);

        // traverse from A -> D
        DLNode<String> curr = sNodeA;
        while (curr != null) {
            assertInstanceOf(String.class, curr.getData());   // make sure node data is of type String
            System.out.println(curr);
            curr = curr.getNext();
        }
        String expected = "DLNode{data='A', prev=null, next=B}" + System.lineSeparator() +
                "DLNode{data='B', prev=A, next=C}" + System.lineSeparator() +
                "DLNode{data='C', prev=B, next=D}" + System.lineSeparator() +
                "DLNode{data='D', prev=C, next=null}" + System.lineSeparator();
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    @Order(2)
    public void testDLNodeInteger() {
        // 10
        DLNode<Integer> iNode10 = new DLNode<>(10);
        assertEquals(10, iNode10.getData());
        assertInstanceOf(Integer.class, iNode10.getData());   // make sure generic node is of type Integer
        assertNull(iNode10.getPrev());
        assertNull(iNode10.getNext());
        String expected = "DLNode{data='10', prev=null, next=null}";
        assertEquals(expected, iNode10.toString().trim());
        outputStreamCaptor.reset();
        iNode10.setData(99);
        assertEquals(99, iNode10.getData());
        iNode10.setData(10);

        // 10 <- 20
        DLNode<Integer> iNode20 = new DLNode<>(20, iNode10, null);
        assertEquals(iNode10, iNode20.getPrev());
        assertInstanceOf(Integer.class, iNode20.getData());
        assertNull(iNode20.getNext());
        expected = "DLNode{data='20', prev=10, next=null}";
        assertEquals(expected, iNode20.toString().trim());
        outputStreamCaptor.reset();

        //  10 <- 20 <- 30 ->...
        DLNode<Integer> iNode30 = new DLNode<>(30, iNode20, iNode10);
        assertEquals(30, iNode30.getData());
        assertInstanceOf(Integer.class, iNode30.getData());
        assertEquals(iNode20, iNode30.getPrev());
        assertEquals(iNode10, iNode30.getNext());
        expected = "DLNode{data='30', prev=20, next=10}";
        assertEquals(expected, iNode30.toString().trim());
        outputStreamCaptor.reset();

        // ...<-> 10 <-> 20 <- 30 <->...
        iNode10.setNext(iNode20);
        expected = "DLNode{data='10', prev=null, next=20}";
        assertEquals(expected, iNode10.toString().trim());
        outputStreamCaptor.reset();

        // ...<-> 10 <-> 20 <- 30 <->...
        iNode20.setPrev(iNode10);
        expected = "DLNode{data='20', prev=10, next=null}";
        assertEquals(expected, iNode20.toString().trim());
        outputStreamCaptor.reset();

        // ...<-> 10 <-> 20 <-> 30 <->...
        iNode20.setNext(iNode30);
        expected = "DLNode{data='20', prev=10, next=30}";
        assertEquals(expected, iNode20.toString().trim());
        outputStreamCaptor.reset();
    }
}
