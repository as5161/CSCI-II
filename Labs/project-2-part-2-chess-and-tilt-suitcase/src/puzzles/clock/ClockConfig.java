package puzzles.clock;

import puzzles.common.solver.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ClockConfig implements Configuration {
    private final int hours;
    private final int current;
    private final int end;

    public ClockConfig(int hours, int current, int end){
        this.hours = hours;
        this.current = current;
        this.end = end;
    }

    @Override
    public boolean isSolution() {
        return this.current == end;
    }

    @Override
    public Collection<Configuration> getNeighbors() {
        Collection<Configuration> neighbors = new ArrayList<>();

        int forward = (current % hours) + 1;
        int backward = (current - 2 + hours) % hours + 1;

        neighbors.add(new ClockConfig(hours, forward, end));
        neighbors.add(new ClockConfig(hours, backward, end));

        return neighbors;
    }

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (!(other instanceof ClockConfig)){
            return false;
        }
        ClockConfig newConfig = (ClockConfig) other;
        return this.current == newConfig.current;
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(current);
    }

    @Override
    public String toString(){
        return Integer.toString(current);
    }
}