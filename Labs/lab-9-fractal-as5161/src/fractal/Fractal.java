package fractal;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
/**
 * Fractal program for rendering the Mandelbrot Set.
 *
 * @author RIT CS
 * @author Aamir Sohail
 */
public class Fractal extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int MAX_ITER = 1000;
    private static final double ZOOM_FACTOR = 1.5;
    private double zoom = 1.0;
    private double offsetX = 0.0;
    private double offsetY = 0.0;
    private boolean threaded = false;
    private Label lblRenderTime = new Label();
    private Label lblZoom = new Label();
    private Label lblOffset = new Label();
    private Label lblCursor = new Label();
    private Label lblThreading = new Label();
    private Canvas canvas;

    /**
     * Entry point for the JavaFX application.
     * Sets up the UI layout and event handlers.
     */
    @Override
    public void start(Stage stage) {
        canvas = new Canvas(WIDTH, HEIGHT);
        BorderPane layout = new BorderPane();
        layout.setCenter(canvas);
        HBox infoPanelTop = new HBox(10);
        infoPanelTop.setPadding(new Insets(10));
        infoPanelTop.getChildren().addAll(lblRenderTime, lblZoom, lblOffset, lblCursor, lblThreading);
        layout.setTop(infoPanelTop);
        HBox controlPanelBottom = new HBox(10);
        controlPanelBottom.setPadding(new Insets(10));
        Button btnReset = new Button("Reset");
        Button btnSave = new Button("Save Image");
        CheckBox cbThreading = new CheckBox("Threaded");
        cbThreading.setOnAction(e -> {
            threaded = cbThreading.isSelected();
            render();
        });
        btnReset.setOnAction(e -> {
            zoom = 1.0;
            offsetX = 0.0;
            offsetY = 0.0;
            render();
        });
        btnSave.setOnAction(e -> saveSnapshot(canvas, "mandelbrot_output.png"));
        controlPanelBottom.getChildren().addAll(btnReset, btnSave, cbThreading);
        layout.setBottom(controlPanelBottom);
        canvas.setOnMouseClicked(this::handleMouseClick);
        canvas.setOnMouseMoved(this::updateCursorPosition);
        render();
        stage.setTitle("Mandelbrot Viewer");
        stage.setScene(new Scene(layout));
        stage.show();
    }
    /**
     * Render the Mandelbrot set onto the canvas.
     * Supports single-threaded or pseudo-threaded row rendering.
     */
    private void render() {
        long start = System.currentTimeMillis();
        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        Runnable drawTask = () -> {
            for (int py = 0; py < HEIGHT; py++) {
                final int rowY = py;
                Runnable rowDraw = () -> {
                    for (int px = 0; px < WIDTH; px++) {
                        double coordX = (px - WIDTH / 2.0) * 4.0 / (WIDTH * zoom) + offsetX;
                        double coordY = (rowY - HEIGHT / 2.0) * 4.0 / (HEIGHT * zoom) + offsetY;
                        int iterations = mandelbrot(coordX, coordY);
                        Color pixelColor = iterations == MAX_ITER
                                ? Color.BLACK
                                : Color.hsb(360.0 * iterations / MAX_ITER, 1, 1);
                        pixelWriter.setColor(px, rowY, pixelColor);
                    }
                };
                if (threaded) {
                    Platform.runLater(rowDraw);
                } else {
                    rowDraw.run();
                }
            }
            long elapsed = System.currentTimeMillis() - start;
            Platform.runLater(() -> {
                lblRenderTime.setText("Render time: " + elapsed + " ms");
                lblZoom.setText(String.format("Zoom: %.2fx", zoom));
                lblOffset.setText(String.format("Offset (X,Y): [%.4f, %.4f]", offsetX, offsetY));
                lblThreading.setText(threaded ? "[Threaded]" : "[Single Thread]");
            });
        };
        if (threaded) {
            new Thread(drawTask).start();
        } else {
            drawTask.run();
        }
    }
    /**
     * Compute the Mandelbrot iteration count for a point (cx, cy).
     */
    private int mandelbrot(double cx, double cy) {
        double zx = 0, zy = 0;
        int count = 0;
        while (zx * zx + zy * zy < 4 && count < MAX_ITER) {
            double temp = zx * zx - zy * zy + cx;
            zy = 2.0 * zx * zy + cy;
            zx = temp;
            count++;
        }
        return count;
    }

    /**
     * Handle mouse clicks for zooming in and out.
     * Left click zooms in, right click zooms out.
     */
    private void handleMouseClick(MouseEvent evt) {
        double clickX = evt.getX();
        double clickY = evt.getY();
        if (evt.getButton() == MouseButton.PRIMARY) {
            zoom *= ZOOM_FACTOR;
        } else if (evt.getButton() == MouseButton.SECONDARY) {
            zoom /= ZOOM_FACTOR;
        }
        offsetX += (clickX - WIDTH / 2.0) * 4.0 / (WIDTH * zoom);
        offsetY += (clickY - HEIGHT / 2.0) * 4.0 / (HEIGHT * zoom);
        render();
    }

    /**
     * Update the label with the current mouse cursor position.
     */
    private void updateCursorPosition(MouseEvent evt) {
        lblCursor.setText(String.format("Cursor (X,Y): [%.0f, %.0f]", evt.getX(), evt.getY()));
    }
    /**
     * Save the current canvas as an image file.
     *
     * @param canvasRef The canvas to snapshot
     * @param filename  The filename to save the image as
     */
    private void saveSnapshot(Canvas canvasRef, String filename) {
        WritableImage snapshot = new WritableImage(WIDTH, HEIGHT);
        canvasRef.snapshot(null, snapshot);
        BufferedImage buffered = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Color fxColor = snapshot.getPixelReader().getColor(x, y);
                int argb =
                        ((int) (fxColor.getOpacity() * 255) << 24) |
                                ((int) (fxColor.getRed() * 255) << 16) |
                                ((int) (fxColor.getGreen() * 255) << 8) |
                                ((int) (fxColor.getBlue() * 255));
                buffered.setRGB(x, y, argb);
            }
        }
        try {
            File outFile = new File(filename);
            ImageIO.write(buffered, "png", outFile);
            System.out.println("Image saved to: " + outFile.getAbsolutePath());
        } catch (Exception ex) {
            System.out.println("Image save failed: " + ex.getMessage());
        }
    }
    /**
     * Java main method. Launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch();
    }
}
