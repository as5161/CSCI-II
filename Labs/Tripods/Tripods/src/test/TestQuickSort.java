package test;

import org.junit.jupiter.api.*;
import sort.QuickSort;
import tripods.Direction;
import tripods.Tripod;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit 5 unit test for the sort.QuickSort.
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestQuickSort {
    /** standard output capturer */
    public final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /** Test the QuickSort.partitionLess method. */
    @Test
    @Order(1)
    public void testPartitionLess() {
        ArrayList<Tripod> tripods = new ArrayList<>();
        Tripod first = new Tripod(1, 3, Direction.WEST, 5);
        tripods.add(first);
        ArrayList<Tripod> less = QuickSort.partitionLess(tripods, first);
        ArrayList<Tripod> expected = new ArrayList<>();
        assertEquals(expected, less);

        Tripod second = new Tripod(2, 4, Direction.NORTH, 11);
        tripods.add(second);
        expected.add(first);
        less = QuickSort.partitionLess(tripods, second);
        assertEquals(expected, less);

        Tripod third = new Tripod(5, 1, Direction.EAST, 8);
        tripods.add(third);
        less = QuickSort.partitionLess(tripods, third);
        assertEquals(expected, less);

        Tripod fourth = new Tripod(3, 7, Direction.SOUTH, 15);
        tripods.add(fourth);
        expected.add(second);
        expected.add(third);
        less = QuickSort.partitionLess(tripods, fourth);
        assertEquals(expected, less);
    }

    /** Test the QuickSort.partitionEqual method. */
    @Test
    @Order(2)
    public void testPartitionEqual() {
        ArrayList<Tripod> tripods = new ArrayList<>();
        Tripod first = new Tripod(1, 3, Direction.WEST, 5);
        tripods.add(first);
        ArrayList<Tripod> equal = QuickSort.partitionEqual(tripods, first);
        ArrayList<Tripod> expected = new ArrayList<>();
        expected.add(first);
        assertEquals(expected, equal);

        Tripod second = new Tripod(2, 0, Direction.SOUTH, 8);
        expected = new ArrayList<>();
        equal = QuickSort.partitionEqual(tripods, second);
        assertEquals(expected, equal);

        tripods.addAll(Arrays.asList(first, second, first, first, second));
        equal = QuickSort.partitionEqual(tripods, second);
        expected.addAll(Arrays.asList(second, second));
        assertEquals(expected, equal);
    }

    /** Test the QuickSort.partitionGreater method. */
    @Test
    @Order(3)
    public void testPartitionGreater() {
        ArrayList<Tripod> tripods = new ArrayList<>();
        Tripod first = new Tripod(1, 3, Direction.WEST, 5);
        tripods.add(first);
        ArrayList<Tripod> greater = QuickSort.partitionGreater(tripods, first);
        ArrayList<Tripod> expected = new ArrayList<>();
        assertEquals(expected, greater);

        Tripod second = new Tripod(2, 4, Direction.NORTH, 11);
        tripods.add(second);
        expected.add(second);
        greater = QuickSort.partitionGreater(tripods, first);
        assertEquals(expected, greater);

        Tripod third = new Tripod(5, 1, Direction.EAST, 8);
        tripods.add(third);
        expected.add(third);
        greater = QuickSort.partitionGreater(tripods, first);
        assertEquals(expected, greater);

        Tripod fourth = new Tripod(4, 1, Direction.SOUTH, 19);
        tripods.add(fourth);
        expected = new ArrayList<>();
        greater = QuickSort.partitionGreater(tripods, fourth);
        assertEquals(expected, greater);
    }

    /** Test the main QuickSort.quickSort method. */
    @Test
    @Order(4)
    public void testQuickSort() {
        // create ten tripods with different sums
        Tripod zero = new Tripod(0, 0, Direction.NORTH, 0);
        Tripod one = new Tripod(0, 0, Direction.NORTH, 1);
        Tripod two = new Tripod(0, 0, Direction.NORTH, 2);
        Tripod three = new Tripod(0, 0, Direction.NORTH, 3);
        Tripod four = new Tripod(0, 0, Direction.NORTH, 4);
        Tripod five = new Tripod(0, 0, Direction.NORTH, 5);
        Tripod six = new Tripod(0, 0, Direction.NORTH, 6);
        Tripod seven = new Tripod(0, 0, Direction.NORTH, 7);
        Tripod eight = new Tripod(0, 0, Direction.NORTH, 8);
        Tripod nine = new Tripod(0, 0, Direction.NORTH, 9);

        // use these various collections of unsorted tripods
        Tripod[][] TRIPODS = {
                {},
                {zero},
                {zero, one},
                {one, zero},
                {zero, one, two},
                {zero, two, one},
                {one, zero, two},
                {one, two, zero},
                {two, zero, one},
                {two, one ,zero},
                {nine, five, two, four, six, three, eight, zero, four, zero, nine, seven}
        };

        // for each collection of tripods
        for (Tripod[] tripods : TRIPODS) {
            // create the list for the users data to sort
            List<Tripod> userData = Arrays.stream(tripods).collect(Collectors.toList());
            // create the list for the expected data to sort
            List<Tripod> expected = Arrays.stream(tripods).collect(Collectors.toList());
            // quick sort the user data
            userData = QuickSort.quickSort((ArrayList<Tripod>)userData);
            // use built in sort to compare against (compares incrementally by tripod sum)
            expected.sort(Comparator.comparingInt(Tripod::sum));
            // show the results of the comparison
            assertEquals(expected, userData);
        }
    }
}
