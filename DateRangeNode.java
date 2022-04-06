import java.time.LocalDate;

/**
 * A QueryNode that represents a range of dates in a search query.
 *
 * @author Jordan Greenbaum
 * @version 25 April 2021
 */
public class DateRangeNode extends QueryNode
{
    private LocalDate start; // the start of this date range
    private LocalDate end; // the end of this date range
    
    /**
     * Constructs a new DateRangeNode object with the specified parent node that represents the specified range of dates.
     * 
     * @param parent this node's parent
     * @param range a String containing the date range this node should represent in the format MM/DD/YYYY--MM/DD/YYYY
     */
    public DateRangeNode(QueryNode parent, String range)
    {
        super(parent);
        
        String[] args = range.split("--");
        
        this.start = Model.parseDate(args[0]);
        this.end   = Model.parseDate(args[1]);
    }
    
    /**
     * Checks if the specified Task's due date falls within the range of dates this node represents.
     * 
     * @param task the Task to check
     * @return {@code true} if the specified Task's due date falls within the range of dates this node represents
     */
    public boolean match(Task task)
    {
        return (start.isBefore(task.getDate()) || start.isEqual(task.getDate()))
            && (end.isAfter(task.getDate()) || end.isEqual(task.getDate()));
    }
}
