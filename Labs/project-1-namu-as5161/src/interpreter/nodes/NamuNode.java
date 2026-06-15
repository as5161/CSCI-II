package interpreter.nodes;

import java.io.PrintWriter;

/**
 * The top-level abstraction for all nodes in the Namu parse tree.  All nodes
 * are capable of being displayed in infix format, and are capable of generating
 * the ALT instructions that can be executed later.
 *
 * @author RIT CS
 */
public interface NamuNode {
    /**
     * Print to standard output the infix format for this node.
     */
    void emit();

    /**
     * Generates the compiled ALT instructions for this node/descendants to be
     * later executed.
     *
     * @param out the stream to write output to using out.println()
     */
    void compile(PrintWriter out);
}
