package toys;

/**
 * Interface representing a general toy.
 * Defines methods that must be implemented by any toy class.
 * These methods provide functionality for managing the toy's attributes such as product code, name, wear, happiness, and retirement status.
 * @author Aamir Sohail
 */
public interface IToy{
    public int getProductCode();
    public String getName();
    public int getHappiness();
    public boolean isRetired();
    public double getWear();
    public void increaseWear(double amount);
    public void play(int time);
}
