package puzzles.clock;

import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Main class for the clock puzzle.
 *
 * @author Zinedine Sehili
 */
public class Clock {
    /**
     * Run an instance of the clock puzzle.
     *
     * @param args [0]: the number of hours in the clock;
     *             [1]: the starting hour;
     *             [2]: the finish hour.
     */
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println(("Usage: java Clock hours start finish"));
        } else {
            System.out.print("Hours: " + args[0] + ", ");
            System.out.print("Start: " + args[1] + ", ");
            System.out.println("End: " + args[2]);

            int hours = Integer.parseInt(args[0]);
            int start = Integer.parseInt(args[1]);
            int end = Integer.parseInt(args[2]);

            ClockConfig clock = new ClockConfig(hours, start, end);
            Solver solver = new Solver();
            try{
                List<Configuration> path = solver.solve(clock);
                int i = 0;
                System.out.println("Total configs: " + solver.getTotalConfigs());
                System.out.println("Unique configs: " + solver.getUniqueConfigs());
                for(Configuration config : path){
                    System.out.println("Step " + i + ": " + config);
                    i++;
                }
            } catch (NullPointerException e) {
                System.out.println("No Solution");
            }


        }
    }
}
