package toys;

/**
 * Abstract class representing a flying toy.
 * Extends Toy and provides altitude-related behaviors.
 * @author Aamir Sohail
 */
public abstract class Flying extends Toy {
    protected double maxAltitude;
    protected double currentAltitude;

    /**
     * Constructor to initialize a flying toy.
     * @param productCode Unique product code for the toy.
     * @param name Name of the toy.
     * @param maxAltitude The maximum altitude the toy can reach.
     */
    public Flying(int productCode, String name, double maxAltitude) {
        super(productCode, name);
        this.maxAltitude = maxAltitude;
        this.currentAltitude = 0.0;
    }

    /**
     * Gets the maximum altitude the toy can reach.
     * @return The maximum altitude.
     */
    public double getMaxAltitude() {
        return this.maxAltitude;
    }

    /**
     * Gets the current altitude of the toy.
     * @return The current altitude.
     */
    public double getCurrentAltitude() {
        return this.currentAltitude;
    }

    /**
     * Increases the altitude of the toy by a specified amount.
     * Ensures the toy does not exceed its maximum altitude.
     * @param amount The amount by which to increase the altitude.
     */
    protected void increaseAltitude(double amount) {
        this.currentAltitude += amount;
        if (this.currentAltitude > maxAltitude) {
            this.currentAltitude = maxAltitude;
        }
    }

    /**
     * Returns a string representation of the flying toy.
     * @return Formatted string including max and current altitude.
     */
    @Override
    public String toString() {
        return super.toString() + ", Flying{MA:" + maxAltitude + ", CA:" + currentAltitude + "}";
    }
}
