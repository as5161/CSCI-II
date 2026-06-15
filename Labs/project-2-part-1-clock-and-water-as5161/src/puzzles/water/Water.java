package puzzles.water;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;
import puzzles.common.solver.WaterConfig;

import java.util.*;

/**
 * The {@code Water} class serves as the entry point for solving the water bucket puzzle.
 * It reads the desired amount of water and bucket capacities from command line arguments,
 * sets up the initial configuration with all buckets empty, and uses the {@code Solver}
 * to find a sequence of configurations that leads to the target amount of water.
 */
public class Water {
    /**
     * The main method for running the water bucket puzzle solver.
     * It expects at least two arguments: the desired amount of water,
     * followed by the capacities of the buckets.
     * <p>
     * Example usage: {@code java Water 60 39 269 677 919 1553}
     *
     * @param args command line arguments: the target amount and bucket sizes
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Water amount bucket1 bucket2 ...");
            return;
        }
        int targetVolume = Integer.parseInt(args[0]);
        List<Integer> inputCapacities = new ArrayList<>();
        for (int index = 1; index < args.length; index++) {
            inputCapacities.add(Integer.parseInt(args[index]));
        }
        int[] bucketCapArray = inputCapacities.stream().mapToInt(i -> i).toArray();
        int[] initialFillLevels = new int[bucketCapArray.length];
        int targetGoal = targetVolume;
        System.out.println("Amount: " + targetVolume + ", Buckets: " + Arrays.toString(bucketCapArray));
        WaterConfig initialConfig = new WaterConfig(bucketCapArray, initialFillLevels, targetGoal);
        Solver configSolver = new Solver();
        List<Configuration> resultPath = configSolver.computePath(initialConfig);
        if (resultPath != null) {
            for (int stepNum = 0; stepNum < resultPath.size(); stepNum++) {
                System.out.println("Step " + stepNum + ": " + resultPath.get(stepNum));
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}
