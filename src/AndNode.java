
/**
 * A QueryNode that represents a logical AND operator in a search query.
 *
 * @author Jordan Greenbaum
 * @version 25 April 2021
 */
public class AndNode extends QueryNode
{
    private LinkedList<QueryNode> args; // all arguments included in this AND operation
    
    /**
     * Constructs a new AndNode object with the specified parent node.
     * 
     * @param parent this node's parent
     */
    public AndNode(QueryNode parent)
    {
        super(parent);
        this.args = new LinkedList<QueryNode>();
    }
    
    /**
     * Checks if the specified Task matches all query arguments stored in this node.
     * 
     * @param task the Task to check
     * @return {@code true} if the specified Task matches all query arguments covered by this AND operator
     */
    public boolean match(Task task)
    {
        for (QueryNode n : args)
        {
            // if one check returns false, the entire AND statement is false
            if (!n.match(task))
            {
                return false;
            }
        }
        
        return true;
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
