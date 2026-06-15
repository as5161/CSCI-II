package toys;

/**
 * Represents a Doll toy.
 * Extends the Toy class and implements the IToy interface.
 * Provides behaviors specific to a Doll, including attributes like hair color, age, and speech.
 * @author Aamir Sohail
 */
public class Doll extends Toy implements IToy{
    private final Color hairColor;
    private static int nextProductCode = 200;
    private final int age;
    private final String speak;

    /**
     * Constructor to initialize a Doll instance with unique product code.
     * @param name The name of the Doll.
     * @param hairColor The color of the Doll's hair.
     * @param age The age of the Doll (used to affect wear during play).
     * @param speak The phrase the Doll says during play.
     */
    protected Doll(String name, Color hairColor, int age, String speak){
    super(nextProductCode++, name);
    this.hairColor = hairColor;
    this.age = age;
    this.speak = speak;
    }

    /**
     * Constructor to initialize a Doll instance with a given product code.
     * @param productCode The product code of the Doll.
     * @param name The name of the Doll.
     * @param hairColor The color of the Doll's hair.
     * @param age The age of the Doll (used to affect wear during play).
     * @param speak The phrase the Doll says during play.
     */
    protected Doll(int productCode,String name, Color hairColor, int age, String speak){
        super(productCode, name);
        this.hairColor = hairColor;
        this.age = age;
        this.speak = speak;
    }

    /**
     * Gets the hair color of the Doll.
     * @return The color of the Doll's hair.
     */
    public Color getHairColor(){
        return this.hairColor;
    }

    /**
     * Gets the age of the Doll.
     * @return The age of the Doll.
     */
    public int getAge(){
        return this.age;
    }

    /**
     * Gets the phrase the Doll speaks during play.
     * @return The phrase the Doll says.
     */
    public String getSpeak(){
        return this.speak;
    }


    /**
     * Implements the special play behavior for the Doll.
     * Simulates the Doll brushing its hair and speaking during play.
     * @param minutes The duration of play in minutes.
     */
    @Override
    protected void specialPlay(int minutes) {
        System.out.println("\t" + getName() + " brushes their " + getHairColor() + " hair and says, "
        + "\"" + getSpeak() + "\"" );
        increaseWear(age);
    }

    /**
     * Returns a string representation of the Doll.
     * Includes details from the parent class and the Doll's unique characteristics (hair color, age, and speech).
     * @return Formatted string representation of the Doll.
     */
    @Override
    public String toString() {
        return super.toString() + ", Doll{HC:" + hairColor + ", A:" + age + ", S:" + speak + "}";
    }
}
