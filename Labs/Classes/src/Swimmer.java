/**
 * A swimmer can dive under water for a certain amount of time.
 *
 * @author RIT CS
 */
public interface Swimmer {
    /**
     * Dive under water.
     *
     * @param minutes the number of minutes
     * @return the distance travelled underwater
     */
    double dive(int minutes);
}
