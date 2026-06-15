package toys;

/**
 * Represents a remote-controlled (RC) plane toy.
 * Extends the Flying class and implements the IToy interface.
 * Provides behaviors specific to an RC plane, such as speed and altitude control.
 * @author Aamir Sohail
 */
public class RCPlane extends Flying implements IToy{
    private static int nextProductCode = 300;
    private static final double ALTITUDE_CHANGE_RATE = 5.0;
    private static final double     SPEED_CHANGE_RATE = 2.0;
    private static final double STARTING_SPEED = 15.0;

    private final double maxSpeed;
    private double currentSpeed;
    private boolean hasTakenOff = false;

    /**
     * Constructor to initialize an RCPlane instance.
     * @param name The name of the RC plane.
     * @param maxSpeed The maximum speed the RC plane can achieve.
     * @param maxAltitude The maximum altitude the RC plane can reach.
     */
    public RCPlane(String name, double maxSpeed, double maxAltitude) {
        super(nextProductCode++, name, maxAltitude);
        this.maxSpeed = maxSpeed;
        this.currentSpeed = STARTING_SPEED;
    }

    /**
     * Gets the current speed of the RC plane.
     * @return The current speed.
     */
    public double getSpeed() {
        return this.currentSpeed;
    }

    /**
     * Gets the maximum speed of the RC plane.
     * @return The maximum speed.
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Increases the speed of the RC plane by a specified amount.
     * Ensures the speed does not exceed the maximum speed.
     * @param amount The amount by which to increase the speed.
     */
    protected void increaseSpeed(double amount){
        this.currentSpeed += amount;
        if (currentSpeed > maxSpeed) {
            currentSpeed = maxSpeed;
        }
    }

    /**
     * Implements the special play behavior for the RC plane.
     * Handles takeoff, altitude changes, and speed adjustments during play.
     * @param time The duration of play in minutes.
     */
    @Override
    protected void specialPlay(int time) {
        if (!hasTakenOff) {
            System.out.println("\t" + getName() + " took off!");
            hasTakenOff = true;
        }

        increaseAltitude(time * ALTITUDE_CHANGE_RATE);
        System.out.println("\t" + getName() + " current altitude: " + getCurrentAltitude() + " feet");
        increaseWear(getSpeed());

        increaseSpeed(time * SPEED_CHANGE_RATE);
        System.out.println("\t" + getName() + " current speed: " + getSpeed() + " mph");

    }

    /**
     * Returns a string representation of the RC plane.
     * Includes details from the parent class and the current speed.
     * @return Formatted string representation of the RC plane.
     */
    @Override
    public String toString() {
        return super.toString() + ", RCPlane{S:" + getSpeed() + "}";
    }
}
