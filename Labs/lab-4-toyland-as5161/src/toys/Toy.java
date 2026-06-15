package toys;

/**
 * @author Aamir Sohail
 * Abstract class representing a toy.
 * Provides common attributes and behaviors for all toys, such as happiness, wear, and play functionality.
 */

public abstract class Toy {
    public final static int INITIAL_HAPPINESS = 0;
    public final static int MAX_HAPPINESS = 100;
    public final static double INITIAL_WEAR = 0.0;
    private final int productCode;
    private final String name;
    private int happiness;
    private double wear;

    /**
     * Constructor to initialize a toy instance.
     * @param productCode Unique product code for the toy.
     * @param name Name of the toy.
     */
    protected Toy(int productCode, String name){
        this.productCode = productCode;
        this.name =name;
        this.happiness = 0;
        this.wear = 0.0;
    }

    /**
     * Gets the product code of the toy.
     * @return The product code.
     */
    public int getProductCode(){
        return this.productCode;
    }

    /**
     * Gets the name of the toy.
     * @return The name of the toy.
     */
    public String getName(){
        return this.name;
    }


    /**
     * Gets the current happiness level of the toy.
     * @return The happiness level.
     */
    public int getHappiness(){
        return this.happiness;
    }

    /**
     * Gets the current wear level of the toy.
     * @return The wear level.
     */
    public double getWear(){
        return this.wear;
    }

    /**
     * Checks if the toy is retired (i.e., happiness has reached the maximum level).
     * @return True if the toy is retired, false otherwise.
     */
    public boolean isRetired(){
        return happiness >= MAX_HAPPINESS;
    }

    /**
     * Increases the wear level of the toy by a specified amount.
     * @param amount The amount by which to increase the wear.
     */
    public void increaseWear(double amount){
        this.wear = this.wear + amount;
    }

    /**
     * Returns a string representation of the toy.
     * Includes product code, name, happiness, retirement status, and wear level.
     * @return Formatted string representation of the toy.
     */
    public String toString(){
        return "Toy{PC:" + getProductCode() + ", N:" + getName() + ", H:" + getHappiness() +
                ", R:" + isRetired() + ", W:" + getWear() + "}";
    }

    /**
     * Simulates playing with the toy for a specified amount of time.
     * Updates happiness and wear, and checks if the toy is retired after playing.
     * @param time The duration of play in minutes.
     */
    public void play(int time){
        System.out.println("PLAYING(" + time + "): " + this);

        specialPlay(time);
        happiness += time;

        if (isRetired()) {
            System.out.println("RETIRED: " + this);
        }
        }

    /**
     * Abstract method for special play behavior.
     * To be implemented by subclasses to define unique play actions for each toy type.
     * @param minutes The duration of play in minutes.
     */
        protected abstract void specialPlay(int minutes);

}

