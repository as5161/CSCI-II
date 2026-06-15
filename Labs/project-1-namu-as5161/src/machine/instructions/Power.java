package machine.instructions;

import machine.*;

/**
 * Represents the POW (power) instruction, which raises the second value
 * on the stack to the power of the first value and pushes the result back.
 */
public class Power implements Instruction {
    private final InstructionStack stack;

    /**
     * Constructs a Power instruction linked to the given Alaton machine.
     *
     * @param machine The Alaton machine containing the instruction stack.
     */
    public Power(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Executes the POW operation by popping two values from the stack,
     * raising the second value to the power of the first, and pushing
     * the result back onto the stack.
     */
    @Override
    public void execute() {
        int value = this.stack.pop();
        int secValue = this.stack.pop();
        int result = (int) Math.pow(secValue, value);
        this.stack.push(result);
    }

    /**
     * Returns a string representation of the POWER instruction.
     *
     * @return A string in the format "POW".
     */
    @Override
    public String toString() {
        return Alaton.POWER;
    }
}
