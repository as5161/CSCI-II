package interpreter.nodes.action;

import common.SymbolTable;
import interpreter.nodes.NamuNode;

/**
 * An NamuNode that performs an action but does not calculate a new
 * value.  The distinction between ActionNode and ExpressionNode is only
 * useful when the nodes are directly executed by the Namu interpreter.
 *
 * @author RIT CS
 */
public interface ActionNode extends NamuNode {
    /**
     * Perform the action represented by this node. Actions are
     * things like printing or changing variable values via assignment.
     *
     * @param symTbl the table where variable values are stored
     */
    void execute(SymbolTable symTbl);
}
