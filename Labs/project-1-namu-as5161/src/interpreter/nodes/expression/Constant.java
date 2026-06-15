package interpreter.nodes.expression;

import common.SymbolTable;
import java.io.PrintWriter;

/**
 * Represents an integer constant in an expression.
 */
public class Constant implements ExpressionNode {
    private final int value;

    /**
     * Constructs a Constant node with the given integer value.
     *
     * @param value The integer value of the constant.
     */
    public Constant(int value) {
        this.value = value;
    }

    /**
     * Evaluates the constant, which simply returns its stored value.
     *
     * @param symTbl The symbol table (not used for constants).
     * @return The integer value of the constant.
     */
    @Override
    public int evaluate(SymbolTable symTbl) {
        return value;
    }

    /**
     * Emits the constant as a raw integer value.
     */
    @Override
    public void emit() {
        System.out.print(value);
    }

    /**
     * Compiles the constant into a machine instruction to push it onto the stack.
     *
     * @param out The PrintWriter to write compiled instructions.
     */
    @Override
    public void compile(PrintWriter out) {
        out.println("PUSH " + value);
    }

    /**
     * Returns a string representation of the constant.
     *
     * @return The integer value as a string.
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
