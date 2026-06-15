package hotpotato;

/**
 * @author RIT CS
 * Represents a player in the game.
 */
public class Player {
    private String name;
    private int id;

    /**
     * Constructor to initialize a player with a name and ID.
     *
     * @param name The player's name
     * @param id The player's unique ID
     */
    public Player(String name, int id){
        this.name = name;
        this.id = id;
    }

    /**
     * Gets the player's name.
     *
     * @return The name of the player
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the player's ID.
     *
     * @return The ID of the player
     */
    public int getId(){

        return this.id;
    }

    /**
     * Compares this player to another object for equality.
     * Two players are considered equal if they have the same name and ID.
     *
     * @param other The object to compare to
     * @return true if both objects are players with the same name and ID, false otherwise
     */
    public boolean equals(Object other){
        if (other instanceof Player) {
            Player two = (Player) other;
            if (this.name == two.name && this.id == two.id) {
                return true;
            }
        }
        return false;
    }

    public boolean equals (Object other){
        if (other instanceof Player){
            Player two = (Player) other;
            if (th)
        }
    }
    /**
     * Returns a string representation of the player in the format: name(ID).
     *
     * @return A formatted string representing the player
     */
    public String toString(){
        return this.name + "(" + this.id + ")";
    }
}
