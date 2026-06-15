import java.util.HashSet;
import java.util.Set;

/**
 * Represents a node in a graph. Each node stores a piece of data and a set of neighboring nodes.
 *
 * @param <T> the type of data stored in the node
 */
public class Node<T> {
    private final T data;
    private final Set<Node<T>> neighbors;

    /**
     * Constructs a new node with the specified data.
     *
     * @param data the data to be stored in the node
     */
    public Node(T data) {
        this.data = data;
        this.neighbors = new HashSet<>();
    }

    /**
     * Returns the data stored in this node.
     *
     * @return the data stored in the node
     */
    public T getData() {
        return data;
    }

    /**
     * Returns the set of neighbors connected to this node.
     *
     * @return a set containing the neighboring nodes
     */
    public Set<Node<T>> getNeighbors() {
        return neighbors;
    }

    /**
     * Adds a neighboring node to this node's set of neighbors.
     *
     * @param neighbor the node to add as a neighbor
     */
    public void addNeighbor(Node<T> neighbor) {
        neighbors.add(neighbor);
    }

    /**
     * Returns a string representation of this node, which is the string representation of its data.
     *
     * @return a string representation of the node's data
     */
    @Override
    public String toString() {
        return data.toString();
    }
}
