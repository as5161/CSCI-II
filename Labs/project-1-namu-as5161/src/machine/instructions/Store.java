package machine.instructions;

import common.SymbolTable;
import machine.*;

/**
 * Represents the STORE instruction, which stores the top value from the stack into a variable.
 */
public class Store implements Instruction {
    private final InstructionStack stack;
    private final String name;
    private final SymbolTable symbolTable;

    /**
     * Constructs a Store instruction for the given machine.
     *
     * @param name    The name of the variable to store the value in.
     * @param machine The Alaton machine that contains the instruction stack and symbol table.
     */
    public Store(String name, Alaton machine) {
        this.stack = machine.getInstructionStack();
        this.name = name;
        this.symbolTable = machine.getSymbolTable();
    }

    /**
     * Executes the STORE instruction by popping a value from the stack and storing it
     * in the symbol table under the specified variable name.
     */
    @Override
    public void execute() {
        int value = this.stack.pop();
        this.symbolTable.set(this.name, value);
    }

    /**
     * Returns a string representation of the STORE instruction.
     *
     * @return A string in the format "STORE variable_name".
     */
    @Override
    public String toString() {
        return "STORE " + this.name;
    }
}
