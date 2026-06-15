package machine.instructions;
import machine.*;

/**
 * Represents the MUL (multiplication) instruction, which multiplies
 * two values from the instruction stack and pushes the result back.
 */
public class Multiply implements Instruction {
    private final InstructionStack stack;
    private int value;
    private int secValue;

    /**
     * Constructs a Multiply instruction linked to the given Alaton machine.
     *
     * @param machine The Alaton machine containing the instruction stack.
     */
    public Multiply(Alaton machine){
        this.stack = machine.getInstructionStack();
        this.value = 0;
        this.secValue = 0;
    }

    /**
     * Executes the MUL operation by popping two values from the stack,
     * multiplying them, and pushing the result back onto the stack.
     */
    @Override
    public void execute() {
        this.value = this.stack.pop();
        this.secValue = this.stack.pop();
        int multiply = this.value * this.secValue;
        this.stack.push(multiply);
    }

    /**
     * Returns a string representation of the MULTIPLY instruction.
     *
     * @return A string in the format "MUL".
     */
    @Override
    public String toString() {
        return Alaton.MULTIPLY;
    }
}
