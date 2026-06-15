package puzzles.chess.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import puzzles.chess.model.ChessModel;
import puzzles.common.Observer;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.File;
import java.nio.file.Paths;

public class ChessGUI extends Application implements Observer<ChessModel, String> {
    /** The resources directory is located directly underneath the gui package */
    private final static String RESOURCES_DIR = "resources/";

    private ChessModel model;
    private GridPane board;
    private Label statusLabel;
    private int selectedRow = -1;
    private int selectedCol = -1;

    // for demonstration purposes
    private Image pawn = new Image(getClass().getResourceAsStream(RESOURCES_DIR+"pawn.png"));
    private Image bishop = new Image(getClass().getResourceAsStream(RESOURCES_DIR+"bishop.png"));
    private Image blue = new Image(getClass().getResourceAsStream(RESOURCES_DIR+"blue.png"));
    private Image king = new Image(getClass().getResourceAsStream(RESOURCES_DIR+"king.png"));
    private Image knight = new Image(getClass().getResourceAsStream(RESOURCES_DIR+"knight.png"));
    private Image queen = new Image(getClass().getResourceAsStream(RESOURCES_DIR+"queen.png"));
    private Image rook = new Image(getClass().getResourceAsStream(RESOURCES_DIR+"rook.png"));


    public void init() {
        String filename = getParameters().getRaw().get(0);
        this.model = new ChessModel(filename);
        model.addObserver(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.board = new GridPane();
        String filename = getParameters().getRaw().get(0);
        this.statusLabel = new Label("loaded " + filename);

        for (int r = 0; r < model.getRows(); r++){
            for (int c = 0; c < model.getCols(); c++){
                Button button = new Button();
                String piece = model.getPiece(r,c);

                if (piece.equals("P")){
                    button.setGraphic(new ImageView(pawn));
                }
                else if (piece.equals("R")){
                    button.setGraphic(new ImageView(rook));
                }
                else if (piece.equals("B")){
                    button.setGraphic(new ImageView(bishop));
                }
                else if (piece.equals("Q")){
                    button.setGraphic(new ImageView(queen));
                }
                else if (piece.equals("N")){
                    button.setGraphic(new ImageView(knight));
                }
                else if (piece.equals("K")){
                    button.setGraphic(new ImageView(king));
                }
                else{
                    button.setGraphic(new ImageView(blue));
                }

                int finalR = r;
                int finalC = c;
                button.setOnAction(e -> model.select(finalR, finalC));

                board.add(button,c,r);
            }
        }

        Button hint = new Button("Hint");
        hint.setOnAction(e-> model.hint());

        Button reset = new Button("Reset");
        reset.setOnAction(e->model.reset());

        Button load = new Button("load");
        load.setOnAction(e->{
            FileChooser chooser = new FileChooser();
            String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
            currentPath += File.separator + "data" + File.separator + "chess";
            chooser.setInitialDirectory(new File(currentPath));
            File file = chooser.showOpenDialog(stage);
            if (file != null){
                model.load(file.getPath());
            }
        });

        HBox controls = new HBox(10, hint, reset, load);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(10));

        VBox root = new VBox(10, board, controls, statusLabel);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Chess GUI");
        stage.show();
    }

    @Override
    public void update(ChessModel chessModel, String message) {
        board.getChildren().clear();

        for (int r = 0; r < model.getRows(); r++){
            for (int c = 0; c < model.getCols(); c++){
                Button button = new Button();
                String piece = model.getPiece(r,c);

                if (piece.equals("P")){
                    button.setGraphic(new ImageView(pawn));
                }
                else if (piece.equals("R")){
                    button.setGraphic(new ImageView(rook));
                }
                else if (piece.equals("B")){
                    button.setGraphic(new ImageView(bishop));
                }
                else if (piece.equals("Q")){
                    button.setGraphic(new ImageView(queen));
                }
                else if (piece.equals("N")){
                    button.setGraphic(new ImageView(knight));
                }
                else if (piece.equals("K")){
                    button.setGraphic(new ImageView(king));
                }
                else{
                    button.setGraphic(new ImageView(blue));
                }

                int finalR = r;
                int finalC = c;
                button.setOnAction(e -> model.select(finalR, finalC));

                board.add(button,c,r);
            }
        }
        statusLabel.setText(message);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ChessGUI filename");
            System.exit(0);
        } else {
            Application.launch(args);
        }
    }
}
