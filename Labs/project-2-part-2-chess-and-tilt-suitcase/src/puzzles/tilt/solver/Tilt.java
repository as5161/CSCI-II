package puzzles.tilt.solver;
import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;
import puzzles.tilt.model.TiltConfig;
import java.io.FileNotFoundException;
import java.util.List;
/**
 * Tilt is a driver class that loads a Tilt puzzle, solves it using a generic solver,
 * and prints the solution steps and search statistics to the console.
 */
public class Tilt {
    /**
     * The entry point for solving a Tilt puzzle from a file.
     * Loads the puzzle, solves it, and prints the solution steps and statistics.
     *
     * @param args command-line arguments (expects one filename)
     * @throws FileNotFoundException if the file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.out.println("Usage: java puzzles.tilt.solver.Tilt filename");
            System.exit(1);
        }

        String filename = args[0];
        TiltConfig start = new TiltConfig(filename);

        System.out.println("File: " + filename);
        System.out.println(start);

        Solver solver = new Solver();
        List<Configuration> path = solver.solve(start);

        System.out.println("Total configs: " + solver.getTotalConfigs());
        System.out.println("Unique configs: " + solver.getUniqueConfigs());

        if (path == null) {
            System.out.println("No solution");
        } else {
            for (int i = 0; i < path.size(); i++) {
                System.out.println("Step " + i + ":");
                System.out.println(path.get(i));
            }
        }
    }
}
