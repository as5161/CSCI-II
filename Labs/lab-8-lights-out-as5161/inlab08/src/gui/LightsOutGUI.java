package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.LightsOutModel;
import model.Observer;

/**
 * LightsOutGUI represents the graphical user interface for the Lights Out game.
 */
public class LightsOutGUI extends Application implements Observer<LightsOutModel, String> {
    private LightsOutModel model;
    private Button[][] buttons;

    /**
     * Initializes the game model and registers this GUI as an observer.
     */
    @Override
    public void init() {
        System.out.println("Initializing model...");
        model = new LightsOutModel();
        model.addObserver(this);
    }

    /**
     * Sets up the game window and initializes the button grid.
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Lights Out Game");
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

        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Updates the GUI when the model changes.
     * @param model The updated model.
     * @param arg Additional update information (not used in this case).
     */
    @Override
    public void update(LightsOutModel model, String arg) {
        System.out.println("Update received from model!");
        int size = model.getDimension();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                updateButton(row, col);
            }
        }
    }

    /**
     * Updates the visual representation of a button based on the model state.
     * @param row The row index of the button.
     * @param col The column index of the button.
     */
    private void updateButton(int row, int col) {
        if (buttons[row][col] == null) {
            System.out.println("Error: Button at (" + row + "," + col + ") is null!");
            return;
        }
        if (model.getTile(row, col).isOn()) {
            buttons[row][col].setStyle("-fx-background-color: #ac0e0e;");
        } else {
            buttons[row][col].setStyle("-fx-background-color: #212f78;");
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
