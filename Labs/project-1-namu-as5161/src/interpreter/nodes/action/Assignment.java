package interpreter.nodes.action;

import common.SymbolTable;
import interpreter.nodes.expression.ExpressionNode;
import java.io.PrintWriter;

/**
 * The Assignment node represents an assignment operation that stores
 * the result of an expression in a variable.
 */
public class Assignment implements ActionNode {
    private final String name;
    private final ExpressionNode child;

    /**
     * Constructs an Assignment node with a variable name and an expression.
     *
     * @param name  The name of the variable to assign to.
     * @param child The expression whose result will be stored.
     */
    public Assignment(String name, ExpressionNode child) {
        this.name = name;
        this.child = child;
    }

    /**
     * Executes the assignment operation by evaluating the expression
     * and storing its result in the symbol table.
     *
     * @param symTbl The symbol table storing variable values.
     */
    @Override
    public void execute(SymbolTable symTbl) {
        int value = child.evaluate(symTbl);
        symTbl.set(name, value);
    }

    /**
     * Emits the assignment operation in infix notation for debugging or display.
     */
    @Override
    public void emit() {
        System.out.print(name + " = ");
        child.emit();
    }

    /**
     * Compiles the assignment operation into low-level machine instructions.
     *
     * @param out The PrintWriter to write compiled instructions.
     */
    @Override
    public void compile(PrintWriter out) {
        child.compile(out);
        out.println("STORE " + name);
    }

    /**
     * Returns a string representation of the assignment operation.
     *
     * @return A string in the form of "variable = expression".
     */
    @Override
    public String toString() {
        return name + " = " + child;
    }
}
