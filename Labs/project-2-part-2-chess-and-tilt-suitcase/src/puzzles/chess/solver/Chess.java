package puzzles.chess.solver;

import puzzles.chess.model.ChessConfig;
import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

import java.util.List;

public class Chess {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Chess filename");
        } else {
            System.out.println("File: " + args[0]);

            ChessConfig start = new ChessConfig(args[0]);
            Solver solver = new Solver();

            List<Configuration> path = solver.solve(start);

            System.out.print(start);
            System.out.println("Total Configs: " + solver.getTotalConfigs());
            System.out.println("Unique Configs: " + solver.getUniqueConfigs());
            if (path == null) {
                System.out.println("No Solution");
            } else {
                int i = 0;
                for (Configuration config : path) {
                    System.out.println("Step " + i + ":\n" + config);
                    i++;
                }
            }
        }
    }
}
