
/**
 * A QueryNode that represents a single tag in a search query.
 *
 * @author Jordan Greenbaum
 * @version 25 April 2021
 */
public class TagNode extends QueryNode
{
    private String tag; // the tag this node represents
    
    /**
     * Constructs a new TagNode object with the specified parent node that represents the specified tag.
     * 
     * @param parent this node's parent node
     * @param tag the tag this node will represent
     */
    public TagNode(QueryNode parent, String tag)
    {
        super(parent);
        this.tag = tag.toLowerCase();
    }
    
    /**
     * Checks if the specified Task has the tag this node represents.
     * 
     * @param task the Task to check
     * @return {@code true} if the specified Task has the tag this node represents
     */
    public boolean match(Task task)
    {
        return tag.equals("untagged")
            ? task.getTags().size() == 0 // if the task has no tags, it should match with 'untagged'
            : task.getTags().contains(this.tag);
    }
}
