
/**
 * An abstract data storage class for turning search queries into tree structures. In a finished tree,
 * a task can be passed to the root's {@code match()} method to see if it matches the search query used
 * to create the tree.
 *
 * @author Jordan Greenbaum
 * @version 25 April 2021
 */
public abstract class QueryNode
{
    private QueryNode parent; // this node's parent
    
    /**
     * Constructs a new QueryNode object with the specified parent node.
     * 
     * @param parent the constructed QueryNode's parent
     */
    public QueryNode(QueryNode parent)
    {
        this.parent = parent;
    }
    
    /**
     * Checks whether or not the specified Task matches the data in this node.
     * 
     * @param task the Task that will be checked for a match
     * @return {@code true} if the specified Task matches the data in this node
     */
    public abstract boolean match(Task task);
    
    /**
     * Links the specified node to this node. This method is only supported in subclasses that represent
     * logical operators and need to be linked to other nodes.
     * 
     * @param node the node to add
     */
    public void addNode(QueryNode node)
    {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Gets the parent of this node.
     * 
     * @return this node's parent
     */
    public QueryNode getParent()
    {
        return this.parent;
    }
}
