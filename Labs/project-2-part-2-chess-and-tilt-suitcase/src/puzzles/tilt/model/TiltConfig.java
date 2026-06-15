package puzzles.tilt.model;
import puzzles.common.solver.Configuration;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 * TiltConfig represents a single configuration of the Tilt puzzle board.
 * It stores the current board state, provides functionality to generate neighboring
 * configurations by tilting, and checks for solution states.
 */
public class TiltConfig implements Configuration {
    private final char[][] board;
    private final int size;
    /**
     * Constructs a TiltConfig by reading a board from a file.
     *
     * @param filename the file containing the board layout
     * @throws FileNotFoundException if the file is not found
     */
    public TiltConfig(String filename) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filename));
        size = in.nextInt();
        in.nextLine();
        board = new char[size][size];
        for (int r = 0; r < size; r++) {
            String line = in.nextLine().trim();
            String[] tokens = line.split("\\s+");
            for (int c = 0; c < size; c++) {
                board[r][c] = tokens[c].charAt(0);
            }
        }
    }

    /**
     * Constructs a TiltConfig by copying an existing board.
     *
     * @param oldBoard the board to copy
     */
    public TiltConfig(char[][] oldBoard) {
        size = oldBoard.length;
        board = new char[size][size];
        for (int r = 0; r < size; r++) {
            board[r] = oldBoard[r].clone();
        }
    }
    /**
     * Determines if this configuration is a solution (no green disks left).
     *
     * @return true if no green disks remain, false otherwise
     */
    @Override
    public boolean isSolution() {
        for (char[] row : board) {
            for (char c : row) {
                if (c == 'G') {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Generates all valid neighboring configurations by tilting the board in each direction.
     *
     * @return a collection of neighboring configurations
     */
    @Override
    public Collection<Configuration> getNeighbors() {
        List<Configuration> neighbors = new ArrayList<>();
        for (char dir : List.of('N', 'S', 'E', 'W')) {
            TiltConfig moved = tilt(dir);
            if (!this.equals(moved)) {
                neighbors.add(moved);
            }
        }
        return neighbors;
    }
    /**
     * Returns the current board layout.
     *
     * @return the board
     */
    public char[][] getBoard() {
        return board;
    }
    /**
     * Returns the size of the board.
     *
     * @return the board size
     */
    public int getSize() {
        return size;
    }
    /**
     * Checks if this configuration is equal to another object.
     *
     * @param o the other object
     * @return true if the boards are identical, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TiltConfig other)) return false;
        return Arrays.deepEquals(this.board, other.board);
    }
    /**
     * Returns a hash code for this configuration.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : board) {
            for (int c = 0; c < row.length; c++) {
                sb.append(row[c]);
                if (c < row.length - 1) sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
    /**
     * Creates a new TiltConfig by tilting the board in a specified direction.
     *
     * @param dir the direction to tilt ('N', 'S', 'E', or 'W')
     * @return the new configuration after tilting
     */
    public TiltConfig tilt(char dir) {
        char[][] newBoard = new char[size][size];
        for (int r = 0; r < size; r++) {
            newBoard[r] = board[r].clone();
        }
        boolean blueFellInHole = false;
        if (dir == 'N') {
            for (int c = 0; c < size; c++) {
                for (int r = 1; r < size; r++) {
                    blueFellInHole |= slide(newBoard, r, c, -1, 0);
                }
            }
        } else if (dir == 'S') {
            for (int c = 0; c < size; c++) {
                for (int r = size - 2; r >= 0; r--) {
                    blueFellInHole |= slide(newBoard, r, c, 1, 0);
                }
            }
        } else if (dir == 'W') {
            for (int r = 0; r < size; r++) {
                for (int c = 1; c < size; c++) {
                    blueFellInHole |= slide(newBoard, r, c, 0, -1);
                }
            }
        } else if (dir == 'E') {
            for (int r = 0; r < size; r++) {
                for (int c = size - 2; c >= 0; c--) {
                    blueFellInHole |= slide(newBoard, r, c, 0, 1);
                }
            }
        }
        if (blueFellInHole) {
            return this;
        } else {
            return new TiltConfig(newBoard);
        }
    }

    /**
     * Slides a piece in a specified direction until it stops.
     *
     * @param board the current board
     * @param r the starting row
     * @param c the starting column
     * @param dr the row direction delta
     * @param dc the column direction delta
     * @return true if a blue disk falls into a hole, false otherwise
     */
    private boolean slide(char[][] board, int r, int c, int dr, int dc) {
        if (board[r][c] != 'G' && board[r][c] != 'B') return false;
        int row = r;
        int col = c;
        char piece = board[r][c];
        while (true) {
            int nr = row + dr;
            int nc = col + dc;
            if (nr < 0 || nr >= board.length || nc < 0 || nc >= board[0].length) break;
            if (board[nr][nc] == '.') {
                board[nr][nc] = piece;
                board[row][col] = '.';
                row = nr;
                col = nc;
            } else if (board[nr][nc] == 'O') {
                if (piece == 'G') {
                    board[row][col] = '.';
                }
                if (piece == 'B') {
                    return true;
                }
                break;
            } else {
                break;
            }
        }
        return false;
    }

    /**
     * Checks if the board contains a blue disk that fell into a hole.
     *
     * @return true if a blue is in a hole, false otherwise
     */
    public boolean hasBlueInHole() {
        for (char[] row : board) {
            for (char c : row) {
                if (c == 'X') {
                    return true;
                }
            }
        }
        return false;
    }
}
