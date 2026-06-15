package puzzles.tilt.model;
import puzzles.common.Observer;
import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
/**
 * TiltModel manages the state of the Tilt puzzle game.
 * It handles board loading, tilting operations, hint generation,
 * and notifying observers about state changes.
 */
public class TiltModel {
    private final List<Observer<TiltModel, String>> observers = new LinkedList<>();
    private TiltConfig currentConfig;
    private String filename;
    /**
     * Constructs a TiltModel by loading the initial board from a file.
     *
     * @param filename the name of the file containing the puzzle
     * @throws FileNotFoundException if the file is not found
     */
    public TiltModel(String filename) throws FileNotFoundException {
        load(filename);
    }
    /**
     * Adds an observer to the model.
     *
     * @param observer the observer to add
     */
    public void addObserver(Observer<TiltModel, String> observer) {
        observers.add(observer);
    }
    /**
     * Notifies all observers with a given message.
     *
     * @param message the message to send to observers
     */

    private void alertObservers(String message) {
        for (Observer<TiltModel, String> observer : observers) {
            observer.update(this, message);
        }
    }
    /**
     * Loads a new board configuration from a file.
     *
     * @param filename the name of the file to load
     * @throws FileNotFoundException if the file is not found
     */
    public void load(String filename) throws FileNotFoundException {
        this.filename = filename;
        this.currentConfig = new TiltConfig(filename);
        alertObservers("Loaded: " + filename);
    }
    /**
     * Resets the board to its initial loaded state.
     *
     * @throws FileNotFoundException if the initial file cannot be found
     */
    public void reset() throws FileNotFoundException {
        load(this.filename);
    }
    /**
     * Attempts to tilt the board in the specified direction.
     * Updates the configuration and notifies observers of the result.
     *
     * @param dir the direction to tilt ('N', 'S', 'E', or 'W')
     */
    public void tilt(char dir) {
        TiltConfig tryMove = currentConfig.tilt(dir);

        if (tryMove.hasBlueInHole()) {
            alertObservers("Illegal move: blue piece would fall into hole!");
        } else if (tryMove.equals(currentConfig)) {
            alertObservers("Invalid move: no pieces moved.");
        } else {
            currentConfig = tryMove;
            if (currentConfig.isSolution()) {
                alertObservers("You win!");
            } else {
                alertObservers("Board tilted " + dir);
            }
        }
    }

    /**
     * Provides a hint by solving the puzzle and making the next move along the solution path.
     * Notifies observers about the hint or if no solution exists.
     */
    public void hint() {
        Solver solver = new Solver();
        List<Configuration> path = solver.solve(currentConfig);
        if (path == null || path.size() <= 1) {
            alertObservers("No solution from current state.");
        } else {
            currentConfig = (TiltConfig) path.get(1);
            if (currentConfig.isSolution()) {
                alertObservers("You win!");
            } else {
                alertObservers("Hint move applied.");
            }
        }
    }
    /**
     * Returns the current board configuration.
     *
     * @return the current TiltConfig
     */
    public TiltConfig getCurrent() {
        return currentConfig;
    }
}
