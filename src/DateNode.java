import java.time.LocalDate;

/**
 * A QueryNode that represents a single date in a search query.
 *
 * @author Jordan Greenbaum
 * @version 25 April 2021
 */
public class DateNode extends QueryNode
{
    private LocalDate date; // the date this node represents
    
    /**
     * Constructs a new DateNode object with the specified parent node that represents the specified date.
     * 
     * @param parent this node's parent
     * @param date a String containing the date this node will represent in MM/DD/YYYY format
     */
    public DateNode(QueryNode parent, String date)
    {
        super(parent);
        this.date = Model.parseDate(date);
    }
    
    /**
     * Checks if the specified Task's due date matches the one stored in this node.
     * 
     * @param task the Task to check
     * @return {@code true} if the specified Task's due date matches the one stored in this node
     */
    public boolean match(Task task)
    {
        return date.isEqual(task.getDate());
    }
}
