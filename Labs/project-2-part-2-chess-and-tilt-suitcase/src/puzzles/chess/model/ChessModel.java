package puzzles.chess.model;

import puzzles.chess.solver.Chess;
import puzzles.common.Observer;
import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

import java.util.LinkedList;
import java.util.List;

public class ChessModel {
    /** the collection of observers of this model */
    private final List<Observer<ChessModel, String>> observers = new LinkedList<>();

    /** the current configuration */
    private ChessConfig currentConfig;
    private ChessConfig initialConfig;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public ChessModel(String filename){
        initialConfig = new ChessConfig(filename);
        currentConfig = initialConfig;
    }

    /**
     * The view calls this to add itself as an observer.
     *
     * @param observer the view
     */
    public void addObserver(Observer<ChessModel, String> observer) {
        this.observers.add(observer);
    }

    /**
     * The model's state has changed (the counter), so inform the view via
     * the update method
     */
    private void alertObservers(String data) {
        for (var observer : observers) {
            observer.update(this, data);
        }
    }

    public String printBoard(){
        return currentConfig.toString();
    }

    public void load(String filename){
        initialConfig = new ChessConfig(filename);
        currentConfig = initialConfig;
        selectedRow = -1;
        selectedCol = -1;
        alertObservers("Loaded: " + filename);
    }

    public void reset(){
        currentConfig = initialConfig;
        selectedRow = -1;
        selectedCol = -1;
        alertObservers("Puzzle Reset.");
    }

    public void hint(){
        Solver solver = new Solver();
        List<Configuration> path = solver.solve(currentConfig);

        if (path == null || path.size() < 2){
            alertObservers("No hint available");
        }
        else{
            currentConfig = (ChessConfig) path.get(1); // get the next move
            alertObservers("Hint");
        }
    }
    public void select(int row, int col){
        if (selectedRow == -1 && selectedCol == -1){
            if (!currentConfig.isPiece(row,col)){
                alertObservers("Invalid selection: No piece at (" + row + "," + col + ")");
            } else {
                selectedRow = row;
                selectedCol = col;
                alertObservers("Selected (" + row + "," + col + ")");
            }
        }
        else{
            ChessConfig next = currentConfig.attemptMove(selectedRow, selectedCol, row, col);
            if (next != null){
                currentConfig = next;
                alertObservers("Moved piece from (" + selectedRow + "," + selectedCol + ") to (" + row + "," + col + ")");
            }
            else{
                alertObservers("Invalid move from (" + selectedRow + "," + selectedCol + ") to (" + row + "," + col + ")");
            }
            selectedRow = -1;
            selectedCol = -1;
        }
    }

    public String getPiece(int row, int col){
        return currentConfig.getPieceAt(row, col);
    }

    public int getRows(){
        return currentConfig.getRows();
    }

    public int getCols(){
        return currentConfig.getCols();
    }
}
