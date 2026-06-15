/**
 * Penguins are animals who can also swim underwater.  While they swim
 * underwater they eat fish at a rate that is determined by the length
 * of their beaks.
 *
 * @author RIT CS
 */
public class Penguin implements Animal, Swimmer {
    /** the base rate a penguin eats fish while diving */
    public final static int FISH_EATEN_PER_MINUTE = 4;
    /** the rate at which a penguin runs */
    public final static double RUN_SPEED_MS = 3.12928;
    /** the speed at which a penguin swims at */
    public final static double SWIM_SPEED_MS = 2.01168;

    /** the length of the penguin's beak */
    private int beakLength;
    /** the total number of fish the penguin has eaten */
    private int fishEaten;

    /**
     * Create a new penguin.
     *
     * @param beakLength the penguin's beak length
     */
    public Penguin(int beakLength) {
        this.beakLength = beakLength;
        this.fishEaten = 0;
    }

    /**
     * Get how many fish the penguin has eaten while diving.
     *
     * @return total number of fish eaten
     */
    public int getFishEaten() {
        return this.fishEaten;
    }

    /**
     * The penguin runs at a constant rate per second.
     *
     * @param seconds number of seconds to run
     * @return
     */
    @Override
    public double run(int seconds) {
        return seconds * RUN_SPEED_MS;
    }

    /**
     * When the penguin speaks they say, "gak! gak!", e.g.:
     *
     * "Penguin{beakLength=20, fishEaten=0} says, "gak! gak!""
     *
     * @return
     */
    @Override
    public String speak() {
        return toString() + " says, \"gak! gak!\"";
    }

    /**
     * When the penguin dives underwater they eat fish at a rate of
     * FISH_EATEN_PER_MINUTE times the number of minutes, times their beak
     * length.  The distance they travel is at a constant rate of
     * SWIM_SPEED_MS.
     *
     * @param minutes the number of minutes to dive
     * @return
     */
    @Override
    public double dive(int minutes) {
        this.fishEaten += (FISH_EATEN_PER_MINUTE * minutes * 
            this.beakLength);
        return minutes * 60 * SWIM_SPEED_MS;
    }

    /**
     * Get the string representation of the penguin, e.g.:
     *
     * "Penguin{beakLength=20, fishEaten=0}"
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Penguin{" +
                "beakLength=" + this.beakLength +
                ", fishEaten=" + this.fishEaten +
                '}';
    }
}
