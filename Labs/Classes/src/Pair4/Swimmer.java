package Pair4;
/**
 * CSCI-142 Computer Science 2 Recitation Exercise
 * 04-Inheritance
 * Animal Farm
 *
 * A swimmer can dive under water for a certain amount of time.
 *
 * @author RIT CS
 */
public interface Swimmer {
    /**
     * Dive under water.
     *
     * @param minutes the number of minutes
     */
    void dive(int minutes);

    /**
     * How many fish has the swimmer eaten while diving?
     *
     * @return total number of fish eaten
     */
    int getFishEaten();
}
