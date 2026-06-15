package test;

import org.junit.jupiter.api.*;
import tripods.Direction;
import tripods.Tripods;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 unit test for the Tripods.
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTripods {
    /** standard output capturer */
    public final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @Order(1)
    public void testLoadAndDisplay() {
        try {
            int[][] grid = Tripods.loadGrid("tripods-5.txt");
            String expected = "Rows: 7, Columns: 11";
            assertEquals(expected, outputStreamCaptor.toString()
                    .trim());
            outputStreamCaptor.reset();

            expected = "3 8 1 9 9 3 1 6 2 4 6" + System.lineSeparator() +
                    "9 2 2 6 7 3 4 5 1 2 2" + System.lineSeparator() +
                    "4 2 1 1 0 3 0 2 2 5 9" + System.lineSeparator() +
                    "2 9 9 6 5 8 0 5 9 7 3" + System.lineSeparator() +
                    "8 8 7 7 7 0 8 3 9 0 8" + System.lineSeparator() +
                    "4 5 7 8 3 3 1 1 6 3 2" + System.lineSeparator() +
                    "5 7 3 7 8 1 3 1 2 1 4";
            Tripods.displayGrid(grid);
            assertEquals(expected, outputStreamCaptor.toString()
                    .trim());
            outputStreamCaptor.reset();
        } catch (IOException e) {
            System.err.println("Should not happen! ");
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    public void testDimensions() {
        try {
            int[][] grid = Tripods.loadGrid("tripods-5.txt");
            int rows = Tripods.getNumRows(grid);
            assertEquals(7, rows);
            int cols = Tripods.getNumColumns(grid);
            assertEquals(11, cols);
            int max = Tripods.getMaxTripods(grid);
            assertEquals(73, max);
        } catch (IOException e) {
            System.err.println("Should not happen! ");
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    public void testSpotValid() {
        try {
            int[][] grid = Tripods.loadGrid("tripods-1.txt");

            assertFalse(Tripods.isSpotValid(grid,0, 0, Direction.NORTH));
            assertFalse(Tripods.isSpotValid(grid,0, 0, Direction.SOUTH));
            assertFalse(Tripods.isSpotValid(grid,0, 0, Direction.EAST));
            assertFalse(Tripods.isSpotValid(grid,0, 0, Direction.WEST));

            assertFalse(Tripods.isSpotValid(grid,0, 1, Direction.NORTH));
            assertTrue(Tripods.isSpotValid(grid,0, 1, Direction.SOUTH));
            assertFalse(Tripods.isSpotValid(grid,0, 1, Direction.EAST));
            assertFalse(Tripods.isSpotValid(grid,0, 1, Direction.WEST));

            assertFalse(Tripods.isSpotValid(grid,0, 2, Direction.NORTH));
            assertFalse(Tripods.isSpotValid(grid,0, 2, Direction.SOUTH));
            assertFalse(Tripods.isSpotValid(grid,0, 2, Direction.EAST));
            assertFalse(Tripods.isSpotValid(grid,0, 2, Direction.WEST));

            assertFalse(Tripods.isSpotValid(grid, 1, 0, Direction.NORTH));
            assertFalse(Tripods.isSpotValid(grid, 1, 0, Direction.SOUTH));
            assertTrue(Tripods.isSpotValid(grid, 1, 0, Direction.EAST));
            assertFalse(Tripods.isSpotValid(grid, 1, 0, Direction.WEST));
;
            assertTrue(Tripods.isSpotValid(grid, 1, 1, Direction.NORTH));
            assertTrue(Tripods.isSpotValid(grid, 1, 1, Direction.SOUTH));
            assertTrue(Tripods.isSpotValid(grid, 1, 1, Direction.EAST));
            assertTrue(Tripods.isSpotValid(grid, 1, 1, Direction.WEST));

            assertFalse(Tripods.isSpotValid(grid, 1, 2, Direction.NORTH));
            assertFalse(Tripods.isSpotValid(grid, 1, 2, Direction.SOUTH));
            assertFalse(Tripods.isSpotValid(grid, 1, 2, Direction.EAST));
            assertTrue(Tripods.isSpotValid(grid, 1, 2, Direction.WEST));

            assertFalse(Tripods.isSpotValid(grid,2, 0, Direction.NORTH));
            assertFalse(Tripods.isSpotValid(grid,2, 0, Direction.SOUTH));
            assertFalse(Tripods.isSpotValid(grid,2, 0, Direction.EAST));
            assertFalse(Tripods.isSpotValid(grid,2, 0, Direction.WEST));

            assertTrue(Tripods.isSpotValid(grid,2, 1, Direction.NORTH));
            assertFalse(Tripods.isSpotValid(grid,2, 1, Direction.SOUTH));
            assertFalse(Tripods.isSpotValid(grid,2, 1, Direction.EAST));
            assertFalse(Tripods.isSpotValid(grid,2, 1, Direction.WEST));

            assertFalse(Tripods.isSpotValid(grid,2, 2, Direction.NORTH));
            assertFalse(Tripods.isSpotValid(grid,2, 2, Direction.SOUTH));
            assertFalse(Tripods.isSpotValid(grid,2, 2, Direction.EAST));
            assertFalse(Tripods.isSpotValid(grid,2, 2, Direction.WEST));
        } catch (IOException e) {
            System.err.println("Should not happen! ");
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    public void testSum() {
        try {
            int[][] grid = Tripods.loadGrid("tripods-3.txt");

            assertEquals(12, Tripods.getSum(grid, 0, 1, Direction.SOUTH));

            assertEquals(13, Tripods.getSum(grid, 0, 2, Direction.SOUTH));

            assertEquals(8, Tripods.getSum(grid, 1, 0, Direction.EAST));

            assertEquals(6, Tripods.getSum(grid, 1, 1, Direction.NORTH));
            assertEquals(6, Tripods.getSum(grid, 1, 1, Direction.SOUTH));
            assertEquals(7, Tripods.getSum(grid, 1, 1, Direction.EAST));
            assertEquals(8, Tripods.getSum(grid, 1, 1, Direction.WEST));

            assertEquals(12, Tripods.getSum(grid, 3, 2, Direction.NORTH));
            assertEquals(12, Tripods.getSum(grid, 3, 2, Direction.SOUTH));
            assertEquals(8, Tripods.getSum(grid, 3, 2, Direction.EAST));
            assertEquals(10, Tripods.getSum(grid, 3, 2, Direction.WEST));

            assertEquals(9, Tripods.getSum(grid, 3, 3, Direction.WEST));

            assertEquals(9, Tripods.getSum(grid, 4, 2, Direction.NORTH));
        } catch (IOException e) {
            System.err.println("Should not happen! ");
            e.printStackTrace();
        }
    }
}
