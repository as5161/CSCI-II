package hotpotato;

import rit.cs.CircularList;
import rit.cs.CircularListLinkedList;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The main program for the game of hot potato.  It takes on the command line the
 * number of players and seed (0 for no seed - truly random) to create the game
 * and then plays the game to completion.
 *
 * @author RIT CS
 * @author Aamir Sohail
 * */
public class HotPotato {
    /** the maximum number of players */
    private final static int MAX_PLAYERS = 20;
    /** the maximum number of passes that can be made in a single round */
    private final static int MAX_PASSES = 10;
    /** use this if not seeding the random number generator for truly random game play */
    private final static int NO_SEED = 0;

    /** A collection of 20 players */
    private final static List<Player> players =
            Arrays.asList(
                    new Player("Alakazam", 0),
                    new Player("Bulbasaur", 1),
                    new Player("Charmander", 2),
                    new Player("Dragonite", 3),
                    new Player("Eevee", 4),
                    new Player("Fennekin", 5),
                    new Player("Gyarados", 6),
                    new Player("Hitmonlee", 7),
                    new Player("Ivysaur", 8),
                    new Player("Jigglypuff", 9),
                    new Player("Kadabra", 10),
                    new Player("Lapras", 11),
                    new Player("Mewtwo", 12),
                    new Player("Nidoking", 13),
                    new Player("Onix", 14),
                    new Player("Pidgeot", 15),
                    new Player("Quilava", 16),
                    new Player("Raichu", 17),
                    new Player("Squirtle", 18),
                    new Player("Typhlosion", 19));
    /** the random number generator */
    private Random random;
    /** the current round number, starting at 1 */
    private int round;
    /** the circle of players */
    private CircularList<Player> circle;

    /**
     * Initialize the game by creating the circle and adding players in order
     * from the above list of players.  Also creates the random number generator
     * with a seed (or not).
     * @see CircularListLinkedList#CircularListLinkedList()
     * @see CircularListLinkedList#append(Object)
     * @param size the size of the circle
     * @param seed the seed of the random number generator
     */
    public HotPotato(int size, int seed) {
        // create the initial circle as a circular list linked list as empty
        this.circle = new CircularListLinkedList<Player>();

        for (int i = 0; i< size; i++){
            this.circle.append(players.get(i));
        }
        if (seed == NO_SEED) {
            this.random = new Random();
        } else {
            this.random = new Random(seed);
        }

        // current round number is 1
        this.round = 1;
    }

    /**
     * Generate a random number from 0 to MAX_PASSES, inclusive.  This is
     * used to determine for the current round how many times to pass the
     * hot potato.
     * @return a random number between 0 and MAX_PASSES
     */
    public int getRandomNumPasses() {
        return this.random.nextInt(MAX_PASSES+1);
    }

    /**
     * Display to standard output the round, number of players still active,
     * and the players in the circle from front to back.
     * @see CircularListLinkedList#size()
     * @see CircularListLinkedList#toString()
     */
    public void display() {
        System.out.println("ROUND " + this.round);
        System.out.println("========");
        System.out.println(this.circle.size() + " player/s remain:");
        System.out.println(this.circle);
    }

    /**
     * Play the game until the size of the circle has just one player left.
     * For each round, display the current circle, then get the random number of passes
     * to move the cursor in the current direction (right then left, alternating).
     * Remove the loser at the cursor and move the cursor in the current direction.
     * See the examples in the output directory for more details.
     *
     * @see HotPotato#display()
     * @see HotPotato#getRandomNumPasses()
     * @see CircularListLinkedList#size()
     * @see CircularListLinkedList#get()
     * @see CircularListLinkedList#forward()
     * @see CircularListLinkedList#backward()
     * @see CircularListLinkedList#removeForward()
     * @see CircularListLinkedList#removeBackward()
     */
    public void playGame() {
        boolean left = false;
        while (this.circle.size() > 1){
            int pass = getRandomNumPasses();
            if (!left) {
                display();
                System.out.println("Passing potato " + pass + " times RIGHT:");
                for (int i = 0; i < pass; i++) {
                    System.out.println("\t:" + this.circle.get().toString() + " has potato and passing RIGHT");
                    this.circle.forward();
                }
                System.out.println(this.circle.get().toString() + " lost!");
                this.circle.removeForward();
                System.out.println();
                left = true;
            }else{
                display();
                System.out.println("Passing potato " + pass + " times LEFT:");
                for (int i = 0; i < pass; i++){
                        System.out.println("\t:" + this.circle.get().toString() + " has potato and passing LEFT");
                        this.circle.backward();
                    }
                System.out.println(this.circle.get().toString() + " lost!");
                System.out.println();
                this.circle.removeBackward();
                    left = false;
                }
            round++;

        }
        display();
        System.out.println(this.circle.get().toString() + " wins!");

    }

    /**
     * The main method expects the number of players and the seed on the command line.
     * If everything is in order the game is created and played.
     * @param args command line arguments containing number of players and random number generator seed.
     * @see HotPotato#HotPotato(int, int)
     * @see HotPotato#playGame()
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java HotPotato #players seed");
        } else {
            int size = Integer.parseInt(args[0]);
            int seed = Integer.parseInt(args[1]);

            System.out.println("Welcome to Pokemon Hot Potato v1.0 :)");
            System.out.println("Number of players: " + size + ", Seed: " + ((seed != NO_SEED) ? seed : "NO SEED!"));
            System.out.println();

            if (size < 0 || size > MAX_PLAYERS) {
                System.out.println("Number of players must be between 1 and " + MAX_PLAYERS);
            } else {
                HotPotato game = new HotPotato(size, seed);
                game.playGame();
            }
        }
    }
}