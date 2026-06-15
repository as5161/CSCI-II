import java.util.*;

/**
 * Represents a graph that implements the IGraph interface.
 * This graph stores nodes in a HashMap where each node's data is mapped to its corresponding node.
 *
 * @param <D> the type of data contained in the nodes
 */
public class Graph<D> implements IGraph<D> {
    private final Map<D, Node<D>> nodes;

    /**
     * Constructs a new empty graph.
     */
    public Graph() {
        this.nodes = new HashMap<>();
    }

    /**
     * Returns a collection of all nodes in the graph.
     *
     * @return a collection of all nodes
     */
    @Override
    public Collection<Node<D>> getNodes() {
        return nodes.values();
    }

    /**
     * Checks if a node with the specified data exists in the graph.
     *
     * @param nodeData the data of the node to check for
     * @return true if the node exists, false otherwise
     */
    @Override
    public boolean hasNode(D nodeData) {
        return nodes.containsKey(nodeData);
    }

    /**
     * Returns the node associated with the specified data.
     *
     * @param nodeData the data of the node to retrieve
     * @return the node corresponding to the data, or null if it does not exist
     */
    @Override
    public Node<D> getNode(D nodeData) {
        return nodes.get(nodeData);
    }

    /**
     * Adds an edge from one node to another. If either node does not exist, it will be created.
     *
     * @param from the data of the starting node
     * @param to the data of the target node
     */
    @Override
    public void addEdge(D from, D to) {
        Node<D> fromNode = nodes.computeIfAbsent(from, Node::new);
        Node<D> toNode = nodes.computeIfAbsent(to, Node::new);
        fromNode.addNeighbor(toNode);
    }

    /**
     * Adds a node with the specified data to the graph. If the node already exists, it is not added again.
     *
     * @param data the data for the new node
     * @return the node that was added or already existed
     */
    @Override
    public Node<D> addNode(D data) {
        return nodes.computeIfAbsent(data, Node::new);
    }
}
