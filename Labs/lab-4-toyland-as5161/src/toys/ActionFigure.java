package toys;

/**
 * Represents an Action Figure toy.
 * Extends the Doll class and implements the IToy interface.
 * Provides additional behaviors for an Action Figure, such as energy level and action-specific play behavior.
 * @author Aamir Sohail
 */
public class ActionFigure extends Doll implements IToy{
    public final static int MIN_ENERGY_LEVEL = 1;
    public final static Color HAIR_COLOR = Color.ORANGE;
    private static int nextProductCode = 500;
    private int energy;

    /**
     * Constructor to initialize an ActionFigure instance with a unique product code.
     * @param name The name of the Action Figure.
     * @param age The age of the Action Figure (affects wear during play).
     * @param speak The phrase the Action Figure says during play.
     */
    protected ActionFigure(String name, int age, String speak){
        super(nextProductCode++, name, HAIR_COLOR, age, speak);
        this.energy = MIN_ENERGY_LEVEL;
    }

    /**
     * Gets the current energy level of the Action Figure.
     * @return The energy level of the Action Figure.
     */
    public int getEnergyLevel(){
        return this.energy;
    }

    /**
     * Implements the special play behavior for the Action Figure.
     * The Action Figure performs a kung foo chop with energy based on the play duration.
     * The energy level increases with each play session.
     * @param minutes The duration of play in minutes.
     */
    @Override
    protected void specialPlay(int minutes) {
        int energyFormula = energy * minutes;

        System.out.println("\t" + getName() + " kung foo chops with " + energyFormula + " energy!");

        energy++;

        super.specialPlay(minutes);
    }

    /**
     * Returns a string representation of the Action Figure.
     * Includes details from the parent Doll class and the Action Figure's unique energy level.
     * @return Formatted string representation of the Action Figure.
     */
    @Override
    public String toString() {
        return super.toString() + ", ActionFigure{E:" + energy + "}";
    }
}
