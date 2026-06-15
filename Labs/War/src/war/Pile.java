
package war;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The Pile class represents a collection of cards in the game.
 * It provides methods to manage a pile of cards, including adding cards,
 * drawing cards, shuffling, and checking the pile's contents.
 * @author RIT CS
 * @author Aamir Sohail
 */

public class Pile {
    private final ArrayList<Card> cards;
    private final String name;
    private static Random rng;


    /**
     * Constructor to create a new Pile with the given name.
     *
     * @param name the name of the pile
     */
    public Pile(String name) {
        this.name = name;
        this.cards = new ArrayList<Card>();
    }

    /**
     * Adds a card to the pile.
     *
     * @param card the card to be added to the pile
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Clears all cards from the pile.
     */
    public void clear() {
        this.cards.clear();
    }

    /**
     * Draws the top card from the pile, sets it face-up or face-down, and returns it.
     * If the card is already face-up, the pile will be shuffled first.
     *
     * @param faceUp boolean indicating whether the drawn card should be face-up (true) or face-down (false)
     * @return the top card from the pile
     */
    public Card drawCard(boolean faceUp) {
        Card topCard = this.cards.get(0);
        if (topCard.isFaceUp()) {
            shuffle();
            topCard = this.cards.get(0);
            System.out.println(this);
        }
        topCard = this.cards.remove(0);

        if (faceUp) {
            topCard.setFaceUp();
        } else {
            topCard.setFaceDown();
        }
        return topCard;
    }

    /**
     * Returns the list of cards in the pile.
     *
     * @return the list of cards in the pile
     */
    public ArrayList<Card> getCards() {
        return this.cards;
    }

    /**
     * Checks if the pile contains any cards.
     *
     * @return true if the pile has one or more cards, false otherwise
     */
    public boolean hasCard() {
        return !this.cards.isEmpty();
    }

    /**
     * Sets the seed for the random number generator used for shuffling.
     *
     * @param seed the seed value for the random number generator
     */
    public static void setSeed(long seed) {
        rng = new Random(seed);
    }

    /**
     * Shuffles the pile of cards and sets all cards face-down.
     * This uses the random number generator set by setSeed().
     */
    public void shuffle() {
        Collections.shuffle(this.cards, rng);
        for (Card card : cards) {
            card.setFaceDown();
        }
        System.out.println("Shuffling " + name + " pile");
    }

    /**
     * Returns a string representation of the pile with all cards in it.
     *
     * @return a string representation of the pile
     */
    public String toString() {
        String total = name + " pile: ";
        for (int i = 0; i < this.cards.size(); i++) {
            total += cards.get(i).toString();
            total += " ";
        }
        return total;
    }
}