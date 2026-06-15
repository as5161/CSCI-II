package magnets;

import backtracking.Configuration;
import test.IMagnetTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class MagnetsConfig implements Configuration, IMagnetTest {
    private final static char EMPTY = '.';
    private final static char BLANK = 'X';
    private final static char POS = '+';
    private final static char NEG = '-';
    private final static char LEFT = 'L';
    private final static char RIGHT = 'R';
    private final static char TOP = 'T';
    private final static char BOTTOM = 'B';
    private final static int IGNORED = -1;
    private final int[] posRowCounts;
    private final int[] posColCounts;
    private final int[] negRowCounts;
    private final int[] negColCounts;
    private final char[][] pairGrid;
    private final int rows;
    private final int cols;
    private final char[][] board;
    private final int cursorRow;
    private final int cursorCol;
    /**
     * Constructor that initializes the MagnetsConfig object by reading the configuration
     * from a given file. This file contains the dimensions of the grid, counts for positive
     * and negative values, and the pair grid.
     *
     * @param filename The name of the configuration file.
     * @throws IOException If there is an error reading the file.
     */
    public MagnetsConfig(String filename) throws IOException {
        System.out.println("File: " + filename);

        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            String[] dimensions = in.readLine().trim().split("\\s+");
            this.rows = Integer.parseInt(dimensions[0]);
            this.cols = Integer.parseInt(dimensions[1]);
            System.out.println("Rows: " + rows + ", Columns: " + cols);
            this.posRowCounts = new int[rows];
            String[] rowTokens = in.readLine().trim().split("\\s+");
            for (int i = 0; i < rows; i++) {
                this.posRowCounts[i] = Integer.parseInt(rowTokens[i]);
            }
            this.posColCounts = new int[cols];
            String[] colTokens = in.readLine().trim().split("\\s+");
            for (int i = 0; i < cols; i++) {
                this.posColCounts[i] = Integer.parseInt(colTokens[i]);
            }
            this.negRowCounts = new int[rows];
            rowTokens = in.readLine().trim().split("\\s+");
            for (int i = 0; i < rows; i++) {
                this.negRowCounts[i] = Integer.parseInt(rowTokens[i]);
            }
            this.negColCounts = new int[cols];
            colTokens = in.readLine().trim().split("\\s+");
            for (int i = 0; i < cols; i++) {
                this.negColCounts[i] = Integer.parseInt(colTokens[i]);
            }
            this.pairGrid = new char[rows][cols];
            for (int row = 0; row < rows; row++) {
                String[] pairs = in.readLine().trim().split("\\s+");
                for (int col = 0; col < cols; col++) {
                    this.pairGrid[row][col] = pairs[col].charAt(0);
                }
            }
            System.out.println("Pairs:");
            for (char[] row : this.pairGrid) {
                for (char c : row) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }
            this.board = new char[rows][cols];
            for (char[] row : this.board) Arrays.fill(row, EMPTY);

            this.cursorRow = 0;
            this.cursorCol = -1;
            System.out.println("Initial config:");
            System.out.println(this);
        }
    }
    /**
     * Private constructor that creates a new MagnetsConfig instance by copying the state
     * from another MagnetsConfig object and placing a new value at the next available position.
     *
     * @param other The MagnetsConfig object to copy from.
     * @param val The value to place in the next available position.
     */
    private MagnetsConfig(MagnetsConfig other, char val) {
        this.rows = other.rows;
        this.cols = other.cols;
        this.posRowCounts = Arrays.copyOf(other.posRowCounts, other.posRowCounts.length);
        this.posColCounts = Arrays.copyOf(other.posColCounts, other.posColCounts.length);
        this.negRowCounts = Arrays.copyOf(other.negRowCounts, other.negRowCounts.length);
        this.negColCounts = Arrays.copyOf(other.negColCounts, other.negColCounts.length);
        this.pairGrid = new char[other.rows][other.cols];
        for (int i = 0; i < other.rows; ++i) {
            System.arraycopy(other.pairGrid[i], 0, this.pairGrid[i], 0, other.cols);
        }
        this.board = new char[other.rows][other.cols];
        for (int i = 0; i < other.rows; ++i) {
            System.arraycopy(other.board[i], 0, this.board[i], 0, other.cols);
        }
        int nextRow = other.cursorRow;
        int nextCol = other.cursorCol + 1;
        if (nextCol >= cols) {
            nextCol = 0;
            nextRow++;
        }
        this.board[nextRow][nextCol] = val;
        this.cursorRow = nextRow;
        this.cursorCol = nextCol;
    }
    /**
     * Returns a list of successor configurations that can be generated from the current configuration.
     * Successors are generated by placing 'X', '+', or '-' in the next available position.
     *
     * @return A list of possible successor configurations.
     */
    public List<Configuration> getSuccessors() {
        List<Configuration> successors = new ArrayList<>();
        int nextRow = cursorRow;
        int nextCol = cursorCol + 1;
        if (nextCol >= cols) {
            nextCol = 0;
            nextRow++;
        }
        if (nextRow >= rows) return successors;
        // Generate successors in the order X, +, -
        for (char value : new char[]{'X', '+', '-'}) {
            successors.add(new MagnetsConfig(this, value));
        }
        return successors;
    }
    /**
     * Validates the current configuration by checking the following conditions:
     * 1. Pair Validation for all pair types (LEFT-RIGHT, TOP-BOTTOM).
     * 2. Polarity Check (ensures adjacent cells do not have the same polarity).
     * 3. Row and Column Counts (checks if the row and column counts match the constraints).
     *
     * @return true if the configuration is valid, false otherwise.
     */
    @Override
    public boolean isValid() {
        int currentRow = this.cursorRow;
        int currentCol = this.cursorCol;
        char currentVal = board[currentRow][currentCol];
        char currentPair = pairGrid[currentRow][currentCol];
        if (currentPair == LEFT) {
            if (currentCol + 1 >= cols || pairGrid[currentRow][currentCol + 1] != RIGHT) return false;
            char rightVal = board[currentRow][currentCol + 1];
            if (rightVal != EMPTY) {
                if (!((currentVal == BLANK && rightVal == BLANK) ||
                        (currentVal == POS && rightVal == NEG) ||
                        (currentVal == NEG && rightVal == POS))) {
                    return false;
                }
            }
        } else if (currentPair == RIGHT) {
            if (currentCol - 1 < 0 || pairGrid[currentRow][currentCol - 1] != LEFT) return false;
            char leftVal = board[currentRow][currentCol - 1];
            if (leftVal != EMPTY) {
                if (!((leftVal == BLANK && currentVal == BLANK) ||
                        (leftVal == POS && currentVal == NEG) ||
                        (leftVal == NEG && currentVal == POS))) {
                    return false;
                }
            }
        } else if (currentPair == TOP) {
            if (currentRow + 1 >= rows || pairGrid[currentRow + 1][currentCol] != BOTTOM) return false;
            char bottomVal = board[currentRow + 1][currentCol];
            if (bottomVal != EMPTY) {
                if (!((currentVal == BLANK && bottomVal == BLANK) ||
                        (currentVal == POS && bottomVal == NEG) ||
                        (currentVal == NEG && bottomVal == POS))) {
                    return false;
                }
            }
        } else if (currentPair == BOTTOM) {
            if (currentRow - 1 < 0 || pairGrid[currentRow - 1][currentCol] != TOP) return false;
            char topVal = board[currentRow - 1][currentCol];
            if (topVal != EMPTY) {
                if (!((topVal == BLANK && currentVal == BLANK) ||
                        (topVal == POS && currentVal == NEG) ||
                        (topVal == NEG && currentVal == POS))) {
                    return false;
                }
            }
        }
        if (currentVal == POS || currentVal == NEG) {
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right
            for (int[] dir : directions) {
                int adjRow = currentRow + dir[0];
                int adjCol = currentCol + dir[1];
                if (adjRow >= 0 && adjRow < rows && adjCol >= 0 && adjCol < cols) {
                    if (board[adjRow][adjCol] == currentVal) {
                        return false;
                    }
                }
            }
        }
        boolean rowComplete = true;
        for (int col = 0; col < cols; col++) {
            if (board[currentRow][col] == EMPTY) {
                rowComplete = false;
                break;
            }
        }
        if (rowComplete) {
            int posCount = 0, negCount = 0;
            for (int col = 0; col < cols; col++) {
                char val = board[currentRow][col];
                if (val == POS) posCount++;
                else if (val == NEG) negCount++;
            }
            if ((posRowCounts[currentRow] != IGNORED && posCount != posRowCounts[currentRow]) ||
                    (negRowCounts[currentRow] != IGNORED && negCount != negRowCounts[currentRow])) {
                return false;
            }
        }
        boolean colComplete = true;
        for (int row = 0; row < rows; row++) {
            if (board[row][currentCol] == EMPTY) {
                colComplete = false;
                break;
            }
        }
        if (colComplete) {
            int posCount = 0, negCount = 0;
            for (int row = 0; row < rows; row++) {
                char val = board[row][currentCol];
                if (val == POS) posCount++;
                else if (val == NEG) negCount++;
            }
            if ((posColCounts[currentCol] != IGNORED && posCount != posColCounts[currentCol]) ||
                    (negColCounts[currentCol] != IGNORED && negCount != negColCounts[currentCol])) {
                return false;
            }
        }

        return true;
    }
    @Override
    public boolean isGoal() {
        return cursorRow == rows - 1 && cursorCol == cols - 1;
    }
    /**
     * Checks whether the current configuration is the goal state, which is when the cursor
     * reaches the bottom-right corner of the grid.
     *
     * @return true if the cursor is at the goal position, false otherwise.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("+ ");
        for (int col = 0; col < cols; ++col) {
            result.append(posColCounts[col] != IGNORED ? posColCounts[col] : " ");
            result.append(col < cols - 1 ? " " : "");
        }
        result.append("\n  ");
        result.append("-".repeat(Math.max(0, cols * 2 - 1)));
        result.append("\n");
        for (int row = 0; row < rows; ++row) {
            result.append(posRowCounts[row] != IGNORED ? posRowCounts[row] : " ").append("|");
            for (int col = 0; col < cols; ++col) {
                result.append(board[row][col]);
                if (col < cols - 1) result.append(" ");
            }
            result.append("|").append(negRowCounts[row] != IGNORED ? negRowCounts[row] : " ").append("\n");
        }
        result.append("  ");
        result.append("-".repeat(Math.max(0, cols * 2 - 1)));
        result.append("\n  ");
        for (int col = 0; col < cols; ++col) {
            result.append(negColCounts[col] != IGNORED ? negColCounts[col] : " ").append(" ");
        }
        result.append(" -\n");
        return result.toString();
    }
    /**
     * Returns the number of rows in the configuration grid.
     *
     * @return The number of rows in the grid.
     */
    @Override
    public int getRows() { return rows; }
    /**
     * Returns the number of columns in the configuration grid.
     *
     * @return The number of columns in the grid.
     */
    @Override
    public int getCols() { return cols; }
    /**
     * Returns the number of positive values in a specific row.
     *
     * @param row The row index.
     * @return The number of positive values in the specified row.
     */
    @Override
    public int getPosRowCount(int row) { return posRowCounts[row]; }
    /**
     * Returns the number of positive values in a specific column.
     *
     * @param col The column index.
     * @return The number of positive values in the specified column.
     */
    @Override
    public int getPosColCount(int col) { return posColCounts[col]; }
    /**
     * Returns the number of negative values in a specific row.
     *
     * @param row The row index.
     * @return The number of negative values in the specified row.
     */
    @Override
    public int getNegRowCount(int row) { return negRowCounts[row]; }
    /**
     * Returns the number of negative values in a specific column.
     *
     * @param col The column index.
     * @return The number of negative values in the specified column.
     */
    @Override
    public int getNegColCount(int col) { return negColCounts[col]; }
    /**
     * Returns the pair type (LEFT, RIGHT, TOP, BOTTOM, etc.) for a given position on the board.
     *
     * @param row The row index.
     * @param col The column index.
     * @return The pair type at the specified position.
     */
    @Override
    public char getPair(int row, int col) { return pairGrid[row][col]; }
    /**
     * Returns the value (X, +, -, etc.) at a given position on the board.
     *
     * @param row The row index.
     * @param col The column index.
     * @return The value at the specified position.
     */
    @Override
    public char getVal(int row, int col) { return board[row][col]; }
    /**
     * Returns the current row index of the cursor.
     *
     * @return The current row index of the cursor.
     */
    @Override
    public int getCursorRow() { return cursorRow; }
    /**
     * Returns the current column index of the cursor.
     *
     * @return The current column index of the cursor.
     */
    @Override
    public int getCursorCol() { return cursorCol; }
}