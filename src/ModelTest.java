

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.io.*;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * The test class ModelTest.
 *
 * @author  Jordan Greenbaum
 * @version 25 April 2021
 */
public class ModelTest
{
    /**
     * Default constructor for test class ModelTest
     */
    public ModelTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        String[] tags1 = {"tag-1", "tag-2"};
        String[] tags2 = {"tag-1", "tag-2", "tag-3"};
        String[] tags3 = {"tag-2", "tag-3", "tag-4"};
        String[] tags4 = {"tag-3", "tag-4", "tag-5"};
        String[] tags5 = {"tag-4", "tag-5"};
        
        Model.addTag("tag-1");
        Model.addTag("tag-2");
        Model.addTag("tag-3");
        Model.addTag("tag-4");
        Model.addTag("tag-5");
        
        Model.addTask("Task 1.", "01/01/2001", new LinkedList<String>(tags1));
        Model.addTask("Task 2.", "02/02/2002", new LinkedList<String>(tags2));
        Model.addTask("Task 3.", "03/03/2003", new LinkedList<String>(tags3));
        Model.addTask("Task 4.", "04/04/2004", new LinkedList<String>(tags4));
        Model.addTask("Task 5.", "05/05/2005", new LinkedList<String>(tags5));
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        Model.reset();
    }
    
    @Test
    @DisplayName("Getting a Task with a specified ID")
    public void testGetTask1()
    {
        assertTrue(Model.getTask(0).getDescription().equals("Task 1."));
    }
    
    @Test
    @DisplayName("Returns null if no tag with the specified ID exists in the system")
    public void testGetTask2()
    {
        assertTrue(Model.getTask(-1) == null);
    }
    
    @Test
    @DisplayName("Adding a valid tag to the system")
    public void testAddTag1()
    {
        Model.addTag("a");
        
        assertTrue(Model.tagExists("a"));
    }
    
    @Test
    @DisplayName("Adding a tag that already exists does not throw an exception")
    public void testAddTag2()
    {
        assertTrue(Model.tagExists("tag-1"));
        
        Model.addTag("tag-1");
        
        assertTrue(Model.tagExists("tag-1"));
    }
    
    @Test
    @DisplayName("Adding an invalid tag does not throw an exception but also does not add the tag to the system")
    public void testAddTag3()
    {
        Model.addTag("1234");
        
        assertFalse(Model.tagExists("1234"));
    }
    
    @Test
    @DisplayName("Deleting a tag from the system")
    public void testDeleteTag1()
    {
        assertTrue(Model.tagExists("tag-1"));
        
        Model.deleteTag("tag-1");
        
        assertFalse(Model.tagExists("tag-1"));
    }
    
    @Test
    @DisplayName("Deleting a tag that does not exist does not throw an exception")
    public void testDeleteTag2()
    {
        assertFalse(Model.tagExists("abc"));
        
        Model.deleteTag("abc");
        
        assertTrue(true);
    }
    
    @Test
    @DisplayName("Tags are case-insensitive")
    public void testDeleteTag3()
    {
        assertTrue(Model.tagExists("tag-1"));
        
        Model.deleteTag("TAG-1");
        
        assertFalse(Model.tagExists("tag-1"));
    }
    
    @Test
    @DisplayName("Deleting a tag from the system removes it from all Tasks that have it")
    public void testDeleteTag4()
    {
        assertTrue(Model.tagExists("tag-1"));
        
        Model.deleteTag("tag-1");
        
        assertEquals(0, Model.searchTags("tag-1").size());
    }
    
    @Test
    @DisplayName("Adds a new Task to the system")
    public void testAddTask1()
    {
        String[] tags = {"tag-1", "tag-2", "tag-3", "tag-4", "tag-5"};
        
        Model.addTask("My birthday.", "02/15/2001", new LinkedList<String>(tags));
        
        assertEquals(1, Model.searchTagsDates("AND tag-1 tag-2 tag-3 tag-4 tag-5 02/15/2001").size());
    }
    
    @Test
    @DisplayName("Adds a new Task to the system with no tags")
    public void testAddTask2()
    {
        Model.addTask("My birthday.", "02/15/2001", new LinkedList<String>());
        
        assertEquals(1, Model.searchTagsDates("02/15/2001").size());
    }
    
    @Test
    @DisplayName("Adding duplicate Tasks does not throw an exception")
    public void testAddTask3()
    {
        String[] tags = {"tag-1", "tag-2", "tag-3", "tag-4", "tag-5"};
        
        Model.addTask("My birthday.", "02/15/2001", new LinkedList<String>(tags));
        Model.addTask("My birthday.", "02/15/2001", new LinkedList<String>(tags));
        
        assertEquals(2, Model.searchTagsDates("AND tag-1 tag-2 tag-3 tag-4 tag-5 02/15/2001").size());
    }
    
    @Test
    @DisplayName("Adding a new Task with an unparseable date sets the date to null")
    public void testAddTask4()
    {
        Model.addTask("My birthday.", "00/00/0000", new LinkedList<String>());
        assertEquals(null, Model.searchText("My birthday.").get(0).getDate());
    }
    
    @Test
    @DisplayName("Searches Tasks for those with a single tag")
    public void testSearchTags1()
    {
        assertEquals(3, Model.searchTags("tag-2").size());
    }
    
    @Test
    @DisplayName("Searches Tasks for those with more than one tag using an AND operator")
    public void testSearchTags2()
    {
        assertEquals(1, Model.searchTags("AND tag-1 tag-2 tag-3").size());
    }
    
    @Test
    @DisplayName("Searches Tasks for those with at least one of two tags using an OR operator")
    public void testSearchTags3()
    {
        assertEquals(5, Model.searchTags("OR tag-2 tag-4").size());
    }
    
    @Test
    @DisplayName("Tag searches work with complex logic and operator precedence")
    public void testSearchTags4()
    {
        assertEquals(2, Model.searchTags("AND tag-3 (OR tag-1 tag-5)").size());
    }
    
    @Test
    @DisplayName("Tag searches are case-insensitive")
    public void testSearchTags5()
    {
        assertEquals(3, Model.searchTags("TAG-2").size());
    }
    
    @Test
    @DisplayName("Searches Tasks for those with a specific due date")
    public void testSearchDates1()
    {
        assertEquals(1, Model.searchDates("01/01/2001").size());
    }
    
    @Test
    @DisplayName("Searches Tasks for those that are due within a range of dates")
    public void testSearchDates2()
    {
        assertEquals(2, Model.searchDates("01/01/2001--12/31/2002").size());
    }
    
    @Test
    @DisplayName("Searches Tasks for those with at least one of two due dates using an OR operator")
    public void testSearchDates3()
    {
        assertEquals(2, Model.searchDates("OR 01/01/2001 02/02/2002").size());
    }
    
    @Test
    @DisplayName("Searches Tasks for those that are due within a specified range of dates or has a specific due date using an OR operator")
    public void testSearchDates4()
    {
        assertEquals(3, Model.searchDates("OR 01/01/2001--12/31/2002 03/03/2003").size());
    }
    
    @Test
    @DisplayName("Searches Tasks for those that are due within at least one of two date ranges using an OR operator")
    public void testSearchDates5()
    {
        assertEquals(4, Model.searchDates("OR 01/01/2001--12/31/2002 01/01/2004--12/31/2005").size());
    }
    
    @Test
    @DisplayName("Searches Tasks for those with a specified tag and due date")
    public void testSearchTagsDates1()
    {
        assertEquals(1, Model.searchTagsDates("tag-1 01/01/2001").size());
    }
    
    @Test
    @DisplayName("Searches Tasks for those with a specified tag and are due within a range of dates")
    public void testSearchTagsDates2()
    {
        assertEquals(2, Model.searchTagsDates("tag-1 01/01/2000--12/31/2002").size());
    }
    
    @Test
    @DisplayName("Searches Tasks using AND operators in the tag query")
    public void testSearchTagsDates3()
    {
        assertEquals(1, Model.searchTagsDates("(AND tag-1 tag-2) 01/01/2001").size());
    }
    
    @Test
    @DisplayName("Searches Tasks using OR operators in the tag query")
    public void testSearchTagsDates4()
    {
        assertEquals(1, Model.searchTagsDates("(OR tag-1 tag-2) 01/01/2001").size());
    }
    
    @Test
    @DisplayName("Searches Tasks using OR operators in the date query")
    public void testSearchTagsDates5()
    {
        assertEquals(2, Model.searchTagsDates("tag-2 (OR 01/01/2001 03/03/2003)").size());
    }
    
    @Test
    @DisplayName("Searches Tasks using logic operators to mix the date and tag queries")
    public void testSearchTagsDates6()
    {
        assertEquals(3, Model.searchTagsDates("OR tag-1 05/05/2005").size());
    }
    
    @Test
    @DisplayName("Searches Tasks using queries with complex logic")
    public void testSearchTagsDates7()
    {
        assertEquals(3, Model.searchTagsDates("OR (AND tag-1 01/01/2001) (AND tag-3 (OR tag-1 tag-5))").size());
    }
    
    @Test
    @DisplayName("Tag and date searches are case-insensitive")
    public void testSearchTagsDates8()
    {
        assertEquals(3, Model.searchTagsDates("or TAG-1 TAG-2").size());
    }
    
    @Test
    @DisplayName("Searches for a Task with the specified piece of text in its description")
    public void testSearchText1()
    {
        assertEquals(5, Model.searchText("Task").size());
    }
    
    @Test
    @DisplayName("Description searches are case-sensitive")
    public void testSearchText2()
    {
        assertEquals(0, Model.searchText("task").size());
    }
    
    @Test
    @DisplayName("Overwrites preexisting files")
    public void testSaveTasks1() throws IOException
    {
        File f = new File("test.csv");
        PrintWriter output = new PrintWriter(f);
        
        output.println("This should not show up.");
        
        output.close();
        
        // overwrite data
        Model.saveTasks(f);
        
        // check that data was overwritten
        Scanner input = new Scanner(f);
        assertFalse(input.nextLine().equals("This should not show up."));
        input.close();
    }
    
    @Test
    @DisplayName("Loads data from a file.")
    public void testLoadTasks1() throws IOException
    {
        File f = new File("test.csv");
        
        Model.reset();
        Model.loadTasks(f);
        
        assertEquals("Task 1.", Model.getTask(0).getDescription());
    }
    
    @Test
    @DisplayName("Overwrites existing data.")
    public void testLoadTasks2() throws IOException
    {
        File f = new File("test.csv");
        
        Model.addTask("Task -1.", "10/10/2010", new LinkedList<String>());
        assertEquals("Task -1.", Model.getTask(5).getDescription());
        Model.loadTasks(f);
        
        assertEquals("Task 1.", Model.getTask(0).getDescription());
        assertTrue(Model.getTask(5) == null);
    }
    
    @Test
    @DisplayName("Does not throw an exception if the specified file does not exist")
    public void testLoadTasks3() throws IOException
    {
        File f = new File("abcdefghijklmnopqrstuvwxyz.csv");
        
        try{
            Model.loadTasks(f);
            assertTrue(true);
        }
        catch(Exception e)
        {
            fail("An exception was thrown.");
        }
    }
    
    @Test
    @DisplayName("Parses dates in the form of MM/DD/YYYY")
    public void testParseDate1()
    {
        assertEquals(LocalDate.of(2000,1,1), Model.parseDate("01/01/2000"));
    }
    
    @Test
    @DisplayName("Parses dates in the form of M/DD/YYYY")
    public void testParseDate2()
    {
        assertEquals(LocalDate.of(2000,1,1), Model.parseDate("1/01/2000"));
    }
    
    @Test
    @DisplayName("Parses dates in the form of MM/D/YYYY")
    public void testParseDate3()
    {
        assertEquals(LocalDate.of(2000,1,1), Model.parseDate("01/1/2000"));
    }
    
    @Test
    @DisplayName("Parses dates in the form of M/D/YYYY")
    public void testParseDate4()
    {
        assertEquals(LocalDate.of(2000,1,1), Model.parseDate("1/1/2000"));
    }
    
    @Test
    @DisplayName("Returns null if the specified String is not parseable")
    public void testParseDate5()
    {
        assertTrue(Model.parseDate("abc") == null);
    }
    
    @Test
    @DisplayName("Clearing all data in the system")
    public void testReset1()
    {
        Model.reset();
        
        assertFalse(Model.hasData());
    }
    
    @Test
    @DisplayName("Removing a Task from the system")
    public void testRemoveTask1()
    {
        Model.removeTask(Model.getTask(0));
        
        assertTrue(Model.getTask(0) == null);
    }
    
    @Test
    @DisplayName("Does not throw any exceptions if the Task does not exist")
    public void testRemoveTask2()
    {
        try
        {
            Model.removeTask(new Task("This task does not exist in the system.","12/31/2000",new LinkedList<String>()));
            assertTrue(true);
        }
        catch (Exception e)
        {
            fail("An exception was thrown");
        }
    }
    
    @Test
    @DisplayName("Tags with whitespace characters are invalid")
    public void testTagIsValid1()
    {
        assertFalse(Model.tagIsValid("a b c"));
    }
    
    @Test
    @DisplayName("Tags with commas are invalid")
    public void testTagIsValid2()
    {
        assertFalse(Model.tagIsValid("a,b,c"));
    }
    
    @Test
    @DisplayName("Tags with parentheses are invalid")
    public void testTagIsValid3()
    {
        assertFalse(Model.tagIsValid("a(b)c"));
    }
    
    @Test
    @DisplayName("Tags that start with non-letter characters are invalid")
    public void testTagIsValid4()
    {
        assertFalse(Model.tagIsValid("1abc"));
        assertFalse(Model.tagIsValid(".abc"));
        assertFalse(Model.tagIsValid("$abc"));
        assertFalse(Model.tagIsValid("(abc"));
        assertFalse(Model.tagIsValid("-abc"));
        assertTrue(Model.tagIsValid("abc1"));
    }
    
    @Test
    @DisplayName("Tags cannot be 'and' nor 'or'")
    public void testTagIsValid5()
    {
        assertFalse(Model.tagIsValid("and"));
        assertFalse(Model.tagIsValid("or"));
    }
    
    @Test
    @DisplayName("Tags with punctuation other than commas and parentheses are valid")
    public void testTagIsValid6()
    {
        assertTrue(Model.tagIsValid("a.b.c"));
        assertTrue(Model.tagIsValid("a-b-c"));
        assertTrue(Model.tagIsValid("a/b/c"));
    }
    
    @Test
    @DisplayName("Checking if a tag exists")
    public void testTagExists1()
    {
        assertTrue(Model.tagExists("tag-1"));
    }
    
    @Test
    @DisplayName("Tags are case-insensitive")
    public void testTagExists2()
    {
        assertTrue(Model.tagExists("TAG-1"));
    }
    
    @Test
    @DisplayName("Returns false if the tag does not exist")
    public void testTagExists3()
    {
        assertFalse(Model.tagExists("abc"));
    }
    
    @Test
    @DisplayName("Returns true if there is at least one Task in the system")
    public void testHasData1()
    {
        Model.reset();
        Model.addTask("Task 1.", "01/01/2001", new LinkedList<String>());
        
        assertTrue(Model.hasData());
    }
    
    @Test
    @DisplayName("Returns true if there is at least one tag in the system")
    public void testHasData2()
    {
        Model.reset();
        Model.addTag("tag-1");
        
        assertTrue(Model.hasData());
    }
    
    @Test
    @DisplayName("Returns false if there are no Tasks and no tags in the system")
    public void testHasData3()
    {
        Model.reset();
        
        assertFalse(Model.hasData());
    }
}
