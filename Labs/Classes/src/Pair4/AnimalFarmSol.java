package Pair4;
import java.util.ArrayList;

/**
 * CSCI-142 Computer Science 2 Recitation Exercise
 * 04-Inheritance
 * Animal Farm
 *
 * A test program for the 2 kinds of animals, e.g. camels and penguins.
 * The animals that possess a particular behavior, e.g. running,
 * speaking (Animal), diving (Swimmer) are exercised.
 *
 * In the end the unique behavior of each animal is exercised individually
 * and the total distance all animals travelled is computed/displayed.
 *
 * This is the solution code.
 *
 * $ java AnimalFarmSol
 * Farm: camelMilk=0.0, penguinFish=0, duckHappiness=0
 * 	Animal{name='Joe, distance=0.0', Camel{numHumps=1, litersMilked=0.0}}
 * 	Animal{name='Pingu, distance=0.0', Penguin{beakLength=10, fishEaten=0}}
 * 	Animal{name='Daffy, distance=0.0', Duck{wingSpan=5.0, happiness=0}}
 * 	Animal{name='Larry, distance=0.0', Camel{numHumps=2, litersMilked=0.0}}
 * 	Animal{name='Tux, distance=0.0', Penguin{beakLength=20, fishEaten=0}}
 * 	Animal{name='Darkwing, distance=0.0', Duck{wingSpan=20.0, happiness=0}}
 *
 * RUNNING and SPEAKING the animals for 5 seconds each...
 * 	Animal{name='Joe, distance=89.40799999999999', Camel{numHumps=1, litersMilked=0.0}} says, "grunt!, grunt!"
 * 	Animal{name='Pingu, distance=15.6464', Penguin{beakLength=10, fishEaten=0}} says, "gak! gak!"
 * 	Animal{name='Daffy, distance=74.73825', Duck{wingSpan=5.0, happiness=0}} says "quack!, quack!"
 * 	Animal{name='Larry, distance=44.70399999999999', Camel{numHumps=2, litersMilked=0.0}} says, "grunt!, grunt!"
 * 	Animal{name='Tux, distance=15.6464', Penguin{beakLength=20, fishEaten=0}} says, "gak! gak!"
 * 	Animal{name='Darkwing, distance=89.73825', Duck{wingSpan=20.0, happiness=0}} says "quack!, quack!"
 *
 * Farm: camelMilk=0.0, penguinFish=0, duckHappiness=0
 * 	Animal{name='Joe, distance=89.40799999999999', Camel{numHumps=1, litersMilked=0.0}}
 * 	Animal{name='Pingu, distance=15.6464', Penguin{beakLength=10, fishEaten=0}}
 * 	Animal{name='Daffy, distance=74.73825', Duck{wingSpan=5.0, happiness=0}}
 * 	Animal{name='Larry, distance=44.70399999999999', Camel{numHumps=2, litersMilked=0.0}}
 * 	Animal{name='Tux, distance=15.6464', Penguin{beakLength=20, fishEaten=0}}
 * 	Animal{name='Darkwing, distance=89.73825', Duck{wingSpan=20.0, happiness=0}}
 *
 * SWIMMING the animals for 10 minutes each...
 * 	Animal{name='Pingu, distance=1222.6544000000001', Penguin{beakLength=10, fishEaten=400}}
 * 	Animal{name='Tux, distance=1222.6544000000001', Penguin{beakLength=20, fishEaten=800}}
 * 	Animal{name='Daffy, distance=342.96225', Duck{wingSpan=5.0, happiness=0}}
 * 	Animal{name='Darkwing, distance=357.96225', Duck{wingSpan=20.0, happiness=0}}
 *
 * Farm: camelMilk=0.0, penguinFish=0, duckHappiness=0
 * 	Animal{name='Joe, distance=89.40799999999999', Camel{numHumps=1, litersMilked=0.0}}
 * 	Animal{name='Pingu, distance=1222.6544000000001', Penguin{beakLength=10, fishEaten=400}}
 * 	Animal{name='Daffy, distance=342.96225', Duck{wingSpan=5.0, happiness=0}}
 * 	Animal{name='Larry, distance=44.70399999999999', Camel{numHumps=2, litersMilked=0.0}}
 * 	Animal{name='Tux, distance=1222.6544000000001', Penguin{beakLength=20, fishEaten=800}}
 * 	Animal{name='Darkwing, distance=357.96225', Duck{wingSpan=20.0, happiness=0}}
 *
 * FLYING animals for 10 seconds each...
 * 	Animal{name='Daffy, distance=1460.5622500000002', Duck{wingSpan=5.0, happiness=0}}
 * 	Animal{name='Darkwing, distance=4828.36225', Duck{wingSpan=20.0, happiness=0}}
 *
 * Farm: camelMilk=0.0, penguinFish=0, duckHappiness=0
 * 	Animal{name='Joe, distance=89.40799999999999', Camel{numHumps=1, litersMilked=0.0}}
 * 	Animal{name='Pingu, distance=1222.6544000000001', Penguin{beakLength=10, fishEaten=400}}
 * 	Animal{name='Daffy, distance=1460.5622500000002', Duck{wingSpan=5.0, happiness=0}}
 * 	Animal{name='Larry, distance=44.70399999999999', Camel{numHumps=2, litersMilked=0.0}}
 * 	Animal{name='Tux, distance=1222.6544000000001', Penguin{beakLength=20, fishEaten=800}}
 * 	Animal{name='Darkwing, distance=4828.36225', Duck{wingSpan=20.0, happiness=0}}
 *
 * INTERACTING with animals uniquely...
 * 	Milking camel for 30 seconds...
 * 		Animal{name='Joe, distance=89.40799999999999', Camel{numHumps=1, litersMilked=261.19350000000003}} says, "grunt!, grunt!"
 * 		Animal{name='Larry, distance=44.70399999999999', Camel{numHumps=2, litersMilked=261.19350000000003}} says, "grunt!, grunt!"
 * 	Getting fish eaten from penguin...
 * 		Animal{name='Pingu, distance=1222.6544000000001', Penguin{beakLength=10, fishEaten=400}} says, "gak! gak!"
 * 		Animal{name='Tux, distance=1222.6544000000001', Penguin{beakLength=20, fishEaten=800}} says, "gak! gak!"
 * 	Petting the ducks for 120 seconds
 * 		Animal{name='Daffy, distance=1460.5622500000002', Duck{wingSpan=5.0, happiness=600}} says "quack!, quack!"
 * 		Animal{name='Darkwing, distance=4828.36225', Duck{wingSpan=20.0, happiness=2400}} says "quack!, quack!"
 *
 * Farm: camelMilk=522.3870000000001, penguinFish=1200, duckHappiness=3000
 * 	Animal{name='Joe, distance=89.40799999999999', Camel{numHumps=1, litersMilked=261.19350000000003}}
 * 	Animal{name='Pingu, distance=1222.6544000000001', Penguin{beakLength=10, fishEaten=400}}
 * 	Animal{name='Daffy, distance=1460.5622500000002', Duck{wingSpan=5.0, happiness=600}}
 * 	Animal{name='Larry, distance=44.70399999999999', Camel{numHumps=2, litersMilked=261.19350000000003}}
 * 	Animal{name='Tux, distance=1222.6544000000001', Penguin{beakLength=20, fishEaten=800}}
 * 	Animal{name='Darkwing, distance=4828.36225', Duck{wingSpan=20.0, happiness=2400}}
 *
 * Total distance all animals travelled: 8868.3453
 *
 * @author RIT CS
 */
public class AnimalFarmSol {
    /** the collection of animals */
    private ArrayList<Animal> animals;
    /** the collection of swimmers */
    private ArrayList<Swimmer> swimmers;
    /** the collection of flyers */
    private ArrayList<Flyer> flyers;
    /** the collection of camels */
    private ArrayList<Camel> camels;
    /** the collection of penguins */
    private ArrayList<Penguin> penguins;
    /** the collection of ducks */
    private ArrayList<Duck> ducks;
    /** how much milk the camels produced */
    private double camelMilk;
    /** how many fish the penguins are */
    private int penguinFish;
    /** how happy the ducks are */
    private int duckHappiness;

    /**
     * Create the farm and add the animals.
     */
    public AnimalFarmSol() {
        this.animals = new ArrayList<>();
        this.swimmers = new ArrayList<>();
        this.flyers = new ArrayList<>();
        this.camels = new ArrayList<>();
        this.penguins = new ArrayList<>();
        this.ducks = new ArrayList<>();
        this.camelMilk = 0;
        this.penguinFish = 0;
        this.duckHappiness = 0;

        // create unique references to each animal
        Camel joe = new Camel(1, "Joe");
        Camel larry = new Camel(2, "Larry");
        Penguin pingu = new Penguin(10, "Pingu");
        Penguin tux = new Penguin(20, "Tux");
        Duck daffy = new Duck(5, "Daffy");
        Duck darkwing = new Duck(20, "Darkwing");

        // add the animals
        this.animals.add(joe);
        this.animals.add(pingu);
        this.animals.add(daffy);
        this.animals.add(larry);
        this.animals.add(tux);
        this.animals.add(darkwing);

        // add the swimmers
        this.swimmers.add(pingu);
        this.swimmers.add(tux);
        this.swimmers.add(daffy);
        this.swimmers.add(darkwing);

        // add the flyers
        this.flyers.add(daffy);
        this.flyers.add(darkwing);

        // add the camels
        this.camels.add(joe);
        this.camels.add(larry);

        // add the penguins
        this.penguins.add(pingu);
        this.penguins.add(tux);

        // add the ducks
        this.ducks.add(daffy);
        this.ducks.add(darkwing);
    }

    /**
     * Run and speak all animals in the farm.  This includes all of the
     * animals since they all implement the Animal interface.
     */
    private void runAndSpeakAnimals() {
        System.out.println("RUNNING and SPEAKING the animals for 5" +
                " seconds each...");
        for (Animal animal : this.animals) {
            animal.runAndSpeak(5);
        }
    }

    /**
     * For animals in the farm that implement the Swimmer interface, have
     * them swim for 10 minutes each.
     */
    private void swimAnimals() {
        System.out.println("SWIMMING the animals for 10 minutes each...");
        for (Swimmer swimmer : this.swimmers) {
            swimmer.dive(10);
            System.out.println("\t" + swimmer);
        }
    }

    /**
     * For animals in the farm that implement the Flyer interface, have them
     * fly for 20 seconds each.
     */
    private void flyAnimals() {
        System.out.println("FLYING animals for 10 seconds each...");
        for (Flyer flyer : this.flyers) {
            flyer.fly(10);
            System.out.println("\t" + flyer);
        }
    }

    /**
     * Each different kind of animal has a unique behavior, i.e. the camel
     * can be milked (30 seconds), and the penguin can eat fish.
     */
    public void interactAnimals() {
        System.out.println("INTERACTING with animals uniquely...");
        System.out.println("\tMilking camel for 30 seconds...");
        for (Camel camel : this.camels) {
            camel.milk(30);
            this.camelMilk += camel.getLitersMilked();
            System.out.println("\t\t" + camel.speak());
        }

        System.out.println("\tGetting fish eaten from penguin...");
        for (Penguin penguin : this.penguins) {
            this.penguinFish += penguin.getFishEaten();
            System.out.println("\t\t" + penguin.speak());
        }

        System.out.println("\tPetting the ducks for 120 seconds");
        for (Duck duck : this.ducks) {
            duck.pet(120);
            this.penguinFish += duck.getFishEaten(); // should be 0
            this.duckHappiness += duck.getHappiness();
            System.out.println("\t\t" + duck.speak());
        }
    }

    /**
     * Get the total distance all animals travelled.
     *
     * @return total distance
     */
    public double getTotalDistanceTravelled() {
        double total = 0;
        for (Animal animal : this.animals) {
            total += animal.getDistance();
        }
        return total;
    }

    /**
     * Return a string representation of the animal farm.  This includes
     * all the statistics of the farm and the state of each animal, e.g.
     * at the start:
     *
     * camelMilk=0.0, penguinFish=0, duckHappiness=0
     * Animal{name='Joe, distance=0.0', Camel{numHumps=1, litersMilked=0.0}}
     * Animal{name='Pingu, distance=0.0', Penguin{beakLength=10, fishEaten=0}}
     * Animal{name='Daffy, distance=0.0', Duck{wingSpan=5.0, happiness=0}}
     * Animal{name='Larry, distance=0.0', Camel{numHumps=2, litersMilked=0.0}}
     * Animal{name='Tux, distance=0.0', Penguin{beakLength=20, fishEaten=0}}
     * Animal{name='Darkwing, distance=0.0', Duck{wingSpan=20.0, happiness=0}}
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        String result = "camelMilk=" + this.camelMilk +
                ", penguinFish=" + this.penguinFish +
                ", duckHappiness=" + this.duckHappiness +
                "\n";
        for (Animal animal : this.animals) {
            result += "\t" + animal.toString() + "\n";
        }
        return result;
    }

    /**
     * The main program exercises the animals in various ways and prints out
     * the farm after each different exercise.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        AnimalFarmSol farm = new AnimalFarmSol();
        System.out.println("\nFarm: " + farm);
        farm.runAndSpeakAnimals();
        System.out.println("\nFarm: " + farm);
        farm.swimAnimals();
        System.out.println("\nFarm: " + farm);
        farm.flyAnimals();
        System.out.println("\nFarm: " + farm);
        farm.interactAnimals();
        System.out.println("\nFarm: " + farm);
        System.out.println("Total distance all animals travelled: " +
                farm.getTotalDistanceTravelled());
    }
}


