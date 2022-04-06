import java.util.Iterator;

/**
 * A generic class that provides dynamic, easily scalable, non-contiguous list-type
 * storage. Values are ordered but can be added, removed, modified, or reordered
 * anywhere in the structure at any time.
 *
 * @author Jordan Greenbaum
 * @version 25 April 2021
 */
public class LinkedList<E> implements Iterable<E>
{
    private Node<E> head; // The first Node in this list
    private Node<E> tail; // The last Node in this list
        
    /**
     * Constructs an empty LinkedList object.
     */
    public LinkedList()
    {
        this.head = null;
        this.tail = null;
    }
    
    /**
     * Constructs a LinkedList object with the specified elements.
     */
    public LinkedList(E[] elements)
    {
        for (E e : elements)
        {
            addLast(e);
        }
    }
    
    /**
     * Returns the size of this list.
     * 
     * @return the size of this list
     */
    public int size()
    {
        int size = 0; // the size of this list
        
        // count the number of Nodes currently in the list
        for (Node<E> current = head; current != null; current = current.getNext())
        {
            size++;
        }
        
        return size;
    }
    
    /**
     * Returns whether or not this list contains 0 nodes.
     * 
     * @return whether or not this list is empty
     */
    public boolean isEmpty()
    {
        return head == null;
    }
    
    /**
     * Returns whether or not this list contains the specified value.
     * 
     * @return whether or not this list contains the specified value
     */
    public boolean contains(E value)
    {
        // cycle through the list and search for a Node with a mathcing value
        for (Node<E> current = head; current != null; current = current.getNext())
        {
            // if the contents of the current Node is a match, return true
            if (equals(current, value))
            {
                return true;
            }
        }
        
        // if the end of the list was reached without a match, return false
        return false;
    }
    
    /**
     * Adds the specified value to the end of this list.
     * 
     * @param value the value to add to this list
     * @return true if the value was added successfully
     */
    public boolean addLast(E value)
    {
        // if the list is empty, create the head of the list
        if (isEmpty())
        {
            head = new Node<E> (value);
            tail = head;
            return true;
        }
        // otherwise, add the new node to the end of the list
        else
        {
            tail.setNext(new Node<E> (value, null, tail));
            tail = tail.getNext();
            return true;
        }
    }
    
    /**
     * Adds the specified value to the beginning of this list.
     * 
     * @param value the value to add to this list
     * @return true if the value was added successfully
     */
    public boolean addFirst(E value)
    {
        // if the list is empty, create the head of the list
        if (isEmpty())
        {
            head = new Node<E> (value);
            tail = head;
            return true;
        }
        // otherwise, add the new node to the beginning of the list
        else
        {
            head.setPrevious(new Node<E> (value, head, null));
            head = head.getPrevious();
            return true;
        }
    }
    
    /**
     * Removes the first node in this list that contains the specified value.
     * 
     * @param value the value to remove
     * @return whether or not the specified value was found in this list
     */
    public boolean remove(E value)
    {
       // If the list isn't empty, cycle through the nodes and remove the first match
       if (!isEmpty())
       {
           for (Node<E> current = head; current != null; current = current.getNext())
           {
                // if the value of the next node is a match, remove it
                if (equals(current, value))
                {
                    removeNode(current);
                    return true;
                }
           }
       }
       
       // if no match was found, return false
       return false;
    }
    
    /**
     * Removes all nodes from this list.
     */
    public void clear()
    {
        // remove the head of the list, thereby removing all nodes that follow
        this.head = null;
        this.tail = null;
    }
    
    /**
     * Compares the values and size of this list to those of the specified list.
     * 
     * @param compList the list to compare this one to
     * @returns whether or not the specified list has the same structure as this one
     */
    public boolean equals(LinkedList compList)
    {
        // make sure the lists are the same size
        if (size() != compList.size())
        {
            return false;
        }
        // otherwise, cycle through the nodes of both lists and make sure the values match
        else
        {
            int i = 0; // keep track of the current index
            
            // check pairs of nodes in the same position to make sure they match
            for (Node<E> current = head; current != null && i < compList.size(); current = current.getNext(), i++)
            {
                // if a pair of nodes doesn't match, return false
                if (!equals(current, (E) compList.get(i)))
                {
                    return false;
                }
            }
            
            // if the end of both lists were reached without a discrepancy, return true
            return true;
        }
    }
    
    /**
     * Returns the value at the specified position in this list.
     * 
     * @param index the position of the desired value
     * @return the value at the specified index in this list
     */
    public E get(int index)
    {
        Node<E> current = head; // to iterate through the nodes
        
        // if the index is out of bounds, throw an exception
        if (index < 0 || index >= size())
        {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size());
        }
        
        // cycle through the nodes until the desired index is reached
        for (int i = 0; i < index; i++)
        {
            current = current.getNext();
        }
        
        // return the value of the node at the specified index
        return current.getValue();
    }
    
    /**
     * Sets the node in this list at the specified position to the specified value.
     * 
     * @param index the position of the node to change
     * @param value the new value
     * @return the value previously stored in the specified node
     */
    public E set(int index, E value)
    {
        E tmp; // to hold the value previously stored in the specified node
        Node<E> current = head; // to iterate through the nodes
        
        // if the index is out of bounds, throw an exception
        if (index < 0 || index >= size())
        {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size());
        }
        
        // cycle through the nodes until the desired index is reached
        for (int i = 0; i < index; i++)
        {
            current = current.getNext();
        }
        
        // store the current value, then set the new value and return the stored value
        tmp = current.getValue();
        current.setValue(value);
        return tmp;
    }
    
    /**
     * Inserts a new node with the specified value at the specified index, increasing the
     * indices of all nodes that follow by one.
     * 
     * @param index the position at which to insert the new node
     * @param value the value to insert
     */
    public void insert(int index, E value)
    {
        // if the index is out of bounds, throw an exception
        if (index < 0 || index > size())
        {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size());
        }
        
        // if inserting at the front of the list, set the head
        if (index == 0)
        {
            addFirst(value);
            return;
        }
        // if the inserting at the end, set the tail
        else if (index == size())
        {
            addLast(value);
            return;
        }
        // otherwise, cycle through the nodes and add a new one at the specified position
        else
        {
            Node<E> current = head; // to iterate through the nodes
        
            // cycle through the nodes until the node in the position immediately before
            // the specified index is reached
            for (int i = 0; i < index-1; i++)
            {
                current = current.getNext();
            }
            
            // insert a new node between the node at the specified position and the one before it
            current.setNext(new Node<E>(value, current.getNext(), current));
        }
    }
    
    /**
     * Removes the node at the specified index from this list.
     * 
     * @param index the position of the Node to remove
     * @return the removed value
     */
    public E remove(int index)
    {
        E tmp; // to hold the value previously at the specified position so it can be returned
        
        // if the index is out of bounds, throw an exception
        if (index < 0 || index >= size())
        {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size());
        }
        
        // otherwise, cycle through the nodes and remove the one at the specified position
        else
        {
            Node<E> current = head; // to iterate through the nodes
        
            // cycle through the nodes until the node in the position immediately before
            // the specified index is reached
            for (int i = 0; i < index; i++)
            {
                current = current.getNext();
            }
            
            // store the value of and then remove the node at the specified position
            tmp = current.getValue();
            removeNode(current);
        }
        
        return tmp;
    }
    
    /**
     * Returns the index of the first instance of the specified value in this list.
     * 
     * @param value the value to find the index of
     * @return the index of the first instance of the specified value
     */
    public int indexOf(E value)
    {
        int i = 0; // to keep track of the index
        
        // cycle through the nodes looking for a matching value
        for (Node<E> current = head; current != null; current = current.getNext(), i++)
        {
            // if the current node's value matches the one specified, return the current index
            if (equals(current, value))
            {
                return i;
            }
        }
        
        // if the end of the list was reached without a match, return -1
        return -1;
    }
    
    /**
     * Returns the index of the last instance of the specified value in this list.
     * 
     * @param value the value to find the index of
     * @return the index of the last instance of the specified value
     */
    public int lastIndexOf(E value)
    {
        int i = 0; // to keep track of the index
        int last = -1; // the node with the highest index whose value matches the one specified
        
        // cycle through the nodes looking for matching values
        for (Node<E> current = head; current != null; current = current.getNext(), i++)
        {
            // if the current node's value matches the one specified, update the greatest matching index
            if (equals(current, value))
            {
                last = i;
            }
        }
        
        // once the end of the list has been reached, return the greatest matching index
        return last;
    }
    
    /**
     * Creates a new list that contains the values in this list from the specified
     * start index to the specified end index. Changes to the new list will not be
     * reflected in this one.
     * 
     * @param fromIndex the start of the sublist (inclusive)
     * @param toIndex the end of the sublist (exclusive)
     * @return a new list containing the values in this list from the specified range
     */
    public LinkedList<E> subList(int fromIndex, int toIndex)
    {
        // if either index is out of bounds, throw an exception
        if (fromIndex < 0)
        {
            throw new IndexOutOfBoundsException("Index " + fromIndex + " out of bounds for size " + size());
        }
        else if (toIndex > size())
        {
            throw new IndexOutOfBoundsException("Index " + toIndex + " out of bounds for size " + size());
        }
        else if (fromIndex > toIndex)
        {
            throw new IndexOutOfBoundsException("fromIndex cannot be greater than toIndex");
        }
        
        LinkedList<E> subList = new LinkedList<E>(); // the subList
        Node<E> current = head; // the node used in the current loop iteration
        
        // cycle through the nodes and add the ones in the specified range to the subList
        for (int i = 0; i < toIndex; current = current.getNext(), i++)
        {
            // if the current index is before the start index of the subList, do nothing
            if (i < fromIndex)
            {
                // do nothing
            }
            // otherwise, add the value at the current node to the subList
            else
            {
                subList.addLast(current.getValue());
            }
        }
        
        // return the subList
        return subList;
    }
    
    /**
     * Converts the data in this list into a String, with each value separated by a comma followed by a space.
     * 
     * @return the data in this list, formatted as a String
     */
    public String toString()
    {
        String str = "["; // the data from this list, formatted as a String
        
        // if the list is empty, the String should be too
        if (isEmpty())
        {
            // do nothing
        }
        // otherwise, add all of the values to the String
        else
        {
            // add the first value to the String
            str += head.getValue() == null ? "null" : head.getValue().toString();
            
            // cycle through the remaining nodes and add the value of each to the String, separated by commas
            for (Node<E> current = head.getNext(); current != null; current = current.getNext())
            {
                str += current.getValue() == null ? ",null" : "," + current.getValue().toString();
            }
        }
        
        return str + "]";
    }
    
    /**
     * Tests the specified Node's value against another specified value to
     * see if they are equal. If both values are null, this method will
     * return true.
     * 
     * @param node the Node whose value should be compared
     * @param val the value to compare the Node to
     * @return whether or not the values are equal
     */
    private boolean equals(Node node, E value)
    {
        return (node.getValue() != null && node.getValue().equals(value))
            || (node.getValue() == null && value == null);
    }
    
    /**
     * A helper method to remove the specified node and update the pointers of the surrounding nodes,
     * as well as the head and tail values if needed.
     * 
     * @param node the node to remove from this list
     */
    private void removeNode(Node<E> node)
    {
        // if the current Node is the head, update the head
        if (node == head)
        {
            head = node.getNext();
            
            // if the new head isn't null, set its previous pointer to null
            if (head != null)
            {
                head.setPrevious(null);
            }
        }
        // otherwise, update the previous Node
        else
        {
            node.getPrevious().setNext(node.getNext());
        }
                
        // if the current Node is the tail, update the tail
        if (node == tail)
        {
            tail = node.getPrevious();
            
            // if the new tail isn't null, set its next pointer to null
            if (head != null)
            {
                tail.setNext(null);
            }
        }
        // otherwise, update the next Node
        else
        {
            node.getNext().setPrevious(node.getPrevious());
        }
    }
    
    /**
     * Removes all Nodes containing duplicate values from this list so
     * each unique value only appears once. The first instance of each
     * value will be kept.
     */
    public void removeDuplicates()
    {
        LinkedList<E> vals = new LinkedList<E>();
        
        // cycle through the nodes and look for duplicates
        for (Node<E> current = head; current != null; current = current.getNext())
        {
            // if there is another instance of the same value in the list, remove this one
            if (vals.contains(current.getValue()))
            {
                removeNode(current);
            }
            // otherwise, add this value to the list of values already checked
            else
            {
                vals.addLast(current.getValue());
            }
        }
    }
    
    /**
     * Creates a duplicate of this list ({@code newList}) such that {@code this != newList} but
     * {@code this.equals(newList)}.
     * 
     * @return a copy of this list
     */
    public LinkedList<E> clone()
    {
        LinkedList<E> list = new LinkedList<E>();
        
        for (Node<E> current = head; current != null; current = current.getNext())
        {
            list.addLast(current.getValue());
        }
        
        return list;
    }
    
    /**
     * Concatenates all values in this LinkedList together, separated by the specified String.
     * 
     * @param separator the String that will be placed between each value
     * @return a String with all values in this list joined together by the specified separator
     */
    public String join(String separator)
    {
        String str = "";
        Node<E> current;
        
        // make sure the list isn't empty
        if (!isEmpty())
        {
            // iterate through each of the nodes and add its value to the String
            for (current = head; current.getNext() != null; current = current.getNext())
            {
                // add the value
                str += current.getValue() == null
                    ?  "null"
                    :  current.getValue().toString();
                
                // add the separator
                str += separator;
            }
            // add the last value without a trailing separator
            str += current.getValue() == null
                    ?  "null"
                    :  current.getValue().toString();
        }
        
        return str;
    }
    
    /**
     * Returns the head of this list.
     * 
     * @return the head of this list.
     */
    public Node<E> getHead()
    {
        return this.head;
    }
    
    /**
     * Creates an Iterator object so the LinkedList can be iterated through using a foreach loop.
     * 
     * @return an iterator to be used in a foreach loop
     */
    public Iterator<E> iterator()
    {
        return new LinkedListIterator<E>(this);
    }
    
    /**
     * A helper class which allows LinkedList objects to be iterated through using foreach loops.
     */
    private class LinkedListIterator<E> implements Iterator<E>
    {
        LinkedList<E> list;
        Node<E> current;
        Node<E> previous;
        
        /**
         * Constructs a new LinkedListIterator object for the specified list.
         * 
         * @param list the list to create an Iterator for
         */
        public LinkedListIterator(LinkedList<E> list)
        {
            this.list = list;
            current = list.getHead();
            previous = null;
        }
        
        /**
         * Returns the next element in the list.
         * 
         * @return the next element in the list
         */
        public E next()
        {
            E value = current.getValue();
            previous = current;
            current = current.getNext();
            return value;
        }
        
        /**
         * Checks if there is another element in the list.
         * 
         * @return {@code true} if not all elements have been iterated through yet
         */
        public boolean hasNext()
        {
            return current != null;
        }
        
        /**
         * Removes the current value from the list.
         */
        public void remove()
        {
            list.removeNode(previous);
        }
    }
}
