package puzzles.water;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

import java.util.Arrays;
import java.util.List;

/**
 * Main class for the water buckets puzzle.
 *
 * @author Zinedine Sehili
 */
public class Water {

    /**
     * Run an instance of the water buckets puzzle.
     *
     * @param args [0]: desired amount of water to be collected;
     *             [1..N]: the capacities of the N available buckets.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println(
                    ("Usage: java Water amount bucket1 bucket2 ...")
            );
        } else {

            int[] capacities = new int[args.length - 1];
            int[] contents = new int[args.length - 1];
            int amount = Integer.parseInt(args[0]);
            for(int i = 0; i < args.length - 1; i++){
                capacities[i] = Integer.parseInt(args[i + 1]);
            }
            System.out.print("Amount: " + args[0] + ", ");
            System.out.println("Buckets: " + Arrays.toString(capacities));

            WaterConfig water = new WaterConfig(capacities, contents, amount);
            Solver solver = new Solver();
            try{
                List<Configuration> path = solver.solve(water);
                int i = 0;
                System.out.println("Total configs: " + solver.getTotalConfigs());
                System.out.println("Unique configs: " + solver.getUniqueConfigs());
                for(Configuration config : path){
                    System.out.println("Step " + i + ": " + config);
                    i++;
                }
            }
            catch (NullPointerException e){
                System.out.println("No Solution");
            }
        }
    }
}
