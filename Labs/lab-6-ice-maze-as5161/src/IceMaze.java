import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Class representing an Ice Maze simulation.
 * The maze is represented as a grid, and the escape paths are calculated using graph traversal.
 */
public class IceMaze {
    private final Graph<Coord> graph;
    private final char[][] grid;
    private final int height;
    private final int width;
    private final int exitRow;
    private final Coord exitCoord;

    /**
     * Constructs an IceMaze object with a grid and exit row information.
     * Initializes the graph for the maze.
     *
     * @param grid 2D array representing the maze grid
     * @param height number of rows in the grid
     * @param width number of columns in the grid
     * @param exitRow the row number of the exit
     */
    public IceMaze(char[][] grid, int height, int width, int exitRow) {
        this.grid = grid;
        this.height = height;
        this.width = width;
        this.exitRow = exitRow;
        this.exitCoord = new Coord(exitRow, width);
        this.graph = new Graph<>();
        buildGraph();
    }

    /**
     * Reads a maze from a file and returns an IceMaze object.
     *
     * @param filename the path to the maze file
     * @return an IceMaze object representing the maze
     * @throws InvalidMazeException if the maze file is invalid
     */
    public static IceMaze readFile(String filename) throws InvalidMazeException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            if (line == null) throw new InvalidMazeException("Empty file");
            String[] parts = line.trim().split("\\s+");
            if (parts.length != 3) throw new InvalidMazeException("Invalid first line");
            int height = Integer.parseInt(parts[0]);
            int width = Integer.parseInt(parts[1]);
            int exitRow = Integer.parseInt(parts[2]);
            if (exitRow < 0 || exitRow >= height) throw new InvalidMazeException("Invalid exit row: " + exitRow);
            char[][] grid = new char[height][width];
            for (int r = 0; r < height; r++) {
                String lines = reader.readLine();
                if (lines == null) throw new InvalidMazeException("Missing grid row " + r);
                String[] cells = lines.trim().split("\\s+");
                if (cells.length != width) throw new InvalidMazeException("Invalid row " + r + " length");
                for (int c = 0; c < width; c++) {
                    String cellStr = cells[c];
                    if (cellStr.length() != 1) {
                        throw new InvalidMazeException("Invalid grid character");
                    }
                    char cell = cellStr.charAt(0);
                    if (cell != '.' && cell != '*') {
                        throw new InvalidMazeException("Invalid grid character");
                    }
                    grid[r][c] = cell;
                }
            }
            return new IceMaze(grid, height, width, exitRow);
        } catch (IOException e) {
            throw new InvalidMazeException("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new InvalidMazeException("Invalid number in maze file");
        }
    }

    /**
     * Builds a graph representation of the maze where each open cell is a node.
     * The method also adds edges based on valid paths between neighboring cells.
     */
    private void buildGraph() {
        graph.addNode(exitCoord);
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (grid[r][c] != '.') continue;
                Coord current = new Coord(r, c);
                graph.addNode(current);
                for (int[] dir : new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}) {
                    int newR = r;
                    int newC = c;
                    boolean exitFound = false;
                    while (true) {
                        int nextR = newR + dir[0];
                        int nextC = newC + dir[1];

                        if (dir[1] == 1 && newR == exitRow && nextC == width) {
                            exitFound = true;
                            break;
                        }
                        if (nextR < 0 || nextR >= height ||
                                nextC < 0 || nextC >= width ||
                                grid[nextR][nextC] == '*') {
                            break;
                        }
                        newR = nextR;
                        newC = nextC;
                    }
                    if (exitFound) {
                        graph.addEdge(current, exitCoord);
                    } else if (newR != r || newC != c) {
                        Coord dest = new Coord(newR, newC);
                        graph.addEdge(current, dest);
                    }
                }
            }
        }
    }

    /**
     * Finds and prints escape routes from the maze.
     * Uses breadth-first search (BFS) to find the shortest path to the exit.
     */
    public void findEscapes() {
        Node<Coord> exitNode = graph.getNode(exitCoord);
        if (exitNode == null) {
            System.out.println("No escape possible");
            return;
        }
        Map<Integer, List<Coord>> stepsMap = new TreeMap<>();
        for (Node<Coord> node : graph.getNodes()) {
            Coord coord = node.getData();
            if (coord.equals(exitCoord)) continue;

            int steps = bfs(node, exitNode);
            if (steps != -1) {
                stepsMap.computeIfAbsent(steps, k -> new ArrayList<>()).add(coord);
            }
        }
        for (List<Coord> coords : stepsMap.values()) {
            coords.sort(Comparator.comparingInt(Coord::r).thenComparingInt(Coord::c));
        }
        for (Map.Entry<Integer, List<Coord>> entry : stepsMap.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            List<Coord> coords = entry.getValue();
            for (int i = 0; i < coords.size(); i++) {
                System.out.print(coords.get(i));
                if (i < coords.size() - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * Performs a breadth-first search (BFS) to find the shortest path from the start node to the target node.
     *
     * @param start the starting node
     * @param target the target node
     * @return the number of steps in the shortest path, or -1 if no path is found
     */
    private int bfs(Node<Coord> start, Node<Coord> target) {
        Queue<Node<Coord>> queue = new LinkedList<>();
        Map<Node<Coord>, Integer> visited = new HashMap<>();
        queue.add(start);
        visited.put(start, 0);
        while (!queue.isEmpty()) {
            Node<Coord> current = queue.poll();
            int steps = visited.get(current);
            if (current.equals(target)) {
                return steps;
            }
            for (Node<Coord> neighbor : current.getNeighbors()) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, steps + 1);
                    queue.add(neighbor);
                }
            }
        }
        return -1;
    }

    /**
     * Prints the graph structure of the maze, showing each node and its neighbors.
     */
    public void printGraph() {
        List<Node<Coord>> nodes = new ArrayList<>(graph.getNodes());
        Node<Coord> exitNode = graph.getNode(exitCoord);
        nodes.remove(exitNode);
        nodes.sort(Comparator.comparing(
                node -> node.getData(),
                Comparator.comparingInt(Coord::r).thenComparingInt(Coord::c)
        ));
        if (exitNode != null) {
            nodes.add(exitNode);
        }
        for (Node<Coord> node : nodes) {
            Coord coord = node.getData();
            System.out.print(coord + ":  ");
            List<Coord> neighbors = new ArrayList<>();
            for (Node<Coord> neighbor : node.getNeighbors()) {
                neighbors.add(neighbor.getData());
            }
            StringJoiner sj = new StringJoiner(", ");
            for (Coord neighbor : neighbors) {
                sj.add(neighbor.toString());
            }
            System.out.println(sj);
        }
    }

    /**
     * Main method for running the IceMaze simulation.
     * It reads a maze file and prints the graph and escape routes.
     *
     * @param args the command-line arguments (filename of maze)
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java IceMaze <filename>");
            return;
        }
        try {
            IceMaze maze = IceMaze.readFile(args[0]);
            maze.printGraph();
            maze.findEscapes();
        } catch (InvalidMazeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
