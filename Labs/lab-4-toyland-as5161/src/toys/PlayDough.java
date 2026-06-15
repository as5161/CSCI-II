package toys;

/**
 * Represents a PlayDough toy.
 * Extends the Toy class and implements the IToy interface.
 * Provides behavior specific to PlayDough, including its color and play effects.
 * @author Aamir Sohail
 */
public class PlayDough extends Toy implements IToy{
    public final static double WEAR_MULTIPLIER = 0.05;
    private final Color color;
    private static int nextProductCode = 100;

    /**
     * Constructor to initialize a PlayDough instance.
     * @param name The name of the PlayDough.
     * @param color The color of the PlayDough.
     */
    protected PlayDough (String name, Color color) {
        super(nextProductCode++, name);
        this.color = color;
    }

    /**
     * Gets the color of the PlayDough.
     * @return The color of the PlayDough.
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * Defines the special play behavior for the PlayDough toy.
     * Increases wear based on the time spent playing.
     * @param minutes The duration of play in minutes.
     */
    @Override
    protected void specialPlay(int minutes) {
        System.out.println("\tArts and crafting with " + color + " " + getName());
        increaseWear(WEAR_MULTIPLIER * minutes);
    }

    /**
     * Returns a string representation of the PlayDough.
     * Includes details from the parent class and the color of the PlayDough.
     * @return Formatted string representation of the PlayDough.
     */
    @Override
    public String toString() {
        return super.toString() + ", PlayDough{C:" + color + "}";
    }
}
