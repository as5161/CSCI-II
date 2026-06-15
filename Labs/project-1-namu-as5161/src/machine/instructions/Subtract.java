package machine.instructions;

import machine.*;

/**
 * Represents the SUBTRACT instruction, which subtracts the top two values on the stack.
 */
public class Subtract implements Instruction {
    private final InstructionStack stack;
    private int value;
    private int secValue;

    /**
     * Constructs a Subtract instruction for the given machine.
     *
     * @param machine The Alaton machine that contains the instruction stack.
     */
    public Subtract(Alaton machine) {
        this.stack = machine.getInstructionStack();
        this.value = 0;
        this.secValue = 0;
    }

    /**
     * Executes the SUBTRACT instruction by popping two values from the stack,
     * subtracting the top value from the second value, and pushing the result back.
     */
    @Override
    public void execute() {
        this.value = this.stack.pop();
        this.secValue = this.stack.pop();
        int minus = this.secValue - this.value;
        this.stack.push(minus);
    }

    /**
     * Returns a string representation of the SUBTRACT instruction.
     *
     * @return A string representation of the operation.
     */
    @Override
    public String toString() {
        return Alaton.SUBTRACT;
    }
}
