package puzzles.tilt.ptui;
import puzzles.common.Observer;
import puzzles.tilt.model.TiltModel;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * TiltPTUI provides a plain-text user interface (PTUI) for playing the Tilt puzzle.
 * It allows loading puzzles, tilting the board, resetting, requesting hints, and quitting.
 */
public class TiltPTUI implements Observer<TiltModel, String> {
    private TiltModel model;
    /**
     * Updates the console output when the model changes.
     *
     * @param model the updated model
     * @param message the message from the model
     */
    @Override
    public void update(TiltModel model, String message) {
        System.out.println(message);
        System.out.println(model.getCurrent());
    }
    /**
     * The entry point of the program. Initializes and runs the text-based Tilt game.
     *
     * @param args command-line arguments (expects one filename)
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java TiltPTUI filename");
            System.exit(0);
        }

        TiltPTUI ptui = new TiltPTUI();
        try {
            ptui.init(args[0]);
            ptui.run();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load: " + args[0]);
        }
    }
    /**
     * Initializes the Tilt model with the given file and registers the observer.
     *
     * @param filename the name of the file to load
     * @throws FileNotFoundException if the file is not found
     */
    private void init(String filename) throws FileNotFoundException {
        this.model = new TiltModel(filename);
        this.model.addObserver(this);
        System.out.println("Loaded: " + filename);
        System.out.println(this.model.getCurrent());
    }
    /**
     * Runs the main input loop, handling user commands to interact with the puzzle.
     */
    private void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("""
                h(int)              -- hint next move
                l(oad) filename     -- load new puzzle file
                t(ilt) {N|S|E|W}    -- tilt the board in the given direction
                q(uit)              -- quit the game
                r(eset)             -- reset the current game
                """);

        while (true) {
            System.out.print("> ");
            String line = in.nextLine().strip();
            if (line.length() == 0) continue;

            String[] tokens = line.split("\\s+");
            String cmd = tokens[0].toLowerCase();

            try {
                switch (cmd) {
                    case "q", "quit" -> {
                        return;
                    }
                    case "h", "hint" -> model.hint();
                    case "r", "reset" -> model.reset();
                    case "t", "tilt" -> {
                        if (tokens.length != 2) {
                            System.out.println("Usage: t(ilt) {N|S|E|W}");
                        } else {
                            model.tilt(tokens[1].toUpperCase().charAt(0));
                        }
                    }
                    case "l", "load" -> {
                        if (tokens.length != 2) {
                            System.out.println("Usage: l(oad) filename");
                        } else {
                            model.load(tokens[1]);
                        }
                    }
                    default -> System.out.println("Unknown command: " + cmd);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Failed to load: " + (tokens.length > 1 ? tokens[1] : ""));
            }
        }
    }
}
