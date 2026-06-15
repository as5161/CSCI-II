package puzzles.common.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a configuration for the clock puzzle.
 * Each configuration holds the total number of hours on the clock, the current hour (activeHour),
 * and the target hour (targetHour). It also keeps track of the number of total and unique configurations created.
 */
public class ClockConfig implements Configuration {
    private final int totalHours;
    private final int activeHour;

    private final int targetHour;
    private static int generatedConfigs = 0;
    private static final java.util.Set<ClockConfig> seenConfigs = new java.util.HashSet<>();
    /**
     * Constructs a new ClockConfig instance with the given hours, current hour (activeHour), and targetHour.
     * Increments the total configuration count and adds this configuration to the unique set.
     *
     * @param totalHours the total number of hours on the clock
     * @param activeHour the current hour
     * @param targetHour the target hour
     */
    public ClockConfig(int totalHours, int activeHour, int targetHour) {
        this.totalHours = totalHours;
        this.activeHour = activeHour;
        this.targetHour = targetHour;

        generatedConfigs++;
        seenConfigs.add(this);
    }
    /**
     * Returns the total number of ClockConfig instances created.
     *
     * @return the total number of configurations
     */
    public static int getTotalConfigs() {
        return generatedConfigs;
    }
    /**
     * Returns the number of unique ClockConfig instances created.
     *
     * @return the number of unique configurations
     */
    public static int getUniqueConfigs() {
        return seenConfigs.size();
    }
    /**
     * Determines if the current configuration is a solution (i.e., activeHour equals targetHour).
     *
     * @return true if activeHour == targetHour, false otherwise
     */
    @Override
    public boolean isSolution() {
        return activeHour == targetHour;
    }
    /**
     * Generates and returns a list of neighboring configurations by moving one hour forward
     * and one hour backward on the clock.
     *
     * @return a list of neighbor configurations
     */
    @Override
    public List<Configuration> getNeighbors() {
        List<Configuration> possibleSteps = new ArrayList<>();
        int oneForward = (activeHour % totalHours) + 1;
        int oneBackward = (activeHour - 2 + totalHours) % totalHours + 1;
        possibleSteps.add(new ClockConfig(totalHours, oneForward, targetHour));
        possibleSteps.add(new ClockConfig(totalHours, oneBackward, targetHour));
        return possibleSteps;
    }
    /**
     * Returns the string representation of this configuration (the current hour).
     *
     * @return the string form of the current hour
     */
    @Override
    public String toString() {
        return Integer.toString(activeHour);
    }
    /**
     * Checks if this configuration is equal to another object.
     *
     * @param o the object to compare with
     * @return true if the object is a ClockConfig with the same activeHour, targetHour, and totalHours
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClockConfig)) return false;
        ClockConfig that = (ClockConfig) o;
        return activeHour == that.activeHour && targetHour == that.targetHour && totalHours == that.totalHours;
    }
    /**
     * Returns a hash code value for this configuration.
     *
     * @return the hash code based on activeHour, targetHour, and totalHours
     */
    @Override
    public int hashCode() {
        return Objects.hash(activeHour, targetHour, totalHours);
    }
}
