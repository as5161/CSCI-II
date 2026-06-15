package machine.instructions;

import machine.InstructionStack;
import machine.Alaton;

/**
 * Represents the PUSH instruction, which pushes a given integer value onto the stack.
 */
public class Push implements Instruction {
    /** The integer value to be pushed onto the stack. */
    private final int value;

    /** The instruction stack associated with the machine. */
    private final InstructionStack stack;

    /**
     * Constructs a PUSH instruction with a specific value.
     *
     * @param value The integer value to push onto the stack.
     * @param machine The Alaton machine that contains the instruction stack.
     */
    public Push(int value, Alaton machine) {
        this.value = value;
        this.stack = machine.getInstructionStack();
    }

    /**
     * Executes the PUSH instruction by placing the stored value onto the stack.
     */
    @Override
    public void execute() {
        this.stack.push(this.value);
    }

    /**
     * Returns a string representation of the PUSH instruction.
     *
     * @return A string in the format "PUSH value".
     */
    @Override
    public String toString() {
        return Alaton.PUSH + " " + this.value;
    }
}
