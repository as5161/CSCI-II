package gui;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import model.LightsOutModel;
import model.Observer;
import java.io.File;
/**
 * LightsOutGUI represents the graphical user interface for the Lights Out game.
 */
public class LightsOutGUI extends Application implements Observer<LightsOutModel, String> {
    private LightsOutModel model;
    private Button[][] buttons;
    private Label moveCounterLabel;
    private Label messageLabel;
    /**
     * Initializes the game model and registers this GUI as an observer.
     */
    @Override
    public void init() {
        model = new LightsOutModel();
        model.addObserver(this);
    }
    /**
     * Sets up the game window and initializes the button grid.
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Lights Out");
        int size = model.getDimension();
        buttons = new Button[size][size];
        GridPane grid = new GridPane();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Button btn = new Button();
                btn.setMinSize(50, 50);
                buttons[row][col] = btn;
                updateButton(row, col);
                int r = row, c = col;
                btn.setOnAction(e -> model.toggleTile(r, c));
                grid.add(btn, col, row);
            }
        }
        moveCounterLabel = new Label("Moves: 0");
        messageLabel = new Label("");
        Button hintButton = new Button("Hint");
        hintButton.setOnAction(e -> model.getHint());
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> {
            model.generateRandomBoard();
            updateBoard();
            moveCounterLabel.setText("Moves: 0");
            messageLabel.setText("New game started!");
        });
        Button loadGameButton = new Button("Load Game");
        loadGameButton.setOnAction(e -> loadGame(stage));
        HBox buttonBox = new HBox(10, hintButton, newGameButton, loadGameButton);
        VBox root = new VBox(10, grid, moveCounterLabel, messageLabel, buttonBox);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Loads a game board from a file selected by the user.
     * Opens a file chooser dialog, allows the user to select a file,
     * and loads the board from the selected file.
     * Displays success or error messages based on the result of loading the game.
     * @param stage The primary stage for the JavaFX application.
     */
    private void loadGame(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load a game board");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/boards"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Lights Out Boards", "*.lob"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            boolean success = model.loadBoardFromFile(selectedFile);
            if (success) {
                updateBoard();
                moveCounterLabel.setText("Moves: " + model.getMoves());
                messageLabel.setText("Game loaded successfully!");
            } else {
                messageLabel.setText("Failed to load file.");
            }
        }
    }


    /**
     * Updates the GUI when the model changes.
     * @param model The updated model.
     * @param arg Additional update information (not used in this case).
     */
    @Override
    public void update(LightsOutModel model, String arg) {
        updateBoard();
        moveCounterLabel.setText("Moves: " + model.getMoves());

        if (model.gameOver()) {
            messageLabel.setText("Congratulations! You won!");
        } else if (arg != null && arg.startsWith(LightsOutModel.HINT_PREFIX)) {
            messageLabel.setText(arg);
            String[] parts = arg.replaceAll("[^0-9,]", "").split(",");
            if (parts.length >= 2) {
                try {
                    int row = Integer.parseInt(parts[0]);
                    int col = Integer.parseInt(parts[1]);
                    highlightHintButton(row, col);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Error parsing hint: " + arg);
                }
            }
        }
    }
    /**
     * Highlights a specific button on the board in yellow to indicate a hint.
     * This is typically used to show the user a recommended move.
     * @param row The row index of the button to highlight.
     * @param col The column index of the button to highlight.
     */
    private void highlightHintButton(int row, int col) {
        buttons[row][col].setStyle("-fx-background-color: yellow;");
    }
    /**
     * Updates the entire board by refreshing the visual state of all the buttons.
     * The buttons are updated based on the current state of the model.
     * If it is the first move, the buttons are updated once; otherwise, they are updated continuously.
     */
    private void updateBoard() {
        int size = model.getDimension();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (model.getMoves() == 0) {
                    updateButtonOnce(row, col);
                }else{
                    updateButton(row,col);
                }
            }
        }
    }
    /**
     * Updates the visual representation of a button based on the model state.
     * @param row The row index of the button.
     * @param col The column index of the button.
     */
    private void updateButton(int row, int col) {
        Button btn = buttons[row][col];
        if (model.getTile(row, col).isOn()) {
            btn.setStyle("-fx-background-color: #ac0e0e;"); // Red for on
        } else {
            btn.setStyle("-fx-background-color: #212f78;"); // Blue for off
        }
    }

    private void updateButtonOnce(int row, int col) {
        Button btn = buttons[row][col];
        if (model.getTile(col, row).isOn()) {
            btn.setStyle("-fx-background-color: #ac0e0e;");
        } else {
            btn.setStyle("-fx-background-color: #212f78;");
        }
    }

    /**
     * Launches the JavaFX application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}