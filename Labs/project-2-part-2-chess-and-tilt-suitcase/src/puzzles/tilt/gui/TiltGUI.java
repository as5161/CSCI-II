package puzzles.tilt.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import puzzles.common.Observer;
import puzzles.tilt.model.TiltModel;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * TiltGUI sets up and runs the graphical user interface (GUI) for the Tilt puzzle game.
 * It displays the game board, handles user interactions (tilts, reset, load, hint),
 * and updates the display based on changes in the TiltModel.
 */
public class TiltGUI extends Application implements Observer<TiltModel, String> {
    private static final String RESOURCES_DIR = "resources/";
    private TiltModel model;
    private GridPane board;
    private Label messageLabel;
    private String filename;
    private Image greenDisk = new Image(getClass().getResourceAsStream(RESOURCES_DIR + "green.png"));
    private Image blueDisk = new Image(getClass().getResourceAsStream(RESOURCES_DIR + "blue.png"));
    private Image hole = new Image(getClass().getResourceAsStream(RESOURCES_DIR + "hole.png"));
    private Image block = new Image(getClass().getResourceAsStream(RESOURCES_DIR + "block.png"));
    /**
     * Initializes the application by retrieving the filename from command line arguments.
     */
    @Override
    public void init() {
        filename = getParameters().getRaw().get(0);
    }
    /**
     * Sets up the JavaFX GUI, loads the Tilt model, and creates all buttons and layout.
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.model = new TiltModel(filename);
        this.model.addObserver(this);
        BorderPane root = new BorderPane();
        messageLabel = new Label("Loaded: " + filename);
        messageLabel.setAlignment(Pos.CENTER);
        board = new GridPane();
        board.setAlignment(Pos.CENTER);
        drawBoard();
        Button upButton = new Button("^");
        upButton.setMaxWidth(Double.MAX_VALUE);
        upButton.setOnAction(e -> model.tilt('N'));
        Button downButton = new Button("v");
        downButton.setMaxWidth(Double.MAX_VALUE);
        downButton.setOnAction(e -> model.tilt('S'));
        Button leftButton = new Button("<");
        leftButton.setMaxHeight(Double.MAX_VALUE);
        leftButton.setOnAction(e -> model.tilt('W'));
        Button rightButton = new Button(">");
        rightButton.setMaxHeight(Double.MAX_VALUE);
        rightButton.setOnAction(e -> model.tilt('E'));
        Button loadButton = new Button("Load");
        loadButton.setPrefSize(80, 30);
        loadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("data/tilt"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                try {
                    model.load(selectedFile.toString());
                } catch (FileNotFoundException ex) {
                    messageLabel.setText("Failed to load file.");
                }
            }
        });
        Button resetButton = new Button("Reset");
        resetButton.setPrefSize(80, 30);
        resetButton.setOnAction(e -> {
            try {
                model.reset();
            } catch (FileNotFoundException ex) {
                messageLabel.setText("Failed to reset.");
            }
        });
        Button hintButton = new Button("Hint");
        hintButton.setPrefSize(80, 30);
        hintButton.setOnAction(e -> model.hint());
        VBox rightButtons = new VBox(10, loadButton, resetButton, hintButton);
        rightButtons.setAlignment(Pos.CENTER);
        BorderPane gameArea = new BorderPane();
        gameArea.setTop(upButton);
        gameArea.setBottom(downButton);
        gameArea.setLeft(leftButton);
        gameArea.setRight(rightButton);
        gameArea.setCenter(board);
        BorderPane.setAlignment(upButton, Pos.CENTER);
        BorderPane.setAlignment(downButton, Pos.CENTER);
        BorderPane.setAlignment(leftButton, Pos.CENTER);
        BorderPane.setAlignment(rightButton, Pos.CENTER);
        HBox center = new HBox(10, gameArea, rightButtons);
        center.setAlignment(Pos.CENTER);
        VBox top = new VBox(10, messageLabel);
        top.setAlignment(Pos.CENTER);
        root.setTop(top);
        root.setCenter(center);
        Scene scene = new Scene(root);
        stage.setTitle("Tilt GUI");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
    /**
     * Draws the current state of the Tilt board onto the GridPane.
     */
    private void drawBoard() {
        board.getChildren().clear();
        char[][] currentBoard = model.getCurrent().getBoard();
        int size = model.getCurrent().getSize();
        double tileSize = (size <= 5) ? 80 : 50;
        board.setHgap(0);
        board.setVgap(0);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                char cell = currentBoard[r][c];
                StackPane tile = new StackPane();
                tile.setPrefSize(tileSize, tileSize);
                tile.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
                ImageView icon = switch (cell) {
                    case 'G' -> new ImageView(greenDisk);
                    case 'B' -> new ImageView(blueDisk);
                    case 'O' -> new ImageView(hole);
                    case '*' -> new ImageView(block);
                    default -> null;
                };
                if (icon != null) {
                    icon.setFitWidth(tileSize - 10);
                    icon.setFitHeight(tileSize - 10);
                    tile.getChildren().add(icon);
                }
                board.add(tile, c, r);
            }
        }
    }
    /**
     * Updates the board and message label when the model changes.
     */
    @Override
    public void update(TiltModel model, String message) {
        drawBoard();
        messageLabel.setText(message);
    }
    /**
     * Main entry point to launch the Tilt GUI application.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java TiltGUI filename");
            System.exit(0);
        }
        Application.launch(args);
    }
}
