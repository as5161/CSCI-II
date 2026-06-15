package machine.instructions;
import machine.*;

/**
 * Represents the NEG (negation) instruction, which negates
 * the top value of the instruction stack and pushes the result back.
 */
public class Negate implements Instruction {
    private final InstructionStack stack;
    private int value;

    /**
     * Constructs a Negate instruction linked to the given Alaton machine.
     *
     * @param machine The Alaton machine containing the instruction stack.
     */
    public Negate(Alaton machine){
        this.stack = machine.getInstructionStack();
        this.value = 0;
    }

    /**
     * Executes the NEG operation by popping the top value from the stack,
     * negating it, and pushing the result back onto the stack.
     */
    @Override
    public void execute() {
        this.value = -1 * this.stack.pop();
        this.stack.push(value);
    }

    /**
     * Returns a string representation of the NEGATE instruction.
     *
     * @return A string in the format "NEG".
     */
    @Override
    public String toString() {
        return Alaton.NEGATE;
    }
}
