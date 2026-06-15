public class Duck implements Animal, Swimmer, Flyer{
    public static final double FLY_SPEED_MS = 22.352;
    public static final double RUN_SPEED = 13.94765;
    public static final double SWIM_SPEED = 26.8224;
    private double wingSpan;
    private int happiness;

    public Duck(double wingSpan, int happiness)
    {
        this.wingSpan = wingSpan;
        this.happiness = happiness;
    }

    public void pet(int seconds){
        this.happiness +=  seconds*wingSpan;
    }
    public int getHappiness() {
        return this.happiness;
    }

    public double run(int seconds) {
        return seconds * RUN_SPEED;
    }

    public String speak(){
        return toString() + "says \"quack!, quack!\"";
    }

    public double dive(int minutes){
        return minutes*SWIM_SPEED;
    }

    public double fly(int seconds){
        return seconds*wingSpan;
    }

    public String toSting(){

    }
}
