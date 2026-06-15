package machine.instructions;

import common.Errors;
import machine.*;

import static java.lang.Math.sqrt;

/**
 * Represents the SQRT (Square Root) instruction, which computes the integer square root of a value on the stack.
 */
public class SquareRoot implements Instruction {
    /** The instruction stack associated with the machine. */
    private final InstructionStack stack;

    /** The value retrieved from the stack to compute the square root. */
    private int value;

    /**
     * Constructs a SquareRoot instruction for the given machine.
     *
     * @param machine The Alaton machine that contains the instruction stack.
     */
    public SquareRoot(Alaton machine) {
        this.stack = machine.getInstructionStack();
        this.value = 0;
    }

    /**
     * Executes the SQRT instruction by popping a value from the stack, computing its square root,
     * and pushing the result back onto the stack. If the value is negative, an error is reported.
     */
    @Override
    public void execute() {
        int x = this.stack.pop();
        if (x < 0) {
            Errors.report(Errors.Type.NEGATIVE_SQUARE_ROOT);
            System.exit(-1);
        } else {
            this.stack.push((int) sqrt(x));
        }
    }

    /**
     * Returns a string representation of the SQRT instruction.
     *
     * @return The string "SQRT".
     */
    @Override
    public String toString() {
        return Alaton.SQUARE_ROOT;
    }
}
