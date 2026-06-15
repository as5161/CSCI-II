public abstract class Vehicle {
    public static final int INITIAL_SPEED = 0;
    private final String brand;
    private int speed;

    protected Vehicle(int speed, String brand){
        this.brand = brand;
        this.speed = INITIAL_SPEED;
    }

    public int getSpeed(){
        return this.speed;
    }

    public String getBrand(){
        return this.brand;
    }
    public void accelerate(int accerate){
        this.speed += accerate;
    }

    public void brake(int slow){
        if (this.speed-slow<0){
            this.speed =0;
        }else {
            this.speed = this.speed-slow;
        }
    }

    protected abstract void specialAction(String actionType);
    public void use(String actionType){
        System.out.print("Using vehicle " + this);

    }

    public String toString(){
        return "Vehicle{Brand " +getBrand() +"Speed: " +getSpeed() + "}";
    }

}
