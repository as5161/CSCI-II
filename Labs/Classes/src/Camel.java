/**
 * A camel is a an animal who can run and speak.  Because they are a mammal
 * they can be milked.
 *
 * @author RIT CS
 */
public class Camel implements Animal {
    /** how fast a camel can run */
    public final static double RUN_SPEED_MS = 17.8816;
    /** the rate they yield milk at when milked */
    public final static double MILK_RATE_LS = 8.70645;

    /** the number of humps */
    private int numHumps;
    /** the total amount of milk that was milked */
    private double litersMilked;

    /**
     * Create a new camel.
     *
     * @param numHumps number of humps
     */
    public Camel(int numHumps) {
        this.numHumps = numHumps;
        this.litersMilked = 0;
    }

    /**
     * Milk the camel for a certain number of seconds at MILK_RATE_LS, thus
     * increasing the total amount of milk that was milked.
     *
     * @param seconds number of seconds to milk for
     */
    public void milk(int seconds) {
        this.litersMilked += (seconds * MILK_RATE_LS);
    }

    /**
     * Get the total amount of milk that was milked.
     *
     * @return total milk that was milked
     */
    public double getLitersMilked() {
        return this.litersMilked;
    }

    /**
     * When the camel runs it's distance travelled is calculated as the 
     * number of seconds times the RUN_SPEED_MS, then divided by the number
     * of humps.
     *
     * @param seconds number of seconds to run
     * @return how far the camel travelled
     */
    @Override
    public double run(int seconds) {
        return (seconds * RUN_SPEED_MS) / this.numHumps;
    }

    /**
     * When the camel speaks it says "grunt!, grunt!", i.e.:
     *
     * "Camel{numHumps=1, litersMilked=0.0} says, "grunt!, grunt!""
     *
     * @return what the camel says
     */
    @Override
    public String speak() {
        return toString() + " says, \"grunt!, grunt!\"";
    }

    /**
     * When the camel is displayed as a string it displays the number of
     * humps and the total amount of milk that was milked, i.e.:
     *
     * "Camel{numHumps=1, litersMilked=0.0}"
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Camel{" +
                "numHumps=" + this.numHumps +
                ", litersMilked=" + this.litersMilked +
                '}';
    }
}
