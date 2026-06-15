package puzzles.common.solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Represents a configuration of the water bucket puzzle.
 * Each configuration includes the current water levels in all buckets,
 * the capacities of those buckets, and the target amount to be achieved.
 *
 * Used by the solver to explore possible states of the puzzle through
 * pouring, emptying, and filling operations.
 */
public class WaterConfig implements Configuration {
    private final int[] bucketCaps;
    private final int[] currentLevels;
    private final int targetAmount;
    public WaterConfig(int[] bucketCaps, int[] currentLevels, int targetAmount) {
        this.bucketCaps = bucketCaps.clone();
        this.currentLevels = currentLevels.clone();
        this.targetAmount = targetAmount;
    }
    /**
     * Checks if the current configuration contains the target amount of water in any bucket.
     *
     * @return true if any bucket has the target amount, false otherwise
     */
    @Override
    public boolean isSolution() {
        for (int waterLevel : currentLevels) {
            if (waterLevel == targetAmount) {
                return true;
            }
        }
        return false;
    }
    /**
     * Generates all valid neighboring configurations by:
     * 1. Pouring from one bucket to another
     * 2. Emptying non-empty buckets
     * 3. Filling non-full buckets
     *
     * @return a list of valid next WaterConfig configurations
     */
    @Override
    public List<Configuration> getNeighbors() {
        List<Configuration> nearbyConfigs = new ArrayList<>();
        int totalBuckets = currentLevels.length;
        for (int from = 0; from < totalBuckets; from++) {
            for (int to = 0; to < totalBuckets; to++) {
                if (from != to && currentLevels[from] > 0 && currentLevels[to] < bucketCaps[to]) {
                    int[] updatedLevels = currentLevels.clone();
                    int transfer = Math.min(currentLevels[from], bucketCaps[to] - currentLevels[to]);
                    updatedLevels[from] -= transfer;
                    updatedLevels[to] += transfer;
                    nearbyConfigs.add(new WaterConfig(bucketCaps, updatedLevels, targetAmount));
                }
            }
        }
        for (int i = 0; i < totalBuckets; i++) {
            if (currentLevels[i] > 0) {
                int[] emptied = currentLevels.clone();
                emptied[i] = 0;
                nearbyConfigs.add(new WaterConfig(bucketCaps, emptied, targetAmount));
            }
        }
        for (int i = 0; i < totalBuckets; i++) {
            if (currentLevels[i] < bucketCaps[i]) {
                int[] filled = currentLevels.clone();
                filled[i] = bucketCaps[i];
                nearbyConfigs.add(new WaterConfig(bucketCaps, filled, targetAmount));
            }
        }
        return nearbyConfigs;
    }
    /**
     * Returns a string showing the current water levels in all buckets.
     *
     * @return a string representation of the configuration
     */
    @Override
    public String toString() {
        return Arrays.toString(currentLevels);
    }

    /**
     * Checks equality with another object.
     * Two WaterConfig objects are equal if their bucket capacities, water levels, and target amounts match.
     *
     * @param obj the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        WaterConfig other = (WaterConfig) obj;
        return targetAmount == other.targetAmount &&
                Arrays.equals(bucketCaps, other.bucketCaps) &&
                Arrays.equals(currentLevels, other.currentLevels);
    }

    /**
     * Returns a hash code based on the bucket capacities, current water levels, and target amount.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = Arrays.hashCode(bucketCaps);
        hash = 31 * hash + Arrays.hashCode(currentLevels);
        hash = 31 * hash + targetAmount;
        return hash;
    }
}
