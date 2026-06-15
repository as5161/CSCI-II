package puzzles.common.solver;

import java.io.ObjectInputFilter;
import java.util.*;

public class Solver {
    HashMap<Configuration, Configuration> predecessors = new HashMap<>();
    Queue<Configuration> queue = new LinkedList<>();
    int totalConfigs = 0;
    int uniqueConfigs = 0;

    public List<Configuration> solve(Configuration start){
        queue.add(start);
        predecessors.put(start, null);
        while(!queue.isEmpty()){
            Configuration current = queue.remove();
            if (current.isSolution()){
                return constructPath(predecessors, start, current);
            }
            else{
                for(Configuration neighbor : current.getNeighbors()){
                    totalConfigs++;
                    if(!predecessors.containsKey(neighbor)) {
                        queue.add(neighbor);
                        predecessors.put(neighbor, current);
                    }

                }
            }
        }
        return null;
    }
    private List<Configuration> constructPath(Map<Configuration, Configuration> predecessors, Configuration start, Configuration solution){
        List<Configuration> path = new LinkedList<>();
        if(predecessors.containsKey(solution)){
            Configuration currentConfig = solution;
            while(!currentConfig.equals(start)){
                path.addFirst(currentConfig);
                currentConfig = predecessors.get(currentConfig);
            }
            path.addFirst(start);
        }
        return path;
    }

    public int getTotalConfigs(){
        return totalConfigs;
    }

    public int getUniqueConfigs(){
        return predecessors.size();
    }
}
