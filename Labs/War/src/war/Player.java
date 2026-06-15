package war;

/**
 * The Player class represents a player in the War card game.
 * Each player has a pile of cards and can perform actions such as
 * adding cards to their pile, drawing cards, and checking if they have cards remaining.
 * A player can also be marked as the winner.
 * @author RIT CS
 * @author Aamir Sohail
 */
public class Player {
    private final Pile pile;
    private boolean winner;

    /**
     * Constructs a Player with a given ID.
     * Initializes the player's pile with the name "P{id}" where {id} is the player's ID.
     * The player is initially not a winner.
     *
     * @param id the unique ID for this player
     */
    public Player(int id) {
        this.pile = new Pile("P" + id);
        this.winner = false;
    }

    /**
     * Adds a single card to the player's pile.
     *
     * @param card the card to add to the pile
     */
    public void addCard(Card card) {
        this.pile.addCard(card);
    }

    /**
     * Adds multiple cards from another pile to the player's pile.
     *
     * @param cards the pile containing cards to be added
     */
    public void addCards(Pile cards) {
        for (Card card : cards.getCards()) {
            this.pile.addCard(card);
        }
    }

    /**
     * Draws a card from the player's pile.
     * The card is drawn face up.
     *
     * @return the card drawn from the player's pile
     */
    public Card drawCard() {
        return this.pile.drawCard(true);
    }

    /**
     * Checks if the player has any cards left in their pile.
     *
     * @return true if the player has cards, false otherwise
     */
    public boolean hasCard() {
        return this.pile.hasCard();
    }

    /**
     * Checks if the player is the winner.
     *
     * @return true if the player is marked as the winner, false otherwise
     */
    public boolean isWinner() {
        return this.winner;
    }

    /**
     * Marks the player as the winner.
     */
    public void setWinner() {
        this.winner = true;
    }

    /**
     * Returns a string representation of the player's pile.
     *
     * @return a string representing the player's pile
     */
    @Override
    public String toString() {
        return this.pile.toString();
    }
}
