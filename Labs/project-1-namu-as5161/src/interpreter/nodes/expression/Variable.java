package interpreter.nodes.expression;

import common.SymbolTable;
import java.io.PrintWriter;

/**
 * Represents a variable in an expression.
 */
public class Variable implements ExpressionNode {
    private final String name;

    /**
     * Constructs a Variable node with a given name.
     *
     * @param name The name of the variable.
     */
    public Variable(String name) {
        this.name = name;
    }

    /**
     * Evaluates the variable by retrieving its value from the symbol table.
     *
     * Throws an error if the variable has not been initialized.
     *
     * @param symTbl The symbol table containing variable values.
     * @return The value of the variable.
     */
    @Override
    public int evaluate(SymbolTable symTbl) {
        if (!symTbl.has(name)) {
            throw new RuntimeException("Uninitialized variable: " + name);
        }
        return symTbl.get(name);
    }

    /**
     * Emits the variable name in infix notation.
     * Example output: `x`
     */
    @Override
    public void emit() {
        System.out.print(name);
    }

    /**
     * Compiles the variable into a machine instruction for loading its value.
     * Example output:
     * LOAD x
     *
     * @param out The PrintWriter to write compiled instructions.
     */
    @Override
    public void compile(PrintWriter out) {
        out.println("LOAD " + name);
    }

    /**
     * Returns the string representation of the variable.
     *
     * @return The variable name.
     */
    @Override
    public String toString() {
        return name;
    }
}
