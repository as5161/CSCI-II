package puzzles.common.solver;

import java.util.*;

/**
 * The {@code Solver} class provides a generic solution mechanism for configuration-based puzzles.
 * It uses a breadth-first search (BFS) strategy to find the shortest path from a starting
 * configuration to a goal configuration.
 */
public class Solver {
    private int exploredCount = 0;
    private int seenCount = 0;

    /**
     * Computes the shortest path to the goal using BFS from the initial configuration.
     *
     * @param initialConfig the starting configuration of the puzzle
     * @return a list of configurations from start to goal, or {@code null} if no path is found
     */
    public List<Configuration> computePath(Configuration initialConfig) {
        Map<Configuration, Configuration> pathMap = new HashMap<>();
        Queue<Configuration> toExplore = new LinkedList<>();
        Set<Configuration> visitedSet = new HashSet<>();
        toExplore.add(initialConfig);
        visitedSet.add(initialConfig);
        pathMap.put(initialConfig, null);
        exploredCount = 1;
        seenCount = 1;
        while (!toExplore.isEmpty()) {
            Configuration currentStep = toExplore.poll();
            if (currentStep.isSolution()) {
                return reconstructPath(pathMap, currentStep);
            }
            for (Configuration nextStep : currentStep.getNeighbors()) {
                exploredCount++;
                if (!visitedSet.contains(nextStep)) {
                    visitedSet.add(nextStep);
                    toExplore.add(nextStep);
                    pathMap.put(nextStep, currentStep);
                    seenCount++;
                }
            }
        }
        return null;
    }

    /**
     * Reconstructs the path from start to goal using the predecessor map built during BFS.
     *
     * @param pathMap the map from configurations to their predecessors
     * @param solutionConfig the final goal configuration
     * @return a list representing the path from start to goal
     */
    private List<Configuration> reconstructPath(Map<Configuration, Configuration> pathMap, Configuration solutionConfig) {
        List<Configuration> pathSequence = new LinkedList<>();
        for (Configuration step = solutionConfig; step != null; step = pathMap.get(step)) {
            pathSequence.add(0, step);
        }
        System.out.println("Total configs: " + exploredCount);
        System.out.println("Unique configs: " + seenCount);
        return pathSequence;
    }
}
