package machine.instructions;

import common.Errors;
import machine.*;

/**
 * Represents the DIVIDE instruction, which pops two values from the stack,
 * divides the second popped value by the first, and pushes the result back onto the stack.
 * If division by zero occurs, an error is reported, and the program terminates.
 */
public class Divide implements Instruction {
    private final InstructionStack stack;
    private int value;
    private int secValue;

    /**
     * Constructs a Divide instruction linked to the given Alaton machine.
     *
     * @param machine The Alaton machine containing the instruction stack.
     */
    public Divide(Alaton machine) {
        this.stack = machine.getInstructionStack();
        this.value = 0;
        this.secValue = 0;
    }

    /**
     * Executes the DIVIDE operation by popping two values from the stack.
     * If the first popped value is zero, an error is reported, and the program exits.
     * Otherwise, the second value is divided by the first, and the result is pushed onto the stack.
     */
    @Override
    public void execute() {
        int x = this.stack.pop();
        int y = this.stack.pop();
        if (x == 0) {
            Errors.report(Errors.Type.DIVIDE_BY_ZERO);
            System.exit(-1);
        } else {
            this.stack.push(y / x);
        }
    }

    /**
     * Returns a string representation of the DIVIDE instruction.
     *
     * @return The string "DIV" representing this instruction.
     */
    @Override
    public String toString() {
        return Alaton.DIVIDE;
    }
}
