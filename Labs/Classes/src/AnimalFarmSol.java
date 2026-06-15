import java.util.ArrayList;

/**
 * CSCI-142 Computer Science 2 Recitation Exercise
 * 03-InterfacesGenerics
 * AnimalFarmSol
 *
 * A test program for the 3 kinds of animals, e.g. camels, penguins and ducks.
 * The animals that possess a particular behavior, e.g. running,
 * speaking (Animal), diving (Swimmer), flying (Flyer) are exercised.
 * In the end the unique behavior of each animal is exercised individually.
 *
 * This is the solution code.
 *
 * $ java AnimalFarmSol
 *
 * Farm: distanceRan=0.0, distanceSwam=0.0, distanceFlown=0.0,
 *     camelMilk=0.0, penguinFish=0, duckHappiness=0
 * 	Camel{numHumps=1, litersMilked=0.0}
 * 	Penguin{beakLength=10, fishEaten=0}
 * 	Duck{wingSpan=5.0, happiness=0}
 * 	Camel{numHumps=2, litersMilked=0.0}
 * 	Penguin{beakLength=20, fishEaten=0}
 * 	Duck{wingSpan=10.0, happiness=0}
 *
 * RUNNING the animals for 5 seconds each...
 * 	Camel{numHumps=1, litersMilked=0.0} says, "grunt!, grunt!"
 * 	Penguin{beakLength=10, fishEaten=0} says, "gak! gak!"
 * 	Duck{wingSpan=5.0, happiness=0} says "quack!, quack!"
 * 	Camel{numHumps=2, litersMilked=0.0} says, "grunt!, grunt!"
 * 	Penguin{beakLength=20, fishEaten=0} says, "gak! gak!"
 * 	Duck{wingSpan=10.0, happiness=0} says "quack!, quack!"
 *
 * Farm: distanceRan=319.8813, distanceSwam=0.0, distanceFlown=0.0,
 *     camelMilk=0.0, penguinFish=0, duckHappiness=0
 * 	Camel{numHumps=1, litersMilked=0.0}
 * 	Penguin{beakLength=10, fishEaten=0}
 * 	Duck{wingSpan=5.0, happiness=0}
 * 	Camel{numHumps=2, litersMilked=0.0}
 * 	Penguin{beakLength=20, fishEaten=0}
 * 	Duck{wingSpan=10.0, happiness=0}
 *
 * SWIMMING the animals for 10 minutes each...
 * 	Camel{numHumps=1, litersMilked=0.0} can't swim!
 * 	Penguin{beakLength=10, fishEaten=400} says, "gak! gak!"
 * 	Duck{wingSpan=5.0, happiness=0} says "quack!, quack!"
 * 	Camel{numHumps=2, litersMilked=0.0} can't swim!
 * 	Penguin{beakLength=20, fishEaten=800} says, "gak! gak!"
 * 	Duck{wingSpan=10.0, happiness=0} says "quack!, quack!"
 *
 * Farm: distanceRan=319.8813, distanceSwam=2950.464, distanceFlown=0.0,
 *     camelMilk=0.0, penguinFish=0, duckHappiness=0
 * 	Camel{numHumps=1, litersMilked=0.0}
 * 	Penguin{beakLength=10, fishEaten=400}
 * 	Duck{wingSpan=5.0, happiness=0}
 * 	Camel{numHumps=2, litersMilked=0.0}
 * 	Penguin{beakLength=20, fishEaten=800}
 * 	Duck{wingSpan=10.0, happiness=0}
 *
 * FLYING animals for 10 seconds each...
 * 	Camel{numHumps=1, litersMilked=0.0} can't fly!
 * 	Penguin{beakLength=10, fishEaten=400} can't fly!
 * 	Duck{wingSpan=5.0, happiness=0} says "quack!, quack!"
 * 	Camel{numHumps=2, litersMilked=0.0} can't fly!
 * 	Penguin{beakLength=20, fishEaten=800} can't fly!
 * 	Duck{wingSpan=10.0, happiness=0} says "quack!, quack!"
 *
 * Farm: distanceRan=319.8813, distanceSwam=2950.464, distanceFlown=3352.8,
 *     camelMilk=0.0, penguinFish=0, duckHappiness=0
 * 	Camel{numHumps=1, litersMilked=0.0}
 * 	Penguin{beakLength=10, fishEaten=400}
 * 	Duck{wingSpan=5.0, happiness=0}
 * 	Camel{numHumps=2, litersMilked=0.0}
 * 	Penguin{beakLength=20, fishEaten=800}
 * 	Duck{wingSpan=10.0, happiness=0}
 *
 * INTERACTING with animals uniquely...
 * 	Milking camel for 30 seconds...
 * 		Camel{numHumps=1, litersMilked=261.19350000000003} says, "grunt!, grunt!"
 * 	Getting fish eaten from penguin...
 * 		Penguin{beakLength=10, fishEaten=400} says, "gak! gak!"
 * 	Petting duck for 120 seconds
 * 		Duck{wingSpan=5.0, happiness=600} says "quack!, quack!"
 * 	Milking camel for 30 seconds...
 * 		Camel{numHumps=2, litersMilked=261.19350000000003} says, "grunt!, grunt!"
 * 	Getting fish eaten from penguin...
 * 		Penguin{beakLength=20, fishEaten=800} says, "gak! gak!"
 * 	Petting duck for 120 seconds
 * 		Duck{wingSpan=10.0, happiness=1200} says "quack!, quack!"
 *
 * Farm: distanceRan=319.8813, distanceSwam=2950.464, distanceFlown=3352.8,
 *     camelMilk=522.3870000000001, penguinFish=1200, duckHappiness=1800
 * 	Camel{numHumps=1, litersMilked=261.19350000000003}
 * 	Penguin{beakLength=10, fishEaten=400}
 * 	Duck{wingSpan=5.0, happiness=600}
 * 	Camel{numHumps=2, litersMilked=261.19350000000003}
 * 	Penguin{beakLength=20, fishEaten=800}
 * 	Duck{wingSpan=10.0, happiness=1200}
 *
 * @author RIT CS
 */
public class AnimalFarmSol {
    /** the collection of animals */
    private ArrayList<Animal> animals;
    /** the total distance the animals ran */
    private double distanceRan;
    /** the total distance the animals swam */
    private double distanceSwam;
    /** the total distance the animals flew */
    private double distanceFlown;
    /** how much milk the camels produced */
    private double camelMilk;
    /** how many fish the penguins are */
    private int penguinFish;
    /** the total happiness of all the ducks from petting */
    private int duckHappiness;

    /**
     * Create the farm and add the animals.
     */
    public AnimalFarmSol() {
        this.animals = new ArrayList<>();
        this.distanceRan = 0;
        this.distanceSwam = 0;
        this.distanceFlown = 0;
        this.camelMilk = 0;
        this.penguinFish = 0;
        this.duckHappiness = 0;

        this.animals.add(new Camel(1));
        this.animals.add(new Penguin(10));
        this.animals.add(new Duck(5));
        this.animals.add(new Camel(2));
        this.animals.add(new Penguin(20));
        this.animals.add(new Duck(10));
    }

    /**
     * Run all animals in the farm that can run.  This includes all of the
     * animals since they all implement the Animal interface.
     */
    private void runAnimals() {
        System.out.println("RUNNING the animals for 5 seconds each...");
        for (Animal animal : this.animals) {
            this.distanceRan += animal.run(5);
            System.out.println("\t" + animal.speak());
        }
    }

    /**
     * For animals in the farm that implement the Swimmer interface, have
     * them swim for 10 minutes each.
     */
    private void swimAnimals() {
        System.out.println("SWIMMING the animals for 10 minutes each...");
        for (Animal animal : this.animals) {
            if (animal instanceof Swimmer) {
                Swimmer swimmer = (Swimmer) animal;
                this.distanceSwam += swimmer.dive(10);
                System.out.println("\t" + animal.speak());
            } else {
                System.out.println("\t" + animal + " can't swim!");

            }
        }
    }

    /**
     * For animals in the farm that implement the Flyer interface, have them
     * fly for 20 minutes each.
     */
    private void flyAnimals() {
        System.out.println("FLYING animals for 10 seconds each...");
        for (Animal animal : this.animals) {
            if (animal instanceof Flyer) {
                Flyer flyer = (Flyer) animal;
                this.distanceFlown += flyer.fly(10);
                System.out.println("\t" + animal.speak());
            } else {
                System.out.println("\t" + animal + " can't fly!");
            }
        }
    }

    /**
     * Each different kind of animal has a unique behavior, i.e. the camel
     * can be milked (30 seconds), the penguin can eat fish, the ducks can
     * be petted (120 seconds) to increase happiness.
     */
    public void interactAnimals() {
        System.out.println("INTERACTING with animals uniquely...");
        for (Animal animal : this.animals) {
            if (animal instanceof Camel) {
                System.out.println("\tMilking camel for 30 seconds...");
                Camel camel = (Camel) animal;
                camel.milk(30);
                this.camelMilk += camel.getLitersMilked();
            } else if (animal instanceof Penguin) {
                System.out.println("\tGetting fish eaten from penguin...");
                Penguin penguin = (Penguin) animal;
                this.penguinFish += penguin.getFishEaten();
            } else { // assume animal is a Duck
                System.out.println("\tPetting duck for 120 seconds");
                Duck duck = (Duck) animal;
                duck.pet(120);
                this.duckHappiness += duck.getHappiness();
            }
            System.out.println("\t\t" + animal.speak());
        }
    }

    /**
     * Return a string representation of the animal farm.  This includes
     * all the statistics of the farm and the state of each animal, e.g.
     * at the start:
     *
     * "distanceRan=0.0, distanceSwam=0.0, distanceFlown=0.0, camelMilk=0.0,
     *      penguinFish=0, duckHappiness=0
     * 	Camel{numHumps=1, litersMilked=0.0}
     * 	Penguin{beakLength=10, fishEaten=0}
     * 	Duck{wingSpan=5.0, happiness=0}
     * 	Camel{numHumps=2, litersMilked=0.0}
     * 	Penguin{beakLength=20, fishEaten=0}
     * 	Duck{wingSpan=10.0, happiness=0}"
     *
     * @return the string representation
     *
     */
    @Override
    public String toString() {
        String result = "distanceRan=" + this.distanceRan +
                ", distanceSwam=" + this.distanceSwam +
                ", distanceFlown=" + this.distanceFlown +
                ", camelMilk=" + this.camelMilk +
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
        System.out.println("Farm: " + farm);
        farm.runAnimals();
        System.out.println("\nFarm: " + farm);
        farm.swimAnimals();
        System.out.println("\nFarm: " + farm);
        farm.flyAnimals();
        System.out.println("\nFarm: " + farm);
        farm.interactAnimals();
        System.out.println("\nFarm: " + farm);
    }
}


