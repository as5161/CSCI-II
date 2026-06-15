package interpreter.nodes.action;

import common.SymbolTable;
import interpreter.nodes.NamuNode;
import interpreter.nodes.expression.ExpressionNode;

import java.io.PrintWriter;

/**
 * The Print node represents the printing of an expression to the output.
 */
public class Print implements ActionNode {
    private final ExpressionNode child;

    /**
     * Constructs a Print node with an expression.
     *
     * @param child The expression to be printed.
     */
    public Print(ExpressionNode child) {
        this.child = child;
    }

    /**
     * Executes the print operation by evaluating the expression
     * and displaying its result to standard output.
     *
     * @param symTbl The symbol table used for evaluating variables.
     */
    @Override
    public void execute(SymbolTable symTbl) {
        System.out.println(child.evaluate(symTbl));
    }

    /**
     * Emits the Print node in infix notation for debugging or display.
     */
    @Override
    public void emit() {
        System.out.print("Print ");
        child.emit();
    }

    /**
     * Compiles the print operation into machine instructions.
     *
     * @param out The PrintWriter to write compiled instructions.
     */
    @Override
    public void compile(PrintWriter out) {
        child.compile(out);
        out.println("PRINT");
    }

    /**
     * Returns a string representation of the print operation.
     *
     * @return A string in the form of "Print expression".
     */
    @Override
    public String toString() {
        return "Print " + child;
    }
}
