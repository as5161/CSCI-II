package machine.instructions;

import common.Errors;
import common.SymbolTable;
import machine.Alaton;
import machine.InstructionStack;

/**
 * Represents the LOAD instruction, which retrieves a value from the symbol table
 * and pushes it onto the instruction stack.
 * If the variable has not been initialized, an error is reported, and the program terminates.
 */
public class Load implements Instruction {
    private final InstructionStack stack;
    private final SymbolTable table;
    private final String name;

    /**
     * Constructs a Load instruction linked to the given Alaton machine.
     *
     * @param name    The name of the variable to load.
     * @param machine The Alaton machine containing the instruction stack and symbol table.
     */
    public Load(String name, Alaton machine) {
        this.stack = machine.getInstructionStack();
        this.table = machine.getSymbolTable();
        this.name = name;
    }

    /**
     * Executes the LOAD operation by retrieving the variable's value from the symbol table
     * and pushing it onto the stack.
     * If the variable is uninitialized, an error is reported, and the program exits.
     */
    @Override
    public void execute() {
        if (!this.table.has(this.name)) {
            Errors.report(Errors.Type.UNINITIALIZED, this.name);
            System.exit(-1);
        } else {
            this.stack.push(this.table.get(this.name));
        }
    }

    /**
     * Returns a string representation of the LOAD instruction.
     *
     * @return A string in the format "LOAD variableName".
     */
    @Override
    public String toString() {
        return Alaton.LOAD + " " + this.name;
    }
}
