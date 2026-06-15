import java.util.Collection;

/**
 * Interface for a directed graph.
 * Provides basic operations for working with nodes and edges in the graph.
 *
 * @param <DataType> the type of data contained in the nodes of the graph
 */
public interface IGraph<DataType> {

    /**
     * Retrieves all nodes in the graph.
     *
     * @return a collection of all nodes in the graph
     */
    Collection<Node<DataType>> getNodes();

    /**
     * Checks if a node with the specified data exists in the graph.
     *
     * @param nodeData the data of the node to check for
     * @return true if the node exists, false otherwise
     */
    boolean hasNode(DataType nodeData);

    /**
     * Retrieves the node corresponding to the specified data.
     *
     * @param nodeData the data of the node to retrieve
     * @return the node associated with the data, or null if it does not exist
     */
    Node<DataType> getNode(DataType nodeData);

    /**
     * Adds an edge between two nodes in the graph. If the nodes do not exist, they are created.
     *
     * @param from the data of the starting node
     * @param to the data of the target node
     */
    void addEdge(DataType from, DataType to);

    /**
     * Adds a new node with the specified data to the graph. If the node already exists, it is not added again.
     *
     * @param data the data for the new node
     * @return the node that was added or already existed
     */
    Node<DataType> addNode(DataType data);
}
