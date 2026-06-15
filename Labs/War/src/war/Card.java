package war;

/**
 * Represents a single card in the game.
 *
 * @author RIT CS
 * @author Aamir Sohail
 */
public class Card {
    /**
     * Create the card with the rank and suit.  The card is not face up to begin with.
     *
     * @param rank the card's rank
     * @param suit the card's suit
     */
    private boolean faceUp;
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.faceUp = false;
    }

    /** Set the card to be face up */
    public void setFaceUp() {
        this.faceUp = true;
    }

    /** Set the card to be face down */
    public void setFaceDown() {
        this.faceUp = false;
    }

    /**
     * Is the card face up or not?
     *
     * @return whether the card is faceUp or not.
     */
    public boolean isFaceUp() {
        return faceUp;
    }

    /**
     * Does this card beat (have a higher rank value) than the other card?
     *
     * @param other the card to compare to
     * @return true if this card beats the other card, false otherwise
     */
    public boolean beats(Card other) {
        if (this.rank.getValue() > other.rank.getValue()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Returns the string representation for a Card which contains the rank, suit and whether the
     * card is face up or not.  For example, if the card is the Ace of Clubs and is face up it
     * would return "A♧(U)".  If the card was the Seven of Spades and is face down it would
     * return "7♠(D)".
     *
     * @return the string described above
     */
    @Override
    public String toString() {
        String side;
        if (this.faceUp == false) {
            side = ("(D)");
        }else{
            side = ("(U)");
        }
        return this.rank.toString() + this.suit + side;
    }

    /**
     * Two cards are equal if they have the same rank (regardless of the suit).
     *
     * @param other the card to compare to for equality
     * @return whether the two cards are equal or not
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Card){
            Card otherCard = (Card) other;
            return this.rank == otherCard.rank;
        }else{
            return false;
        }
    }
}
