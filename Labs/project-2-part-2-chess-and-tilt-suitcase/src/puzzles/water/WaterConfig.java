package puzzles.water;

import puzzles.common.solver.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class WaterConfig implements Configuration {
    int[] capacities;
    int[] contents;
    int goal;

    public WaterConfig(int[] capacities, int[] contents, int goal){
        this.capacities = capacities;
        this.contents = contents;
        this.goal = goal;
    }

    @Override
    public boolean isSolution() {
        for (int i : contents){
            if (i == goal){
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<Configuration> getNeighbors() {
        Collection<Configuration> neighbors = new ArrayList<>();

        // filling bucket
        for (int i = 0; i < contents.length; i++){
            if (contents[i] < capacities[i]){
                int[] newContents = contents.clone();
                newContents[i] = capacities[i];
                neighbors.add(new WaterConfig(capacities, newContents, goal));
            }
        }

        //emptying buckets
        for (int i = 0; i < contents.length; i++){
            if (contents[i] != 0){
                int[] newContents = contents.clone();
                newContents[i] = 0;
                neighbors.add(new WaterConfig(capacities, newContents, goal));
            }
        }

        // pouring buckets
        for (int i = 0; i < contents.length; i++){
            for (int j = 0; j < contents.length; j++){
                if (i != j && contents[i] > 0 && contents[j] < capacities[j]){
//                    int pourAmount = capacities[j] - contents[j];
//                    if(pourAmount > contents[i]){
//                        pourAmount = contents[i]; // ensure pour amount is not more than contents[i]
//                    }
                    int pourAmount = Math.min(contents[i], capacities[j] - contents[j]); // I could've just done this
                    int[] newContents = contents.clone();
                    newContents[i] -= pourAmount;
                    newContents[j] += pourAmount;
                    neighbors.add(new WaterConfig(capacities, newContents, goal));
                }
            }
        }

        return neighbors;
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof WaterConfig)){
            return false;
        }
        WaterConfig otherConfig = (WaterConfig) other;
        return Arrays.equals(this.contents, otherConfig.contents);
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(contents);
    }

    @Override
    public String toString(){
        return Arrays.toString(contents);
    }
}
