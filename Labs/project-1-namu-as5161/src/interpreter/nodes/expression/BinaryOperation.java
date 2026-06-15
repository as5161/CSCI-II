package interpreter.nodes.expression;

import common.SymbolTable;
import java.io.PrintWriter;

/**
 * Represents a binary operation (+, -, *, /, %, ^).
 */
public class BinaryOperation implements ExpressionNode {
    private final String operator;
    private final ExpressionNode leftChild;
    private final ExpressionNode rightChild;

    /**
     * Constructs a BinaryOperation with an operator and two operand expressions.
     *
     * @param operator The binary operator.
     * @param leftChild The left operand.
     * @param rightChild The right operand.
     */
    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild) {
        this.operator = operator;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    /**
     * Evaluates the binary operation using the provided symbol table.
     *
     * @param symTbl The symbol table storing variable values.
     * @return The result of the evaluated binary operation.
     */
    @Override
    public int evaluate(SymbolTable symTbl) {
        int left = leftChild.evaluate(symTbl);
        int right = rightChild.evaluate(symTbl);

        switch (operator) {
            case "+": return left + right;
            case "-": return left - right;
            case "*": return left * right;
            case "/":
                if (right == 0) throw new RuntimeException("Divide by zero error");
                return left / right;
            case "^":
                return (int) Math.pow(left, right);
            case "%":
                if (right == 0) throw new RuntimeException("Modulo by zero error");
                return left % right;
            default:
                throw new RuntimeException("Unexpected binary operator: " + operator);
        }
    }

    /**
     * Emits the binary operation in infix notation for debugging or display.
     */
    @Override
    public void emit() {
        System.out.print("( ");
        leftChild.emit();
        System.out.print(" " + operator + " ");
        rightChild.emit();
        System.out.print(" )");
    }

    /**
     * Compiles the binary operation into low-level machine instructions.
     *
     * @param out The PrintWriter to write compiled instructions.
     */
    @Override
    public void compile(PrintWriter out) {
        leftChild.compile(out);
        rightChild.compile(out);
        switch (operator) {
            case "+": out.println("ADD"); break;
            case "-": out.println("SUB"); break;
            case "*": out.println("MUL"); break;
            case "/": out.println("DIV"); break;
            case "%": out.println("MOD"); break;
            case "^": out.println("POW"); break;
        }
    }

    /**
     * Returns a string representation of the binary operation in infix notation.
     *
     * @return A string representing the binary operation.
     */
    @Override
    public String toString() {
        return "( " + leftChild + " " + operator + " " + rightChild + " )";
    }
}
