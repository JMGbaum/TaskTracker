import java.util.GregorianCalendar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a single task in the taskmaster system. Each task can be
 * identified through its unique numeric ID and has a description, due date, and
 * list of associated tags.
 *
 * @author Jordan Greenbaum
 * @version 25 April 2021
 */
public class Task
{
    private static int                nextID = 0;  // the ID to use when creating the next Task object
    public  final  int                ID;          // this task's unique numeric ID
    private        String             description; // the text description of this task
    private        LinkedList<String> tags;        // a list of tags assigned to this task
    private        LocalDate          date;        // the due date for this task
    
    /**
     * Constructs a new Task object with its own unique numeric ID and specified description, due date,
     * and tags.
     * 
     * @param description this Task's description
     * @param date this Task's due date
     * @param tags a list of this Task's tags
     */
    public Task(String description, String date, LinkedList<String> tags)
    {
        this.ID = nextID++;
        this.description = description;
        this.date = Model.parseDate(date);
        this.tags = tags;
    }
    
    /**
     * Returns this Task's description.
     * 
     * @return this Task's description
     */
    public String getDescription()
    {
        return this.description;
    }
    
    /**
     * Returns a clone of the list of this Task's tags.
     * 
     * @return a clone of the list of this Task's tags
     */
    public LinkedList<String> getTags()
    {
        return this.tags.clone();
    }
    
    /**
     * Returns this Task's due date.
     * 
     * @return this Task's due date
     */
    public LocalDate getDate()
    {
        return this.date;
    }
    
    /**
     * Returns this Task's due date as a String in MM/DD/YYYY format.
     * 
     * @return this Task's due date as a String in MM/DD/YYYY format
     */
    public String getDateAsString()
    {
        return this.date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }
    
    /**
     * Sets this Task's description.
     * 
     * @param description this Task's new description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    /**
     * Sets this Task's tags.
     * 
     * @param tags the list of tags that will replace the current list
     */
    public void setTags(LinkedList<String> tags)
    {
        this.tags = tags;
    }
    
    /**
     * Sets this Task's due date.
     * 
     * @param date a String containing the new due date of this Task is MM/DD/YYYY format
     */
    public void setDate(String date)
    {
        this.date = Model.parseDate(date);
    }
    
    /**
     * Adds a new tag to this Task if it exists in the system and hasn't already been added to this Task.
     * 
     * @param tag the tag to add
     * @return {@code true} if the new tag was successfully added
     */
    public boolean addTag(String tag)
    {
        tag = tag.toLowerCase();
        if (Model.tagExists(tag) && !this.tags.contains(tag))
        {
            this.tags.addLast(tag);
            return true;
        }
        
        return false;
    }
    
    /**
     * Removes a tag from this Task.
     * 
     * @param tag the tag to remove
     * @return {@code true} if the tag was successfully removed
     */
    public boolean removeTag(String tag)
    {
        return this.tags.remove(tag);
    }
    
    /**
     * Checks whether this Task has the specified tag.
     * 
     * @param tag the tag to check for
     * @return {@code true} if this Task has the specified tag
     */
    public boolean hasTag(String tag)
    {
        // check for no tags if the search query is "untagged"
        if (tag.equals("untagged"))
        {
            return this.tags.isEmpty();
        }
        // otherwise, check if this Task's list of tags contains the one specified
        else
        {
            return this.tags.contains(tag);
        }
    }
    
    /**
     * Checks whether or not this Task has the same due date, description, and tags as the one specified.
     * This method may return true even if {@code this != task}.
     * 
     * @param task the Task to compare this one to
     * @return {@code true} if this Task has the same due date, description, and tags as the one specified
     */
    public boolean equals(Task task)
    {
        return this.date.isEqual(task.getDate())
            && this.description.equals(task.getDescription())
            && this.tags.equals(task.getTags());
    }
    
    /**
     * A static method that resets the Task ID counter so that the next Task object created will have an ID of 0.
     */
    public static void reset()
    {
        nextID = 0;
    }
}
