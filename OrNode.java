
/**
 * A QueryNode that represents a logical OR operator in a search query.
 *
 * @author Jordan Greenbaum
 * @version 25 April 2021
 */
public class OrNode extends QueryNode
{
    private LinkedList<QueryNode> args; // all arguments included in this OR operation
    
    /**
     * Constructs a new OrNode object with the specified parent node.
     * 
     * @param parent this node's parent
     */
    public OrNode(QueryNode parent)
    {
        super(parent);
        this.args = new LinkedList<QueryNode>();
    }
    
    /**
     * Checks if the specified Task matches any of the query arguments stored in this node.
     * 
     * @param task the Task to check
     * @return {@code true} if the specified Task matches any of the query arguments covered by this OR operator
     */
    public boolean match(Task task)
    {
        for (QueryNode n : args)
        {
            // if one check returns true, the entire OR statement is true
            if (n.match(task))
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Adds the specified node to the list of nodes that must be matched in order for this node's {@link #match} method
     * to return {@code true}.
     * 
     * @param node the node to add
     */
    public void addNode(QueryNode node)
    {
        args.addLast(node);
    }
}
