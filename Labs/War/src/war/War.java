package war;

/**
 * The main program for the card game, War.  It is run on the command line as:<br>
 * <br>
 * java War cards-per-player seed<br>
 * <br>
 *
 * @author RIT CS
 * @author Aamir Sohail 
 */

public class War {
    /** The maximum number of cards a single player can have */
    public final static int MAX_CARDS_PER_PLAYER = 26;
    private final Player p1;
    private final Player p2;
    private int round;

    /**
     * Initialize the game.
     *
     * @param cardsPerPlayer the number of cards for a single player
     */
    public War(int cardsPerPlayer) {
        Pile deck = new Pile("Initial");
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                deck.addCard(new Card(rank, suit));
            }
        }
        deck.shuffle();
        System.out.println(deck);
        this.p1 = new Player(1);
        this.p2 = new Player(2);

        for (int i = 0; i < cardsPerPlayer; i++) {
            p1.addCard(deck.drawCard(false));
            p2.addCard(deck.drawCard(false));
        }
        round = 1;
    }

    /**
     * Play a single round of War.
     */
    private void playRound() {
        Pile trick = new Pile("Trick");
        System.out.println(p1);
        System.out.println(p2);
        Card p1Card = p1.drawCard();
        Card p2Card = p2.drawCard();
        System.out.println("P1 card: " + p1Card);
        System.out.println("P2 card: " + p2Card);
        trick.addCard(p1Card);
        trick.addCard(p2Card);

        if (p1Card.beats(p2Card)) {
            p1.addCards(trick);
            System.out.println("P1 wins round gets " + trick);
        } else if (p2Card.beats(p1Card)) {
            p2.addCards(trick);
            System.out.println("P2 wins round gets " + trick);
        } else {
            System.out.println("WAR!");
            System.out.println(p1);
            System.out.println(p2);
            while (p1.hasCard() && p2.hasCard()) {
                if (!p1.hasCard()) {
                    p2.addCards(trick);
                    p2.addCard(p1Card);
                    p2.addCard(p2Card);
                    System.out.println("Player 1 has no cards for WAR. Player 2 wins the trick: " + trick);
                    return;
                }

                if (!p2.hasCard()) {
                    p1.addCards(trick);
                    p1.addCard(p1Card);
                    p1.addCard(p2Card);
                    System.out.println("Player 2 has no cards for WAR. Player 1 wins the trick: " + trick);
                    return;
                }

                Card p1WarCard = p1.drawCard();
                Card p2WarCard = p2.drawCard();
                System.out.println("P1 card: " + p1WarCard);
                System.out.println("P2 card: " + p2WarCard);
                trick.addCard(p1WarCard);
                trick.addCard(p2WarCard);

                if (p1WarCard.beats(p2WarCard)) {
                    p1.addCards(trick);
                    System.out.println("P1 wins round gets " + trick);
                    return;
                }
                else if (p2WarCard.beats(p1WarCard)) {
                    p2.addCards(trick);
                    System.out.println("P2 wins the war and gets " + trick);
                    return;
                }
                else {
                    System.out.println("War continues!");
                }

            }
            if (!p1.hasCard()){
                p2.addCards(trick);
                System.out.println("P2 wins round gets " + trick);
            }else{
                p1.addCards(trick);
                System.out.println("P1 wins round gets " + trick);
            }
        }
    }




    /**
     * Play the full game.
     */
    public void playGame() {
        while (p1.hasCard() && p2.hasCard()) {
            System.out.println("ROUND " + round);
            playRound();
            round++;
        }
        System.out.println(p1);
        System.out.println(p2);

        if (p1.hasCard()) {
            System.out.println("P1 WINS!");
        } else {
            System.out.println("P2 WINS!");
        }    }

    /**
     * The main method get the command line arguments, then constructs
     * and plays the game.  The Pile's random number generator and seed
     * need should get set here using Pile.setSeed(seed).
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java War cards-per-player seed");
        } else {
            int cardsPerPlayer = Integer.parseInt(args[0]);
            if (cardsPerPlayer <= 0 || cardsPerPlayer > MAX_CARDS_PER_PLAYER) {
                System.out.println("cards-per-player must be between 1 and " + MAX_CARDS_PER_PLAYER);
            } else {
                Pile.setSeed(Integer.parseInt(args[1]));
                War war = new War(cardsPerPlayer);
                war.playGame();
            }
        }
    }
}
