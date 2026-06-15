package machine;

import common.Errors;
import common.SymbolTable;
import machine.instructions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;


/**
 * The machine can process/execute a series of low level ALT instructions using
 * an instruction stack and symbol table.
 *
 * @author RIT CS
 */
public class Alaton {
    /** the push instruction */
    public final static String PUSH = "PUSH";
    /** the print instruction */
    public final static String PRINT = "PRINT";
    /** the store instruction */
    public final static String STORE = "STORE";
    /** the load instruction */
    public final static String LOAD = "LOAD";
    /** the negate instruction */
    public final static String NEGATE = "NEG";
    /** the square root instruction */
    public final static String SQUARE_ROOT = "SQRT";
    /** the add instruction */
    public final static String ADD = "ADD";
    /** the subtract instruction */
    public final static String SUBTRACT = "SUB";
    /** the multiply instruction */
    public final static String MULTIPLY = "MUL";
    /** the divide instruction */
    public final static String DIVIDE = "DIV";
    /** the modulus instruction */
    public final static String MODULUS = "MOD";
    /** the power instruction */
    public final static String POWER = "POW";

    /** the list of valid machine instructions */
    public static final List< String > OPERATIONS =
            List.of(
                    ADD,
                    DIVIDE,
                    LOAD,
                    MODULUS,
                    MULTIPLY,
                    NEGATE,
                    POWER,
                    PUSH,
                    PRINT,
                    SQUARE_ROOT,
                    STORE,
                    SUBTRACT
            );

    /** the terminating character when reading machine instructions from user (not file) */
    private final static String EOF = ".";
    private SymbolTable symbols;
    private InstructionStack stack;
    private ArrayList<Instruction> InstructionList = new ArrayList<>();

    /**
     * Create a new machine, with an empty symbol table, instruction stack, and
     * list of instructions.
     */
    public Alaton() {
        this.symbols = new SymbolTable();
        this.stack = new InstructionStack();
    }

    /**
     * Return the instruction stack.
     *
     * @return the stack
     */
    public InstructionStack getInstructionStack() {
        return this.stack;
    }

    /**
     * Return the symbol table.
     *
     * @return the symbol table
     */
    public SymbolTable getSymbolTable() {
        return this.symbols;
    }


    /**
     * Assemble the machine instructions.
     *
     * @param altIn the input source
     * @param stdin true if input is coming from standard input (for prompting)
     */
    public void assemble(Scanner altIn, boolean stdin) {
        if (stdin) System.out.print("🤖 ");

        HashMap<String, Function<String[], Instruction>> instructions = new HashMap<>();
        instructions.put("ADD", f -> new Add(this));
        instructions.put("DIV", f -> new Divide(this));
        instructions.put("LOAD", f -> new Load(f[1], this));
        instructions.put("MOD", f -> new Modulus(this));
        instructions.put("MUL", f -> new Multiply(this));
        instructions.put("NEG", f -> new Negate(this));
        instructions.put("POW", f -> new Power(this));
        instructions.put("PRINT", f -> new Print(this));
        instructions.put("PUSH", f -> new Push(Integer.parseInt(f[1]), this));
        instructions.put("SQRT", f -> new SquareRoot(this));
        instructions.put("STORE", f -> new Store(f[1], this));
        instructions.put("SUB", f -> new Subtract(this));

        while (altIn.hasNextLine()) {
            String[] parts = altIn.nextLine().strip().split("\\s+");
            String operation = parts[0];

            if (!OPERATIONS.contains(operation)) {
                Errors.report(Errors.Type.ILLEGAL_INSTRUCTION, operation);
                System.exit(-1);
            }
            InstructionList.add(instructions.get(operation).apply(parts));
        }

        System.out.println("(ALT) Machine instructions:");
        for (Instruction inst : InstructionList) System.out.println(inst);
    }

    /**
     * Executes each assembled machine instruction in order.  When completed it
     * displays the symbol table and the instruction stack.
     */
    public void execute() {
        System.out.println("(ALT) Executing...");

        for (Instruction i : InstructionList){
            i.execute();
        }

        System.out.println("(ALT) Completed execution!");
        System.out.println("(ALT) Symbol table:");

        System.out.print(this.getSymbolTable());
        System.out.print(this.getInstructionStack());
    }

    /**
     * The main method.  Machine instructions can either be specified from standard input
     * (no command line), or from a file (only argument on command line).  From
     * here the machine assembles the instructions and then executes them.
     *
     * @param args command line argument (optional)
     * @throws FileNotFoundException if the machine file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        // determine input source
        Scanner altIn = null;
        boolean stdin = false;
        if (args.length == 0) {
            altIn = new Scanner(System.in);
            stdin = true;
        } else if (args.length == 1){
            altIn = new Scanner(new File(args[0]));
        } else {
            System.out.println("Usage: java Alaton [filename.alt]");
            System.exit(1);
        }

        Alaton machine = new Alaton();
        machine.assemble(altIn, stdin);            // assemble the machine instructions
        machine.execute();                         // execute the program
        altIn.close();
    }
}
