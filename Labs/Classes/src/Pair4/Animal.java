package Pair4;

/**
 * CSCI-142 Computer Science 2 Recitation Exercise
 * 04-Inheritance
 * Animal Farm
 *
 * All animals have a name.  They can all run and speak but how they do it
 * is up to the concrete subclasses that inherit, i.e. Camel, Penguin and
 * Duck.
 *
 * @author RIT CS
 */
public abstract class Animal {
    /** animal's name */
    private String name;
    /** the total distance the animal has travelled */
    private double distance;

    /**
     * Create a new animal.
     *
     * @param name animal's name
     */
    public Animal(String name) {
        this.name = name;
        this.distance = 0;
    }

    /**
     * An animal both runs and speaks.  How the separate things happen is up
     * to how the subclasses implement these behaviors.
     *
     * @param seconds number of seconds to run
     */
    public void runAndSpeak(int seconds) {
        run(seconds);
        System.out.println("\t" + speak());
    }

    /**
     * Return the total distance the animal travelled.
     *
     * @return distance travelled
     */
    public double getDistance() {
        return this.distance;
    }

    /**
     * An animal travels a certain distance.
     *
     * @param distance amount travelled
     */
    protected void travel(double distance) {
        this.distance += distance;
    }

    /**
     * Animal runs for a certain number of seconds.
     *
     * @param seconds number of seconds to run
     */
    public abstract void run(int seconds);

    /**
     * What the animal says when they speak.
     *
     * @return their message
     */
    public abstract String speak();

    /**
     * Animal string representation, leaving the end open
     * for the addition of information on specifica animal
     * types.
     * @return a string in format "Animal{name='NNN, distance=DDD', "
     */
    @Override
    public String toString() {
        return "Animal{" +
                "name='" + this.name +
                ", distance=" + this.distance +
                "\', ";
    }
}
