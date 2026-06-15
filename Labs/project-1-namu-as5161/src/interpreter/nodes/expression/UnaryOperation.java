package interpreter.nodes.expression;

import common.SymbolTable;
import java.io.PrintWriter;

/**
 * Represents a unary operation (! for negation, $ for square root).
 */
public class UnaryOperation implements ExpressionNode {
    private final String operator;
    private final ExpressionNode child;

    /**
     * Constructs a UnaryOperation node with an operator and a child expression.
     *
     * @param operator The unary operator (! for negation, $ for square root).
     * @param child    The expression to be operated on.
     */
    public UnaryOperation(String operator, ExpressionNode child) {
        this.operator = operator;
        this.child = child;
    }

    /**
     * Evaluates the unary operation.
     *
     * Negation (!) returns the negative of the child expression.
     * Square root ($) returns the integer square root of the child expression,
     * throwing an error if the value is negative.
     *
     * @param symTbl The symbol table containing variable values.
     * @return The result of applying the unary operation.
     */
    @Override
    public int evaluate(SymbolTable symTbl) {
        int value = child.evaluate(symTbl);
        switch (operator) {
            case "!":
                return -value;
            case "$":
                if (value < 0) {
                    throw new RuntimeException("Cannot take square root of negative number");
                }
                return (int) Math.sqrt(value);
            default:
                throw new RuntimeException("Unknown unary operator: " + operator);
        }
    }

    /**
     * Emits the unary operation in infix notation.
     * Example output: `!x` or `$y`
     */
    @Override
    public void emit() {
        System.out.print(operator);
        child.emit();
    }

    /**
     * Compiles the unary operation into low-level machine instructions.
     * Example output:
     * LOAD x
     * NEG
     *
     * @param out The PrintWriter to write compiled instructions.
     */
    @Override
    public void compile(PrintWriter out) {
        child.compile(out);
        if (operator.equals("!")) {
            out.println("NEG");
        } else if (operator.equals("$")) {
            out.println("SQRT");
        }
    }

    /**
     * Returns a string representation of the unary operation.
     *
     * @return A string in the form of "operator expression".
     */
    @Override
    public String toString() {
        return operator + " " + child;
    }
}
