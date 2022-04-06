

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.time.LocalDate;

/**
 * The test class TaskTest.
 *
 * @author  Jordan Greenbaum
 * @version 25 April 2021
 */
public class TaskTest
{
    Task task;
    
    /**
     * Default constructor for test class TaskTest
     */
    public TaskTest()
    {
        Model.reset();
        Model.addTag("red");
        Model.addTag("orange");
        Model.addTag("yellow");
        Model.addTag("green");
        Model.addTag("blue");
        Model.addTag("violet");
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        task = new Task("Test task", "01/01/2001", new LinkedList<String>());
        
        task.addTag("red");
        task.addTag("orange");
        task.addTag("yellow");
        task.addTag("green");
        task.addTag("blue");
        task.addTag("violet");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        task = null;
    }
    
    @Test
    @DisplayName("Gets the description of a Task")
    public void testGetDescription1()
    {
        assertEquals("Test task", task.getDescription());
    }
    
    @Test
    @DisplayName("Returns null if the description is null")
    public void testGetDescription2()
    {
        Task t = new Task(null, "01/01/2000", null);
        assertEquals(null, t.getDescription());
    }
    
    @Test
    @DisplayName("The returned tag list != the actual tag list")
    public void testGetTags1()
    {
        LinkedList<String> tags = new LinkedList<String>();
        tags.addLast("red");
        tags.addLast("orange");
        
        Task t = new Task(null, "01/01/2000", tags);
        
        assertFalse(tags == t.getTags());
    }
    
    @Test
    @DisplayName("(The returned tag list).equals(the actual tag list)")
    public void testGetTags2()
    {
        LinkedList<String> tags = new LinkedList<String>();
        tags.addLast("red");
        tags.addLast("orange");
        
        Task t = new Task(null, "01/01/2000", tags);
        
        assertTrue(t.getTags().equals(tags));
    }
    
    @Test
    @DisplayName("Returns an empty list if there are no tags")
    public void testGetTags3()
    {
        LinkedList<String> tags = new LinkedList<String>();
        
        Task t = new Task(null, "01/01/2000", tags);
        
        assertTrue(t.getTags().isEmpty());
    }
    
    @Test
    @DisplayName("(The returned tag list).equals(the actual tag list)")
    public void testGetTags4()
    {
        Task t = new Task(null, "01/01/2000", null);
        
        try
        {
            t.getTags();
            fail("No exception was thrown");
        }
        catch (NullPointerException e)
        {
            assertTrue(true);
        }
    }
    
    @Test
    @DisplayName("Returns the due date")
    public void testGetDate1()
    {
        assertTrue(task.getDate().isEqual(LocalDate.of(2001,1,1)));
    }
    
    @Test
    @DisplayName("Returns the due date as a String")
    public void testGetDateAsString1()
    {
        assertEquals("01/01/2001", task.getDateAsString());
    }
    
    @Test
    @DisplayName("Sets the description")
    public void testSetDescription1()
    {
        task.setDescription("new description");
        
        assertEquals("new description", task.getDescription());
    }
    
    @Test
    @DisplayName("The description can be set to null")
    public void testSetDescription2()
    {
        task.setDescription(null);
        
        assertEquals(null, task.getDescription());
    }
    
    @Test
    @DisplayName("Sets the tags")
    public void testSetTags1()
    {
        Task t = new Task(null, "01/01/2000", new LinkedList<String>());
        
        LinkedList<String> tags = new LinkedList<String>();
        tags.addLast("red");
        tags.addLast("orange");
        tags.addLast("yellow");
        
        t.setTags(tags);
        
        assertTrue(t.getTags().equals(tags));
    }
    
    @Test
    @DisplayName("Replaces old tags")
    public void testSetTags2()
    {
        assertTrue(task.hasTag("green"));
        assertTrue(task.hasTag("blue"));
        assertTrue(task.hasTag("violet"));
        
        LinkedList<String> tags = new LinkedList<String>();
        tags.addLast("red");
        tags.addLast("orange");
        tags.addLast("yellow");
        
        task.setTags(tags);
        
        assertFalse(task.hasTag("green"));
        assertFalse(task.hasTag("blue"));
        assertFalse(task.hasTag("violet"));
    }
    
    @Test
    @DisplayName("Tags can be set to an empty list")
    public void testSetTags3()
    {
        LinkedList<String> tags = new LinkedList<String>();
        
        task.setTags(tags);
        
        assertTrue(task.getTags().isEmpty());
    }
    
    @Test
    @DisplayName("Tags can be set to null")
    public void testSetTags4()
    {
        try
        {
            task.setTags(null);
            assertTrue(true);
        }
        catch (Exception e)
        {
            fail("An exception was thrown");
        }
    }
    
    @Test
    @DisplayName("Sets the date")
    public void testSetDate1()
    {
        task.setDate("01/23/4567");
        
        assertEquals("01/23/4567", task.getDateAsString());
    }
    
    @Test
    @DisplayName("When the date String cannot be parsed, the date is set to null")
    public void testSetDate2()
    {
        task.setDate("abcdefg");
        assertEquals(null, task.getDate());
    }
    
    @Test
    @DisplayName("Throws a NullPointerException if the supplied date is null")
    public void testSetDate3()
    {
        try
        {
            task.setDate(null);
            fail("No exception was thrown");
        }
        catch (NullPointerException e)
        {
            assertTrue(true);
        }
    }
    
    @Test
    @DisplayName("Adds the specified tag to the Task")
    public void testAddTag1()
    {
        Task t = new Task(null, "01/01/2000", new LinkedList<String>());
        
        assertTrue(t.addTag("red"));
        
        assertTrue(t.hasTag("red"));
    }
    
    @Test
    @DisplayName("Returns false if the specified tag is already in the Task")
    public void testAddTag2()
    {
        assertFalse(task.addTag("red"));
    }
    
    @Test
    @DisplayName("Returns false it the specified tag has not been added to the system")
    public void testAddTag3()
    {
        assertFalse(task.addTag("banana"));
    }
    
    @Test
    @DisplayName("Throws a NullPointerException if the tag argument is null")
    public void testAddTag4()
    {
        try
        {
            task.addTag(null);
            fail("No exception was thrown");
        }
        catch (NullPointerException e)
        {
            assertTrue(true);
        }
    }
    
    @Test
    @DisplayName("Removes the specified tag from the Task and returns true")
    public void testRemoveTag1()
    {
        assertTrue(task.removeTag("red"));
        
        assertFalse(task.hasTag("red"));
    }
    
    @Test
    @DisplayName("Returns false if the specified tag is not in the Task")
    public void testRemoveTag2()
    {
        assertFalse(task.removeTag("banana"));
    }
    
    @Test
    @DisplayName("Returns false if the tag argument is null")
    public void testRemoveTag3()
    {
        assertFalse(task.removeTag(null));
    }
    
    @Test
    @DisplayName("Returns true if the Task contains the specified tag")
    public void testHasTag1()
    {
        assertTrue(task.hasTag("red"));
    }
    
    @Test
    @DisplayName("Returns false if the Task does not contain the specified tag")
    public void testHasTag2()
    {
        assertFalse(task.hasTag("banana"));
    }
    
    @Test
    @DisplayName("When the specified tag is \"untagged\", returns true if the Task has no tags")
    public void testHasTag3()
    {
        Task t = new Task(null, "01/01/2000", new LinkedList<String>());
        
        assertTrue(t.hasTag("untagged"));
    }
    
    @Test
    @DisplayName("When the specified tag is \"untagged\", returns false if the Task has at least one tag")
    public void testHasTag4()
    {
        assertFalse(task.hasTag("untagged"));
    }
    
    @Test
    @DisplayName("Throws a NullPointerException when the specified tag is null")
    public void testHasTag5()
    {
        try
        {
            task.hasTag(null);
            fail("No exception was thrown");
        }
        catch (NullPointerException e)
        {
            assertTrue(true);
        }
    }
    
    @Test
    @DisplayName("A Task equals itself")
    public void testEquals1()
    {
        assertTrue(task.equals(task));
    }
    
    @Test
    @DisplayName("A Task equals another Task object if both of their descriptions, due dates, and tags are the same")
    public void testEquals2()
    {
        Task t = new Task("Test task", "01/01/2001", new LinkedList<String>());
        t.addTag("red");
        t.addTag("orange");
        t.addTag("yellow");
        t.addTag("green");
        t.addTag("blue");
        t.addTag("violet");
        
        assertTrue(task.equals(t));
        assertFalse(task == t);
    }
    
    @Test
    @DisplayName("Resets the ID counter to 0")
    public void testReset()
    {
        Task.reset();
        
        Task t = new Task(null, "01/01/2000", null);
        
        assertTrue(t.ID == 0);
    }
}
