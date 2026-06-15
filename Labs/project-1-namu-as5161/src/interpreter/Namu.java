package interpreter;

import common.Errors;
import common.SymbolTable;
import interpreter.nodes.action.ActionNode;
import interpreter.nodes.action.Assignment;
import interpreter.nodes.action.Print;
import interpreter.nodes.expression.*;
import machine.Alaton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * The Namu interpreter is responsible for tokenizing, parsing, executing,
 * and compiling NMU code into ALT machine instructions.
 */
public class Namu {
    private final static String EOF = ".";
    private final static String PRINT = "@";
    private final static String ASSIGN = "=";
    private final static String TMP_NMU_FILE = "tmp/TEMP.nmu";

    private final List<ActionNode> program = new ArrayList<>();
    private final List<String> tokens = new ArrayList<>();

    /**
     * Reads and tokenizes NMU input.
     *
     * @param in     The Scanner to read NMU input from.
     * @param stdin  Indicates if input is from standard input (interactive mode).
     */
    public Namu(Scanner in, boolean stdin) {
        if (stdin) System.out.print("🌳 ");
        System.out.println("(NMU) prefix...");

        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            if (stdin && line.equals(EOF)) break;
            if (!line.isEmpty()) {
                System.out.println(line);
                tokens.addAll(Arrays.asList(line.split("\\s+")));
            }
        }
    }

    /**
     * Builds the Abstract Syntax Tree (AST) for the NMU program.
     * Iterates through the tokenized list and constructs corresponding ActionNodes.
     */
    public void buildProgram() {
        while (!tokens.isEmpty()) {
            ActionNode action = parseAction();
            if (action != null) program.add(action);
        }
    }

    /**
     * Parses an NMU action (either an assignment or a print statement).
     *
     * @return The parsed ActionNode or null if an invalid action is encountered.
     */
    private ActionNode parseAction() {
        if (tokens.isEmpty()) return null;

        String firstToken = tokens.remove(0);
        if (firstToken.equals(PRINT)) {
            return new Print(parseExpression());
        } else if (firstToken.equals(ASSIGN)) {
            if (tokens.isEmpty()) Errors.report(Errors.Type.PREMATURE_END);
            String varName = tokens.remove(0);
            return new Assignment(varName, parseExpression());
        }

        Errors.report(Errors.Type.ILLEGAL_ACTION, "Invalid action: " + firstToken);
        return null;
    }

    /**
     * Parses an expression recursively.
     * Handles constants, variables, unary operations, and binary operations.
     *
     * @return The parsed ExpressionNode.
     */
    private ExpressionNode parseExpression() {
        if (tokens.isEmpty()) Errors.report(Errors.Type.PREMATURE_END);

        String token = tokens.remove(0);
        if (token.matches("[!$]")) {
            return new UnaryOperation(token, parseExpression());
        }
        else if (token.matches("[+\\-*/%^]")) {
            ExpressionNode left = parseExpression();
            ExpressionNode right = parseExpression();
            return new BinaryOperation(token, left, right);
        }
        else if (token.matches("-?\\d+")) {
            return new Constant(Integer.parseInt(token));
        }
        else if (token.matches("[a-zA-Z].*")) {
            return new Variable(token);
        }

        Errors.report(Errors.Type.ILLEGAL_OPERATOR, "Invalid token: " + token);
        return null;
    }

    /**
     * Displays the NMU program in infix notation.
     * Each ActionNode is converted into a human-readable format.
     */
    public void displayProgram() {
        System.out.println("(NMU) infix...");
        for (ActionNode action : program) {
            System.out.println(action.toString());
        }
    }

    /**
     * Interprets and executes the NMU program.
     * Evaluates each action and updates the symbol table accordingly.
     */
    public void interpretProgram() {
        System.out.println("(NMU) interpreting program...");
        SymbolTable symTbl = new SymbolTable();
        for (ActionNode action : program) {
            action.execute(symTbl);
        }
        System.out.println("(NMU) Symbol table:");
        System.out.println(symTbl);
    }

    /**
     * Compiles the NMU program into ALT machine instructions.
     * Writes the compiled instructions to a temporary file.
     *
     * @throws IOException If an error occurs while writing to the file.
     */
    public void compileProgram() throws IOException {
        System.out.println("(NMU) compiling program to " + TMP_NMU_FILE + "...");
        try (PrintWriter out = new PrintWriter(TMP_NMU_FILE)) {
            for (ActionNode action : program) {
                action.compile(out);
            }
        }
    }

    /**
     * Executes the compiled NMU program using the Alaton machine.
     * Reads the compiled file, assembles the instructions, and runs them.
     *
     * @throws FileNotFoundException If the compiled file cannot be found.
     */
    public void executeProgram() throws FileNotFoundException {
        Scanner altIn = new Scanner(new File(TMP_NMU_FILE));
        Alaton machine = new Alaton();
        machine.assemble(altIn, false);
        machine.execute();
        altIn.close();
    }

    /**
     * Runs the NMU interpreter.
     * Reads NMU code from a file or standard input, processes it, and executes it.
     *
     * @param args Command-line arguments specifying the NMU input file (optional).
     * @throws IOException If there are issues with file operations.
     */
    public static void main(String[] args) throws IOException {
        Scanner nmuIn;
        boolean stdin = false;

        if (args.length == 0) {
            nmuIn = new Scanner(System.in);
            stdin = true;
        } else if (args.length == 1) {
            nmuIn = new Scanner(new File(args[0]));
        } else {
            System.out.println("Usage: java Namu [filename.nmu]");
            System.exit(1);
            return;
        }

        Namu interpreter = new Namu(nmuIn, stdin);
        interpreter.buildProgram();
        interpreter.displayProgram();
        interpreter.interpretProgram();
        interpreter.compileProgram();
        interpreter.executeProgram();
    }
}
