package puzzles.clock;

import puzzles.common.solver.*;

import java.util.List;
/**
 * The Clock class is a driver for solving the clock puzzle using the Solver framework.
 * It expects three command-line arguments: the total number of hours on the clock,
 * the starting hour, and the target hour.
 *
 * The puzzle is solved using a breadth-first search approach implemented in the Solver class.
 * The solution path, if found, is printed step by step along with statistics on
 * total and unique configurations explored.
 */
public class Clock {
    /**
     * The main method parses input arguments and solves the clock puzzle.
     * It expects exactly three arguments:
     * - fullCycle: the total number of positions on the clock
     * - initialTick: the initial hour
     * - finalTick: the target hour
     *
     * If a solution exists, it prints the sequence of configurations from start to end.
     * If not, it notifies the user that no solution was found.
     *
     * @param args the command-line arguments: fullCycle, initialTick, and finalTick
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Clock hours start end");
            return;
        }
        int fullCycle = Integer.parseInt(args[0]);
        int initialTick = Integer.parseInt(args[1]);
        int finalTick = Integer.parseInt(args[2]);
        Configuration initConfig = new ClockConfig(fullCycle, initialTick, finalTick);
        Solver searcher = new Solver();
        List<Configuration> steps = searcher.computePath(initConfig);
        System.out.println("Hours: " + fullCycle + ", Start: " + initialTick + ", End: " + finalTick);
        System.out.println("Total configs: " + ClockConfig.getTotalConfigs());
        System.out.println("Unique configs: " + ClockConfig.getUniqueConfigs());
        if (steps == null) {
            System.out.println("No solution");
        } else {
            for (int stepIndex = 0; stepIndex < steps.size(); stepIndex++) {
                System.out.println("Step " + stepIndex + ": " + steps.get(stepIndex));
            }
        }
    }
}
