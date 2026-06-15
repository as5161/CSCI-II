package puzzles.chess.model;

// TODO: implement your ChessConfig for the puzzles.common solver

import puzzles.common.solver.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class ChessConfig implements Configuration {
    private final int rows;
    private final int cols;
    private final String[][] board;

    public ChessConfig(int rows, int cols, String[][] board){
        this.rows = rows;
        this.cols = cols;
        this.board = board;
    }

    // constructor from file
    public ChessConfig(String filename){
        int temp_Rows = 0;
        int temp_Cols = 0;
        String[][] temp_Board = null;

        try (Scanner in = new Scanner(new File(filename))){
            temp_Rows = in.nextInt();
            temp_Cols = in.nextInt();
            in.nextLine();
            temp_Board = new String[temp_Rows][temp_Cols];
            for (int r = 0; r < temp_Rows; r++){
                String[] line = in.nextLine().split(" ");
                for (int c = 0; c < temp_Cols; c++){
                    temp_Board[r][c] = line[c];
                }
            }
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        this.rows = temp_Rows;
        this.cols = temp_Cols;
        this.board = temp_Board;
    }

    @Override
    public boolean isSolution() {
        // go through entire board and
        // return when there is only 1 piece
        int pieces = 0;
        for (int r = 0; r< rows; r++){
            for (int c = 0; c < cols; c++){
                if (!board[r][c].equals(".")){
                    pieces++;
                }
            }
        }
        return pieces == 1;
    }

    @Override
    public Collection<Configuration> getNeighbors() {
        List<Configuration> neighbors = new ArrayList<>();

        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                String piece = board[r][c];
                if (!piece.equals(".")){
                    if (piece.equals("R")){
                        RookMove(r,c,neighbors);
                    }
                    else if (piece.equals("B")){
                        BishopMove(r,c,neighbors);
                    }
                    else if (piece.equals("Q")){
                        QueenMove(r,c,neighbors);
                    }
                    else if (piece.equals("N")){
                        KnightMove(r,c, neighbors);
                    }
                    else if (piece.equals("K")){
                        KingMove(r,c, neighbors);
                    }
                    else if (piece.equals("P")){
                        PawnMove(r,c,neighbors);
                    }
                }
            }
        }

        return neighbors;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ChessConfig otherConfig)){
            return false;
        }
        if (rows != otherConfig.rows || cols != otherConfig.cols){
            return false;
        }
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                if (!board[r][c].equals(otherConfig.board[r][c])){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int r = 0; r< rows; r++){
            for (int c = 0; c < cols; c++){
                hash += board[r][c].hashCode();
            }
        }
        return hash;
    }

    @Override
    public String toString() {
        String result = "";
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                result += board[r][c];
                if (c < cols - 1){
                    result += " ";
                }
            }
            result += "\n";
        }
        return result;
    }

    private boolean isValid (int r, int c){
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    private String[][] copyBoard(){
        String[][] copy = new String[rows][cols];
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                copy[r][c] = board[r][c];
            }
        }
        return copy;
    }

    private void KnightMove(int r, int c, List<Configuration> neighbors){
        int [][] moves = {{-2, -1}, {-2, 1}, {-1,-2}, {-1,2},{1,-2}, {1,2}, {2,-1}, {2, 1}};

        for (int[] move : moves){
            int newR = r + move[0];
            int newC = c + move[1];

            if (isValid(newR, newC) && !board[newR][newC].equals(".")){
                String[][] newBoard = copyBoard();
                newBoard[newR][newC] = board[r][c];
                newBoard[r][c] = ".";
                neighbors.add(new ChessConfig(rows, cols, newBoard));
            }
        }
    }
    private void KingMove(int r, int c, List<Configuration> neighbors){
        int[][] moves = {{-1,-1}, {-1,0}, {-1, 1}, {0,-1}, {0, 1}, {1,-1}, {1,0}, {1,1}};
        for (int[] move : moves){
            int newR = r + move[0];
            int newC = c + move[1];

            if (isValid(newR, newC) && !board[newR][newC].equals(".")){
                String[][] newBoard = copyBoard();
                newBoard[newR][newC] = board[r][c];
                newBoard[r][c] = ".";
                neighbors.add(new ChessConfig(rows, cols, newBoard));
            }
        }
    }
    private void PawnMove(int r, int c, List<Configuration> neighbors){
        int[][] moves = {{-1,-1}, {-1,1}};
        for (int[] move : moves){
            int newR = r + move[0];
            int newC = c + move[1];

            if (isValid(newR, newC) && !board[newR][newC].equals(".")){
                String[][] newBoard = copyBoard();
                newBoard[newR][newC] = board[r][c];
                newBoard[r][c] = ".";
                neighbors.add(new ChessConfig(rows, cols, newBoard));
            }
        }
    }
    private void RookMove(int r, int c, List<Configuration> neighbors){
        int [][] moves = {{-1,0}, {1,0}, {0, -1}, {0,1}};
        for (int[] move : moves){
            int newR = r + move[0];
            int newC = c + move[1];

            while(isValid(newR, newC)){
            if (!board[newR][newC].equals(".")){
                String[][] newBoard = copyBoard();
                newBoard[newR][newC] = board[r][c];
                newBoard[r][c] = ".";
                neighbors.add(new ChessConfig(rows, cols, newBoard));
                break;
            }
            newR += move[0];
            newC += move[1];
            }
        }
    }
    private void BishopMove(int r, int c, List<Configuration> neighbors){
        int[][] moves = {{-1,-1}, {-1,1}, {1,-1}, {1,1}};

        for (int[] move : moves){
            int newR = r + move[0];
            int newC = c + move[1];

            while(isValid(newR, newC)){
                if (!board[newR][newC].equals(".")){
                    String[][] newBoard = copyBoard();
                    newBoard[newR][newC] = board[r][c];
                    newBoard[r][c] = ".";
                    neighbors.add(new ChessConfig(rows, cols, newBoard));
                    break;
                }
                newR += move[0];
                newC += move[1];
            }
        }
    }
    private void QueenMove(int r, int c, List<Configuration> neighbors){
        int[][] moves = {{-1,0}, {1,0}, {0,-1}, {0,1}, {-1,-1}, {-1,1},{1,-1},{1,1}};
        for (int[] move : moves){
            int newR = r + move[0];
            int newC = c + move[1];

            while(isValid(newR, newC)){
                if(!board[newR][newC].equals(".")){
                    String[][] newBoard = copyBoard();
                    newBoard[newR][newC] = board[r][c];
                    newBoard[r][c] = ".";
                    neighbors.add(new ChessConfig(rows,cols,newBoard));
                    break;
                }
                newR += move[0];
                newC += move[1];
            }
        }
    }

    public boolean isPiece(int r, int c){
        return isValid(r,c) && !board[r][c].equals(".");
    }

    public ChessConfig attemptMove(int fromR, int fromC, int toR, int toC){
        if (!isValid(fromR, fromC) || !isValid(toR, toC)){
            return null;
        }
        String piece = board[fromR][fromC];
        if (piece.equals(".")){
            return null;
        }
        if(board[toR][toC].equals(".")){
            return null;
        }

        List<Configuration> neighbors = (List<Configuration>) getNeighbors();
        for(Configuration config : neighbors){
            ChessConfig neighbor = (ChessConfig) config;
            if (neighbor.board[toR][toC].equals(piece) && neighbor.board[fromR][fromC].equals(".")){
                return neighbor;
            }
        }
        return null;
    }

    public String getPieceAt(int r, int c){
        if (isValid(r,c)){
            return board[r][c];
        }
        return ".";
    }

    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }
}