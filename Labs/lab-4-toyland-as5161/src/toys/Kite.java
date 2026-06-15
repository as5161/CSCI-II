package toys;

/**
 * Represents a Kite toy.
 * Extends the Flying class and implements the IToy interface.
 * Provides behaviors specific to flying kites, including altitude control, wear, and type.
 * @author Aamir Sohail
 */
public class Kite extends Flying implements IToy{
    private static int nextProductCode = 400;
    private static final double ALTITUDE_CHANGE_RATE = 0.5;
    private static final double WEAR_MULTIPLIER = 0.05;
    private final Color color;
    private final Type type;
    private final int lineLength;
    private boolean hasTakenOff = false;

    /**
     * Constructor to initialize a Kite instance.
     * @param name The name of the kite.
     * @param color The color of the kite.
     * @param type The type of the kite (e.g., Cellular, Delta, etc.).
     * @param lineLength The length of the string used for flying the kite.
     */
    public Kite(String name, Color color, Type type, int lineLength) {
        super(nextProductCode++, name, lineLength);
        this.color = color;
        this.type = type;
        this.lineLength = lineLength;
    }

    /**
     * Gets the color of the Kite.
     * @return The color of the kite.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the type of the Kite.
     * @return The type of the kite (e.g., Cellular, Delta, etc.).
     */
    public Type getType() {
        return type;
    }

    /**
     * Gets the length of the line used for flying the Kite.
     * @return The line length.
     */
    public int getLineLength() {
        return lineLength;
    }

    /**
     * Implements the special play behavior for the Kite.
     * Handles takeoff, altitude changes, wear, and outputs related messages during play.
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
        increaseWear(time * WEAR_MULTIPLIER);
        System.out.println("\tFlying the " + color + ", " + type + " kite " + getName() + " with " + lineLength + " feets of line");
    }

    /**
     * Returns a string representation of the Kite.
     * Includes details from the parent class and the kite's unique characteristics.
     * @return Formatted string representation of the kite.
     */
    @Override
    public String toString() {
        return super.toString() + ", Kite{C:" + color + ", T:" + type + ", LL:" + lineLength + "}";
    }

    /**
     * Enum to represent the different types of kites.
     */
    public enum Type {
        CELLULAR, DELTA, DIAMOND, PARAFOIL, ROKKAKUS, SLED, STUNT
    }
}
