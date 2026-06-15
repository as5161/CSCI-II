package machine.instructions;

import machine.*;

/**
 * Represents the MOD (modulus) instruction, which calculates the remainder
 * of the division of two values from the instruction stack.
 */
public class Modulus implements Instruction {
    private final InstructionStack stack;

    /**
     * Constructs a Modulus instruction linked to the given Alaton machine.
     *
     * @param machine The Alaton machine containing the instruction stack.
     */
    public Modulus(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Executes the MOD operation by popping two values from the stack,
     * computing the remainder of their division, and pushing the result back onto the stack.
     */
    @Override
    public void execute() {
        int value = this.stack.pop();
        int secValue = this.stack.pop();

        int result = secValue % value;
        this.stack.push(result);
    }

    /**
     * Returns a string representation of the MODULUS instruction.
     *
     * @return A string in the format "MOD".
     */
    @Override
    public String toString() {
        return Alaton.MODULUS;
    }
}
