package machine.instructions;

import machine.InstructionStack;
import machine.Alaton;

/**
 * Represents the PRINT instruction, which removes the top value from the stack
 * and prints it to the output.
 */
public class Print implements Instruction {
    private final InstructionStack stack;

    /**
     * Constructs a Print instruction linked to the given Alaton machine.
     *
     * @param machine The Alaton machine containing the instruction stack.
     */
    public Print(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Executes the PRINT operation by popping the top value from the stack
     * and printing it to standard output.
     */
    @Override
    public void execute() {
        System.out.println(stack.pop());
    }

    /**
     * Returns a string representation of the PRINT instruction.
     *
     * @return A string in the format "PRINT".
     */
    @Override
    public String toString() {
        return Alaton.PRINT;
    }
}
