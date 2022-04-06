import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.Iterator;

/**
 * The test class LinkedListTest.
 *
 * @author  Jordan Greenbaum
 * @version 25 April 2021
 */
public class LinkedListTest
{
    LinkedList<Integer> list;
    LinkedList<String> strlist;
    
    /**
     * Default constructor for test class LinkedListTest
     */
    public LinkedListTest()
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
        list = new LinkedList<Integer>();
        strlist = new LinkedList<String>();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        list = null;
        strlist = null;
    }
    
    @Test
    @DisplayName("Size of a list")
    public void testSize1()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(3, list.size());
    }
    
    @Test
    @DisplayName("Size of an empty list")
    public void testSize2()
    {
        // run test
        assertEquals(0, list.size());
    }
    
    @Test
    @DisplayName("List is not empty when there are elements in it")
    public void testIsEmpty1()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertFalse(list.isEmpty());
    }
    
    @Test
    @DisplayName("List is empty when there are no elements in it")
    public void testIsEmpty2()
    {
        // run test
        assertTrue(list.isEmpty());
    }
    
    @Test
    @DisplayName("List recognizes regular values")
    public void testContains1()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
    }
    
    @Test
    @DisplayName("List recognizes 0 values")
    public void testContains2()
    {
        // add values
        list.addLast(0);
        
        // run test
        assertTrue(list.contains(0));
    }
    
    @Test
    @DisplayName("List recognizes negative values")
    public void testContains3()
    {
        // add values
        list.addLast(-1);
        list.addLast(-2);
        list.addLast(-3);
        
        // run test
        assertTrue(list.contains(-1));
        assertTrue(list.contains(-2));
        assertTrue(list.contains(-3));
    }
    
    @Test
    @DisplayName("List recognizes null values")
    public void testContains4()
    {
        // add values
        list.addLast(null);
        
        // run test
        assertTrue(list.contains(null));
    }
    
    @Test
    @DisplayName("List recognizes which values it does NOT contain")
    public void testContains5()
    {
        // add values
        list.addLast(-1);
        list.addLast(1);
        
        // run test
        assertFalse(list.contains(-2));
        assertFalse(list.contains(0));
        assertFalse(list.contains(2));
    }
    
    @Test
    @DisplayName("Positive values can be added to the end of the list")
    public void testAddLast1()
    {
        // add values to list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals("[1,2,3]", list.toString());
    }
    
    @Test
    @DisplayName("0 values can be added to the end of the list")
    public void testAddLast2()
    {
        // add values to list
        list.addLast(0);
        list.addLast(1);
        list.addLast(0);
        list.addLast(1);
        list.addLast(0);
        
        // run test
        assertEquals("[0,1,0,1,0]", list.toString());
    }
    
    @Test
    @DisplayName("Negative values can be added to the end of the list")
    public void testAddLast3()
    {
        // add values to list
        list.addLast(-1);
        list.addLast(-2);
        list.addLast(-3);
        
        // run test
        assertEquals("[-1,-2,-3]", list.toString());
    }
    
    @Test
    @DisplayName("Null values can be added to the end of the list")
    public void testAddLast4()
    {
        // add values to list
        list.addLast(null);
        
        // run test
        assertEquals("[null]", list.toString());
    }
    
    @Test
    @DisplayName("Positive values can be added to the front of the list")
    public void testAddFirst1()
    {
        // add values to list
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        
        // run test
        assertEquals("[3,2,1]", list.toString());
    }
    
    @Test
    @DisplayName("0 values can be added to the front of the list")
    public void testAddFirst2()
    {
        // add values to list
        list.addFirst(0);
        list.addFirst(1);
        list.addFirst(0);
        list.addFirst(1);
        list.addFirst(0);
        
        // run test
        assertEquals("[0,1,0,1,0]", list.toString());
    }
    
    @Test
    @DisplayName("Negative values can be added to the front of the list")
    public void testAddFirst3()
    {
        // add values to list
        list.addFirst(-1);
        list.addFirst(-2);
        list.addFirst(-3);
        
        // run test
        assertEquals("[-3,-2,-1]", list.toString());
    }
    
    @Test
    @DisplayName("Null values can be added to the front of the list")
    public void testAddFirst4()
    {
        // add values to list
        list.addFirst(1);
        list.addFirst(null);
        
        // run test
        assertEquals("[null,1]", list.toString());
    }
    
    @Test
    @DisplayName("The value to remove can be recognized in the first node")
    public void testRemove1()
    {
        // add values to list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertTrue(list.remove((Integer) 1), "The value was not found in the list");
        assertEquals("[2,3]", list.toString());
    }
    
    @Test
    @DisplayName("The value to remove can be recognized in the middle of the list")
    public void testRemove2()
    {
        // add values to list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertTrue(list.remove((Integer) 2), "The value was not found in the list");
        assertEquals("[1,3]", list.toString());
    }
    
    @Test
    @DisplayName("The value to remove can be recognized in the last node")
    public void testRemove3()
    {
        // add values to list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertTrue(list.remove((Integer) 3), "The value was not found in the list");
        assertEquals("[1,2]", list.toString());
    }
    
    @Test
    @DisplayName("Only the first instance of the value is removed when there are duplicates")
    public void testRemove4()
    {
        // add values to list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertTrue(list.remove((Integer) 2), "The value was not found in the list");
        assertEquals("[1,3,1,2,3]", list.toString());
    }
    
    @Test
    @DisplayName("Null values can be removed")
    public void testRemove5()
    {
        // add values to list
        list.addLast(null);
        
        // run test
        assertTrue(list.remove(null), "The value was not found in the list");
        assertEquals("[]", list.toString(), "The value was not removed from the list");
    }
    
    @Test
    @DisplayName("The method returns false when the specified value is not found")
    public void testRemove6()
    {
        // run test
        assertFalse(list.remove(null), "The value was not found in the list");
    }
    
    @Test
    @DisplayName("A list can be cleared")
    public void testClear1()
    {
        // add values to list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // clear them
        list.clear();
        
        // run test
        assertEquals("[]", list.toString());
    }
    
    @Test
    @DisplayName("Clearing an empty list does not cause an error")
    public void testClear2()
    {
        // clear the empty list
        list.clear();
        
        // run test
        assertEquals("[]", list.toString());
    }
    
    @Test
    @DisplayName("Lists with the same values but different references are considered equal")
    public void testEquals1()
    {
        LinkedList list2 = new LinkedList<Integer>();
        
        // add values to original list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // add values to new list
        list2.addLast(1);
        list2.addLast(2);
        list2.addLast(3);
        
        // run test
        assertTrue(list.equals(list2));
    }
    
    @Test
    @DisplayName("Lists with the same values but different lengths are considered unequal")
    public void testEquals2()
    {
        LinkedList list2 = new LinkedList<Integer>();
        LinkedList list3 = new LinkedList<Integer>();
        
        // add values to original list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // add values to new lists
        list2.addLast(1);
        list2.addLast(2);
        list2.addLast(3);
        list2.addLast(4);
        
        list3.addLast(1);
        list3.addLast(2);
        
        // run test
        assertFalse(list.equals(list2));
        assertFalse(list.equals(list3));
    }
    
    @Test
    @DisplayName("Lists with different values are considered unequal")
    public void testEquals3()
    {
        LinkedList list2 = new LinkedList<Integer>();
        
        // add values to original list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // add values to new list
        list2.addLast(4);
        list2.addLast(5);
        list2.addLast(6);
        
        // run test
        assertFalse(list.equals(list2));
    }
    
    @Test
    @DisplayName("Two empty lists are considered equal")
    public void testEquals4()
    {
        LinkedList list2 = new LinkedList<Integer>();
        
        // run test
        assertTrue(list.equals(list2));
    }
    
    @Test
    @DisplayName("Lists with null values are considered equal")
    public void testEquals5()
    {
        LinkedList list2 = new LinkedList<Integer>();
        
        // add values to original list
        list.addLast(1);
        list.addLast(null);
        list.addLast(2);
        list.addLast(null);
        list.addLast(3);
        
        // add values to new list
        list2.addLast(1);
        list2.addLast(null);
        list2.addLast(2);
        list2.addLast(null);
        list2.addLast(3);
        
        // run test
        assertTrue(list.equals(list2));
    }
    
    @Test
    @DisplayName("Getting the first value in a list")
    public void testGet1()
    {
        // add values to the list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(1, list.get(0));
    }
    
    @Test
    @DisplayName("Getting a value from the middle of a list")
    public void testGet2()
    {
        // add values to the list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(2, list.get(1));
    }
    
    @Test
    @DisplayName("Getting the last value in a list")
    public void testGet3()
    {
        // add values to the list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(3, list.get(2));
    }
    
    @Test
    @DisplayName("Getting a negative value")
    public void testGet4()
    {
        // add values to the list
        list.addLast(-1);
        
        // run test
        assertEquals(-1, list.get(0));
    }
    
    @Test
    @DisplayName("Getting a 0 value")
    public void testGet5()
    {
        // add values to the list
        list.addLast(0);
        
        // run test
        assertEquals(0, list.get(0));
    }
    
    @Test
    @DisplayName("Getting a null value")
    public void testGet6()
    {
        // add values to the list
        list.addLast(null);
        
        // run test
        assertEquals(null, list.get(0));
    }
    
    @Test
    @DisplayName("Getting an out-of-bounds value results in a thrown IndexOutOfBoundsException")
    public void testGet7()
    {
        // run test
        try {
            list.get(0);
            fail("No exception was thrown");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true, "An IndexOutOfBoundsException was thrown");
        }
    }
    
    @Test
    @DisplayName("Setting the first value in a list")
    public void testSet1()
    {
        // add values to the list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(1, list.set(0, 4));
        assertEquals("[4,2,3]", list.toString());
    }
    
    @Test
    @DisplayName("Setting a value in the middle of a list")
    public void testSet2()
    {
        // add values to the list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(2, list.set(1, 4));
        assertEquals("[1,4,3]", list.toString());
    }
    
    @Test
    @DisplayName("Setting the last value in a list")
    public void testSet3()
    {
        // add values to the list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(3, list.set(2, 4));
        assertEquals("[1,2,4]", list.toString());
    }
    
    @Test
    @DisplayName("Setting to a negative value")
    public void testSet4()
    {
        // add values to the list
        list.addLast(0);
        
        // run test
        assertEquals(0, list.set(0, -1));
        assertEquals("[-1]", list.toString());
    }
    
    @Test
    @DisplayName("Setting to a 0 value")
    public void testSet5()
    {
        // add values to the list
        list.addLast(1);
        
        // run test
        assertEquals(1, list.set(0, 0));
        assertEquals("[0]", list.toString());
    }
    
    @Test
    @DisplayName("Setting to a null value")
    public void testSet6()
    {
        // add values to the list
        list.addLast(1);
        
        // run test
        assertEquals(1, list.set(0, null));
        assertEquals("[null]", list.toString());
    }
    
    @Test
    @DisplayName("Setting an out-of-bounds value results in a thrown IndexOutOfBoundsException")
    public void testSet7()
    {
        // run test
        try {
            list.set(0, 0);
            fail("No exception was thrown");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true, "An IndexOutOfBoundsException was thrown");
        }
    }
    
    @Test
    @DisplayName("Inserting at the beginning of a list")
    public void testInsert1()
    {
        // add values to the list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        list.insert(0, 4);
        assertEquals("[4,1,2,3]", list.toString());
    }
    
    @Test
    @DisplayName("Inserting in the middle of a list")
    public void testInsert2()
    {
        // add values to the list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        list.insert(1, 4);
        assertEquals("[1,4,2,3]", list.toString());
    }
    
    @Test
    @DisplayName("Inserting at the end of a list")
    public void testInsert3()
    {
        // add values to the list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        list.insert(2, 4);
        assertEquals("[1,2,4,3]", list.toString());
    }
    
    @Test
    @DisplayName("Inserting after the end of a list")
    public void testInsert4()
    {
        // add values to the list
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        list.insert(3, 4);
        assertEquals("[1,2,3,4]", list.toString());
    }
    
    @Test
    @DisplayName("Inserting a negative value")
    public void testInsert5()
    {
        // run test
        list.insert(0, -1);
        assertEquals("[-1]", list.toString());
    }
    
    @Test
    @DisplayName("Inserting a 0 value")
    public void testInsert6()
    {
        // run test
        list.insert(0, 0);
        assertEquals("[0]", list.toString());
    }
    
    @Test
    @DisplayName("Inserting a null value")
    public void testInsert7()
    {
        // run test
        list.insert(0, null);
        assertEquals("[null]", list.toString());
    }
    
    @Test
    @DisplayName("Inserting out-of-bounds (not including immediately after the end of the list) results in a thrown IndexOutOfBoundsException")
    public void testInsert8()
    {
        // run test
        try {
            list.insert(1, 0);
            fail("No exception was thrown");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true, "A IndexOutOfBoundsException was thrown");
        }
    }
    
    @Test
    @DisplayName("Removing the first node in a list")
    public void testRemoveIndex1()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(1, list.remove(0));
        assertEquals("[2,3]", list.toString());
    }
    
    @Test
    @DisplayName("Removing a node from the middle of a list")
    public void testRemoveIndex2()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(2, list.remove(1));
        assertEquals("[1,3]", list.toString());
    }
    
    @Test
    @DisplayName("Removing the last node in a list")
    public void testRemoveIndex3()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(3, list.remove(2));
        assertEquals("[1,2]", list.toString());
    }
    
    @Test
    @DisplayName("Removing an out-of-bounds node from a list will throw a IndexOutOfBoundsException")
    public void testRemoveIndex4()
    {
        // run test
        try {
            list.remove(0);
            fail("No exception was thrown");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true, "A IndexOutOfBoundsException was thrown");
        }
    }
    
    @Test
    @DisplayName("Finding the index of a positive number")
    public void testIndexOf1()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(0, list.indexOf(1));
        assertEquals(1, list.indexOf(2));
        assertEquals(2, list.indexOf(3));
    }
    
    @Test
    @DisplayName("Finding the index of a negative number")
    public void testIndexOf2()
    {
        // add values
        list.addLast(-1);
        list.addLast(-2);
        list.addLast(-3);
        
        // run test
        assertEquals(0, list.indexOf(-1));
        assertEquals(1, list.indexOf(-2));
        assertEquals(2, list.indexOf(-3));
    }
    
    @Test
    @DisplayName("Finding the index of a 0 value")
    public void testIndexOf3()
    {
        // add values
        list.addLast(-1);
        list.addLast(0);
        list.addLast(1);
        
        // run test
        assertEquals(1, list.indexOf(0));
    }
    
    @Test
    @DisplayName("Finding the index of a null value")
    public void testIndexOf4()
    {
        // add values
        list.addLast(-1);
        list.addLast(null);
        list.addLast(1);
        
        // run test
        assertEquals(1, list.indexOf(null));
    }
    
    @Test
    @DisplayName("Finding the index of a duplicate value returns the first index")
    public void testIndexOf5()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(0, list.indexOf(1));
        assertEquals(1, list.indexOf(2));
        assertEquals(2, list.indexOf(3));
    }
    
    @Test
    @DisplayName("Finding the index of a value that does not exist returns -1")
    public void testIndexOf6()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(-1, list.indexOf(4));
    }
    
    @Test
    @DisplayName("Finding the last index of a positive number")
    public void testLastIndexOf1()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(0, list.lastIndexOf(1));
        assertEquals(1, list.lastIndexOf(2));
        assertEquals(2, list.lastIndexOf(3));
    }
    
    @Test
    @DisplayName("Finding the last index of a negative number")
    public void testLastIndexOf2()
    {
        // add values
        list.addLast(-1);
        list.addLast(-2);
        list.addLast(-3);
        
        // run test
        assertEquals(0, list.lastIndexOf(-1));
        assertEquals(1, list.lastIndexOf(-2));
        assertEquals(2, list.lastIndexOf(-3));
    }
    
    @Test
    @DisplayName("Finding the last index of a 0 value")
    public void testLastIndexOf3()
    {
        // add values
        list.addLast(-1);
        list.addLast(0);
        list.addLast(1);
        
        // run test
        assertEquals(1, list.lastIndexOf(0));
    }
    
    @Test
    @DisplayName("Finding the last index of a null value")
    public void testLastIndexOf4()
    {
        // add values
        list.addLast(-1);
        list.addLast(null);
        list.addLast(1);
        
        // run test
        assertEquals(1, list.lastIndexOf(null));
    }
    
    @Test
    @DisplayName("Finding the last index of a duplicate value returns the last index")
    public void testLastIndexOf5()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(3, list.lastIndexOf(1));
        assertEquals(4, list.lastIndexOf(2));
        assertEquals(5, list.lastIndexOf(3));
    }
    
    @Test
    @DisplayName("Finding the last index of a value that does not exist")
    public void testLastIndexOf6()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run test
        assertEquals(-1, list.lastIndexOf(4));
    }
    
    @Test
    @DisplayName("Creating a sublist that starts with the first node and ends in the middle")
    public void testSubList1()
    {
        // create list to compare to
        LinkedList sublist = new LinkedList<Integer>();
        
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        
        // add values to sublist
        sublist.addLast(1);
        sublist.addLast(2);
        sublist.addLast(3);
        
        // run test
        assertTrue(list.subList(0,3).equals(sublist));
    }
    
    @Test
    @DisplayName("Creating a sublist that starts in the middle and ends in the middle")
    public void testSubList2()
    {
        // create list to compare to
        LinkedList sublist = new LinkedList<Integer>();
        
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        
        // add values to sublist
        sublist.addLast(2);
        sublist.addLast(3);
        
        // run test
        assertTrue(list.subList(1,3).equals(sublist));
    }
    
    @Test
    @DisplayName("Creating a sublist that includes all nodes except the last node")
    public void testSubList3()
    {
        // create list to compare to
        LinkedList sublist = new LinkedList<Integer>();
        
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        
        // add values to sublist
        sublist.addLast(1);
        sublist.addLast(2);
        sublist.addLast(3);
        sublist.addLast(4);
        
        // run test
        assertTrue(list.subList(0,4).equals(sublist));
    }
    
    @Test
    @DisplayName("Creating a sublist that includes all nodes")
    public void testSubList4()
    {
        // create list to compare to
        LinkedList sublist = new LinkedList<Integer>();
        
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        
        // add values to sublist
        sublist.addLast(1);
        sublist.addLast(2);
        sublist.addLast(3);
        sublist.addLast(4);
        sublist.addLast(5);
        
        // run test
        assertTrue(list.subList(0,5).equals(sublist));
    }
    
    @Test
    @DisplayName("Creating a sublist that has only one node")
    public void testSubList5()
    {
        // create list to compare to
        LinkedList sublist = new LinkedList<Integer>();
        
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        
        // add values to sublist
        sublist.addLast(1);
        
        // run test
        assertTrue(list.subList(0,1).equals(sublist));
    }
    
    @Test
    @DisplayName("Creating a sublist with the same start index and end index")
    public void testSubList6()
    {
        // create list to compare to
        LinkedList sublist = new LinkedList<Integer>();
        
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        
        // run test
        assertTrue(list.subList(3,3).equals(sublist));
    }
    
    @Test
    @DisplayName("Creating a sublist where end index < start index")
    public void testSubList7()
    {
        // create list to compare to
        LinkedList sublist = new LinkedList<Integer>();
        
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        
        // run test
        try {
            list.subList(3,1);
            fail("No exception was thrown");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true, "An IndexOutOfBoundsException was thrown");
        }
    }
    
    @Test
    @DisplayName("Creating a sublist where the end index is out of bounds")
    public void testSubList8()
    {
        // create list to compare to
        LinkedList sublist = new LinkedList<Integer>();
        
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        
        // run test
        try {
            list.subList(0,6);
            fail("No exception was thrown");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true, "An IndexOutOfBoundsException was thrown");
        }
    }
    
    @Test
    @DisplayName("Creating a sublist where the start index is out of bounds")
    public void testSubList9()
    {
        // create list to compare to
        LinkedList sublist = new LinkedList<Integer>();
        
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        
        // run test
        try {
            list.subList(5,6);
            fail("No exception was thrown");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true, "An IndexOutOfBoundsException was thrown");
        }
    }
    
    @Test
    @DisplayName("Turning a list into a String")
    public void testToString1()
    {
        // Add values
        list.addLast(-2);
        list.addLast(-1);
        list.addLast(0);
        list.addLast(1);
        list.addLast(2);
        
        // Test that the String is formatted correctly
        assertEquals("[-2,-1,0,1,2]", list.toString());
    }
    
    @Test
    @DisplayName("Turning an empty list into a String")
    public void testToString2()
    {
        // Test that the String is formatted correctly
        assertEquals("[]", list.toString());
    }
    
    @Test
    @DisplayName("Turning a list with null values into a String")
    public void testToString3()
    {
        // Add values
        list.addLast(null);
        list.addLast(1);
        list.addLast(null);
        list.addLast(2);
        list.addLast(null);
        list.addLast(3);
        list.addLast(null);
        
        // Test that the String is formatted correctly
        assertEquals("[null,1,null,2,null,3,null]", list.toString());
    }
    
    @Test
    @DisplayName("Removing duplicate numeric values")
    public void testRemoveDuplicates1()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run tests
        list.removeDuplicates();
        assertEquals("[1,2,3,4,5]", list.toString());
    }
    
    @Test
    @DisplayName("Removing duplicate strings")
    public void testRemoveDuplicates2()
    {
        // add values
        strlist.addLast("a");
        strlist.addLast("b");
        strlist.addLast("c");
        strlist.addLast("d");
        strlist.addLast("e");
        strlist.addLast("a");
        strlist.addLast("b");
        strlist.addLast("c");
        strlist.addLast("a");
        strlist.addLast("b");
        strlist.addLast("c");
        
        // run tests
        strlist.removeDuplicates();
        assertEquals("[a,b,c,d,e]", strlist.toString());
    }
    
    @Test
    @DisplayName("Removing duplicate null values")
    public void testRemoveDuplicates3()
    {
        // add values
        list.addLast(null);
        list.addLast(null);
        list.addLast(null);
        list.addLast(null);
        list.addLast(null);
        
        // run tests
        list.removeDuplicates();
        assertEquals("[null]", list.toString());
    }
    
    @Test
    @DisplayName("Duplicating a list with a single element")
    public void testClone1()
    {
        // add values
        list.addLast(1);
        
        // run tests
        LinkedList<Integer> list2 = list.clone();
        assertEquals("[1]", list2.toString());
        assertFalse(list == list2);
        assertTrue(list.equals(list2));
    }
    
    @Test
    @DisplayName("Duplicating a list with multiple elements")
    public void testClone2()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run tests
        LinkedList<Integer> list2 = list.clone();
        assertEquals("[1,2,3]", list2.toString());
        assertFalse(list == list2);
        assertTrue(list.equals(list2));
    }
    
    @Test
    @DisplayName("Duplicating an empty list")
    public void testClone3()
    {
        // run tests
        LinkedList<Integer> list2 = list.clone();
        assertEquals("[]", list2.toString());
        assertFalse(list == list2);
        assertTrue(list.equals(list2));
    }
    
    @Test
    @DisplayName("Modifying a duplicate list DOES NOT modify the original list")
    public void testClone4()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // clone the list
        LinkedList<Integer> list2 = list.clone();
        
        // modify the duplicate
        list2.remove(0);
        list2.remove(1);
        list2.addLast(6);
        list2.insert(1, 3);
        list2.set(1, 4);
        
        // run tests
        assertEquals("[1,2,3]", list.toString());
        assertEquals("[2,4,6]", list2.toString());
    }
    
    @Test
    @DisplayName("Modifying the object stored inside an element in a duplicate list DOES modify the corresponding object in the original list")
    public void testClone5()
    {
        LinkedList<int[]> listA = new LinkedList<int[]>();
        
        int[] arr1 = {1,2,3};
        int[] arr2 = {4,5,6};
        int[] arr3 = {7,8,9};
        
        // add values
        listA.addLast(arr1);
        listA.addLast(arr2);
        listA.addLast(arr3);
        
        // clone the list
        LinkedList<int[]> listB = listA.clone();
        
        // modify the duplicate
        listB.get(0)[0] = 0;
        listB.get(0)[2] = 4;
        listB.get(1)[0] = 3;
        listB.get(1)[2] = 7;
        listB.get(2)[0] = 6;
        listB.get(2)[2] = 10;
        
        // run tests
        assertTrue(listA.equals(listB));
        assertEquals(listA.toString(), listB.toString());
        assertFalse(listA == listB);
    }
    
    @Test
    @DisplayName("List values can be joined with an empty String")
    public void testJoin1()
    {
        // add values
        strlist.addLast("val 1");
        strlist.addLast("val 2");
        strlist.addLast("val 3");
        
        assertEquals("val 1val 2val 3", strlist.join(""));
    }
    
    @Test
    @DisplayName("List values can be joined with a whitespace character String")
    public void testJoin2()
    {
        // add values
        strlist.addLast("val 1");
        strlist.addLast("val 2");
        strlist.addLast("val 3");
        
        assertEquals("val 1 val 2 val 3", strlist.join(" "));
    }
    
    @Test
    @DisplayName("List values can be joined with a sequence of characters String")
    public void testJoin3()
    {
        // add values
        strlist.addLast("val 1");
        strlist.addLast("val 2");
        strlist.addLast("val 3");
        
        assertEquals("val 1, abc, val 2, abc, val 3", strlist.join(", abc, "));
    }
    
    @Test
    @DisplayName("Joining a list using null places \"null\" between each value")
    public void testJoin4()
    {
        // add values
        strlist.addLast("val 1");
        strlist.addLast("val 2");
        strlist.addLast("val 3");
        
        assertEquals("val 1nullval 2nullval 3", strlist.join(null));
    }
    
    @Test
    @DisplayName("Joining an empty list returns an empty String")
    public void testJoin5()
    {
        assertEquals("", strlist.join(""));
    }
    
    @Test
    @DisplayName("Joining a list with one value returns the value as a String")
    public void testJoin6()
    {
        // add values
        strlist.addLast("val 1");
        
        assertEquals("val 1", strlist.join(""));
    }
    
    @Test
    @DisplayName("Lists with data types other than Strings can be joined")
    public void testJoin7()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        assertEquals("1,2,3", list.join(","));
    }
    
    @Test
    @DisplayName("Lists can be iterated in a foreach loop")
    public void testIterator1()
    {
        int i = 0; // current index
        
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // run tests
        for (int current : list)
        {
            assertEquals(current, list.get(i));
            i++;
        }
    }
    
    @Test
    @DisplayName("Elements can be removed using an Iterator")
    public void testIterator2()
    {
        // add values
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        
        // iterate
        Iterator<Integer> listIterator = list.iterator();
        int count = 0;
        
        while (listIterator.hasNext())
        {
            int value = listIterator.next();
            count++;
            
            if (value % 2 == 0)
            {
                listIterator.remove();
            }
        }
        
        // run tests
        assertEquals(3, count);
        assertEquals("[1,3]", list.toString());
    }
    
    @Test
    @DisplayName("Using other data types")
    public void testGeneric1()
    {
        // add values
        strlist.addLast("A");
        strlist.addLast("B");
        strlist.addLast("C");
        strlist.addLast("D");
        strlist.addLast("E");
        
        // remove values
        assertEquals("C", strlist.remove(2), "Removing Strings is not working correctly");
        assertTrue(strlist.remove("E"));
        
        // contains
        assertTrue(strlist.contains("A"));
        assertFalse(strlist.contains("C"));
        
        // get values
        assertEquals("A", strlist.get(0));
        
        // indexOf
        assertEquals(0, strlist.indexOf("A"));
        
        // set values
        assertEquals("D", strlist.set(2, "F"));
        
        // size
        assertEquals(3, strlist.size());
        
        // toString
        assertEquals("[A,B,F]", strlist.toString());
    }
}
