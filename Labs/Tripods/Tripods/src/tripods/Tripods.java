package tripods;

import sort.QuickSort;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A program that finds the optimal placement of a number of tripods in an
 * N * M grid of numbers.  A tripod can touch three adjacent cells, based on orientation,
 * e.g. a north facing tripod touches the east, south and west cells.
 *
 * The goal is to find the placement of a number of tripods, such that the
 * total sums of the cells that all combined tripods touch is maximum.
 *
 * @author RIT CS
 * @author Aamir Sohail
 */
public class Tripods {
    /** there are 4 corners where a tripod cannot be placed in any direction */
    public final static int CORNERS = 4;
    /** when the number of cells exceeds this number it won't be displayed to standard output */
    public final static int MAX_CELLS_TO_DISPLAY = 10000;

    /**
     * Read the 2-D number grid into a 2 dimension native array. This method should
     * print the following to standard output:
     *
     * Rows: #, Columns: #
     *
     * @param filename the file with the number grid
     * @return the newly created 2-D native array of numbers
     * @throws IOException if the file cannot be found or there is an error reading
     */
    public static int[][] loadGrid(String filename) throws IOException {
        File javaGrid = new File(filename);
        Scanner read = new Scanner(javaGrid);

        int rows = read.nextInt();
        int columns = read.nextInt();
        read.nextLine();

        int [][] grid = new int[rows][columns];

        for (int i = 0; i < rows; i++){
            String[] rowValues = read.nextLine().trim().split(" ");
            for (int j =0; j < columns; j++){
                grid[i][j] = Integer.parseInt(rowValues[j]);
            }
        }
        System.out.println("Rows: " + rows + ", Columns: " + columns);
        return grid;
    }

    /**
     * Get the number of rows in the grid.
     *
     * @param grid the 2-D grid of numbers
     * @return number of rows
     */
    public static int getNumRows(int[][] grid) {
        int rows = grid.length;
        return rows;
    }

    /**
     * Get the number of columns in the grid.
     *
     * @param grid the 2-D grid of numbers
     * @return number of columns
     */
    public static int getNumColumns(int[][] grid) {
        int column = grid[0].length;
        return column;
    }

    /**
     * Get the maximum number of tripods that could be placed on the board.
     * A tripod cannot be placed in any of the four corners of the grid in
     * any orientation.
     *
     * @param grid the 2-D grid of numbers
     * @return the maximum number of tripods that can placed in the grid
     */
    public static int getMaxTripods(int[][] grid) {
        int rows = getNumRows(grid);
        int columns = getNumColumns(grid);
        int max = rows * columns - CORNERS;

        return max;
    }

    /**
     * Display the grid to standard output, only if the number of cells is less
     * than or equal to MAX_CELLS_TO_DISPLAY.  If that size exceeds, print
     * "Too many cells to display" instead.  For example with tripods-3.txt:
     *
     *  0 3 7 9
     *  2 5 1 4
     *  3 3 2 1
     *  4 6 8 4
     *  4 1 2 0
     *
     * @param grid the 2-D grid of numbers
     */
    public static void displayGrid(int[][] grid) {
        int max = getNumRows(grid) * getNumColumns(grid);
        if (max <= MAX_CELLS_TO_DISPLAY) {
            for (int i = 0; i < grid.length; i++) {
                for ( int j=0; j < grid[i].length; j++){
                    System.out.print(grid[i][j]);
                    if (j < grid[i].length-1){
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }else{
                System.out.println("Too many cells to display at this moment");
        }
    }

    /**
     * A spot is valid if the tripod can be placed in the cell at (row, col),
     * taking into account the direction of the tripod and the location of its
     * three legs.
     *
     * @param grid the 2-D grid of numbers
     * @param row tripod's row (centered)
     * @param col tripod's column (centered)
     * @param dir the direction the tripod is facing
     * @return whether the tripod can be placed in the spot or not
     */
    public static boolean isSpotValid(int[][] grid, int row, int col, Direction dir) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (dir == Direction.NORTH) {
            if (row - 1 >= 0 && col - 1 >= 0 && col + 1 < cols) {
                return true;
            }
        } else if (dir == Direction.SOUTH) {
            if (row + 1 < rows && col - 1 >= 0 && col + 1 < cols) {
                return true;
            }
        } else if (dir == Direction.EAST) {
            if (col + 1 < cols && row - 1 >= 0 && row + 1 < rows) {
                return true;
            }
        } else if (dir == Direction.WEST) {
            if (col - 1 >= 0 && row - 1 >= 0 && row + 1 < rows) {
                return true;
            }
        }
        return false;
    }

    /**
     * Given a tripod at a location in the grid and facing a certain direction,
     * sum the numbers in the grid that the three legs of the tripod stand on.
     *
     * @param grid the 2-D grid of numbers
     * @param row tripod's row (centered)
     * @param col tripod's column (centered)
     * @param dir the direction the tripod is facing
     *
     * @rit.pre spot must be valid
     *
     * @return the sum of the tripods legs
     */
    public static int getSum(int[][] grid, int row, int col, Direction dir) {
        int sum = 0;
        if (dir == Direction.NORTH) {
            sum += grid[row - 1][col];
            sum += grid[row][col - 1];
            sum += grid[row][col + 1];
        }
        else if (dir == Direction.SOUTH) {
            sum += grid[row + 1][col];
            sum += grid[row][col - 1];
            sum += grid[row][col + 1];
        }
        else if (dir == Direction.EAST) {
            sum += grid[row][col + 1];
            sum += grid[row - 1][col];
            sum += grid[row + 1][col];
        }
        else if (dir == Direction.WEST) {
            sum += grid[row][col - 1];
            sum += grid[row - 1][col];
            sum += grid[row + 1][col];
        }

        return sum;
    }
    /**
     * Generates a list of tripods with their corresponding sums based on the grid
     * and direction. The tripods are sorted in descending order based on their
     * sums using QuickSort.
     *
     * @param grid the 2-D grid of numbers
     * @return a sorted list of tripods, sorted by the sum of the touched cells
     */
    public static ArrayList<Tripod> generateSums(int [][]grid){
        ArrayList<Tripod> sums = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                for (Direction dir : Direction.values()) {
                    if (isSpotValid(grid, i, j, dir)) {
                        int sum = getSum(grid, i, j, dir);
                        sums.add(new Tripod(i, j, dir, sum));
                    }
                }
            }
        }

        return QuickSort.quickSort(sums);

    }
    /**
     * Displays the optimal placements of the specified number of tripods,
     * sorted by the sum of their touched cells. Each placement is displayed
     * with its location, direction, sum, and the total sum of the tripods.
     *
     * @param tripods the list of sorted tripods
     * @param numTripods the number of tripods to display
     */
    public static void displayOptimalPlacements(ArrayList<Tripod> tripods, int numTripods) {
        Collections.reverse(tripods);
        int collect = 0;
        ArrayList<String> same = new ArrayList<>();
        int placedTripods = 0;
        for (Tripod tripodNow : tripods) {
            String location = tripodNow.row() + "," + tripodNow.col();

            if (!same.contains(location)) {
                same.add(location);
                System.out.println((placedTripods + 1) + ": location: (" + tripodNow.row() + ", " + tripodNow.col() +
                        "), direction: " + tripodNow.dir() + ", sum: " + tripodNow.sum());

                collect += tripodNow.sum();
                placedTripods++;

                if (placedTripods >= numTripods) {
                    break;
                }
            }
        }
        System.out.println("Total sum: " + collect);
    }
        /**
         * The main program takes the file name from the command line.  It then
         * loads the file into a 2-D native array which is then displayed.
         * Next the user is prompted for how many tripods they want for
         * the optimal sum.  If the number of tripods does not exceed the
         * total number that can be placed, the optimal tripods by location
         * are generated, then sorted by descending sum and then displayed
         * to the user.
         *
         * @param args command line arguments (unused)
         */

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Tripods filename");
        } else {
            try {
                int[][] grid = loadGrid(args[0]);
                displayGrid(grid);
                int maxTripods = getMaxTripods(grid);
                Scanner search = new Scanner(System.in);
                System.out.print("Number of tripods: ");
                int tripodsNum = search.nextInt();
                System.out.println("Optimal placement:");
                while (tripodsNum > maxTripods){
                    System.out.println("The number of tripods is greater then the maximum tripods.");
                    System.out.print("Please enter a valid number of tripods: ");
                    tripodsNum = search.nextInt();
                }
                ArrayList<Tripod> correctTripods = generateSums(grid);
                displayOptimalPlacements(correctTripods, tripodsNum);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
