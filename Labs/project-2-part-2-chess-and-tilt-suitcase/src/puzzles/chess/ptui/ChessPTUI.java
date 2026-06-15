package puzzles.chess.ptui;

import puzzles.chess.model.ChessModel;
import puzzles.common.Observer;

import java.util.Scanner;

public class ChessPTUI implements Observer<ChessModel, String> {
    private ChessModel model;

    @Override
    public void update(ChessModel model, String message) {
        System.out.println(model.printBoard());
        System.out.println(message);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ChessPTUI filename");
        }
        else{
            ChessPTUI ptui = new ChessPTUI();
            ptui.init(args[0]);
            ptui.run();
        }
    }

    private void init(String filename){
        model = new ChessModel(filename);
        model.addObserver(this);
    }

    private void run(){
        Scanner in = new Scanner(System.in);
        System.out.println(model.printBoard());

        printHelp();

        while(true){
            System.out.print("> ");
            String command = in.next();
            if(command.equals("q") || command.equals("quit")){
                break;
            }
            else if(command.equals("h") || command.equals("hint")){
                model.hint();
            }
            else if(command.equals("l") || command.equals("load")){
                String filename = in.next();
                model.load(filename);
            }
            else if (command.equals("r") || command.equals("reset")){
                model.reset();
            }
            else if (command.equals("s") || command.equals("select")){
                int row = in.nextInt();
                int col =  in.nextInt();
                model.select(row, col);
            }
            else{
                printHelp();
            }
        }
    }
    private void printHelp(){
        System.out.println("h(int)              -- hint next move");
        System.out.println("l(oad)              -- load new puzzle file");
        System.out.println("s(elect) r c        -- select cell at r,c ");
        System.out.println("q(uit)              -- quit the game");
        System.out.println("r(eset)             -- reset the current game");
    }
}
