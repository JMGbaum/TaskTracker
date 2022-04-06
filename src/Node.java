
/**
 * A generic class to be used in a data structures for easily scalable, list-type storage.
 * Can hold any type of value so that the class can be easily reused and repurposed.
 * Nodes hold pointers to adjacent Nodes rather than being stored contiguously, so data
 * structures can be scaled and modified dynamically.
 *
 * @author Jordan Greenbaum
 * @version 25 April 2021
 */
public class Node<E>
{
    private E value; // the value to store in this Node
    private Node<E> next; // the next Node in the Linked List
    private Node<E> prev; // the previous Node in the Linked List

    /**
     * Construct a Node object that contains the specified value.
     * 
     * @param value the value to store in this Node
     */
    public Node(E value)
    {
        // initialize instance variables
        this.value = value;
        this.next = null;
        this.prev = null;
    }
    
    /**
     * Construct a Node object that contains the specified value.
     * 
     * @param value the value to store in this Node
     * @param next the next node in the Linked List
     */
    public Node(E value, Node<E> next, Node<E> prev)
    {
        // initialise instance variables
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
    
    /**
     * Gets the value stored in this Node.
     * 
     * @return the value stored in this Node
     */
    public E getValue()
    {
        return value;
    }
    
    /**
     * Stores the specified value in this Node.
     * 
     * @param E value the value to store in this Node
     */
    public void setValue(E value)
    {
        this.value = value;
    }
    
    /**
     * Gets the next Node in the Linked List.
     * 
     * @return the next Node in the Linked List
     */
    public Node<E> getNext()
    {
        return next;
    }
    
    /**
     * Sets the Node that follows this one in the Linked List.
     * 
     * @param next the next Node in the Linked List
     */
    public void setNext(Node<E> next)
    {
        this.next = next;
    }
    
    /**
     * Gets the previous Node in the Linked List.
     * 
     * @return the previous Node in the Linked List
     */
    public Node<E> getPrevious()
    {
        return prev;
    }
    
    /**
     * Sets the Node that precedes this one in the Linked List.
     * 
     * @param prev the previous Node in the Linked List
     */
    public void setPrevious(Node<E> prev)
    {
        this.prev = prev;
    }
}
