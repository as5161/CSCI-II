package machine.instructions;

import machine.*;

/**
 * Represents the ADD instruction, which pops two values from the stack,
 * adds them together, and pushes the result back onto the stack.
 */
public class Add implements Instruction {
    private final InstructionStack stack;
    private int value;
    private int secValue;

    /**
     * Constructs an Add instruction linked to the given Alaton machine.
     *
     * @param machine The Alaton machine containing the instruction stack.
     */
    public Add(Alaton machine) {
        this.stack = machine.getInstructionStack();
        this.value = 0;
        this.secValue = 0;
    }

    /**
     * Executes the ADD operation by popping two values from the stack,
     * adding them together, and pushing the result back onto the stack.
     */
    @Override
    public void execute() {
        this.value = this.stack.pop();
        this.secValue = this.stack.pop();
        int add = this.value + this.secValue;
        this.stack.push(add);
    }

    /**
     * Returns a string representation of the ADD instruction.
     *
     * @return The string "ADD" representing this instruction.
     */
    @Override
    public String toString() {
        return Alaton.ADD;
    }
}
