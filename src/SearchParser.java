
/**
 * A helper class to turn a search query into a tree of {@link QueryNode} objects.
 *
 * @author Jordan Greenbaum
 * @version 25 April 2021
 */
public class SearchParser
{
    /**
     * Splits the specified query String into its different arguments, then parses each argument as its own QueryNode
     * which gets added to a tree structure.
     * 
     * @param query the search query to parse
     * @return the search query parsed as a tree of QueryNodes
     */
    public static QueryNode parseQuery(String query)
    {
        String[] args = query.split("\\s+|(?=\\))");
        QueryNode head = new AndNode(null);
        
        addQueryNode(args, 0, head);
        
        return head;
    }
    
    /**
     * Recursively parses each argument in the specified list of query args and adds a QueryNode
     * for each one to build a tree.
     * 
     * @param args the search query, split into its separate arguments
     * @param index the index of the argument to parse
     * @param parent the parent of the new node
     */
    private static void addQueryNode(String[] args, int index, QueryNode parent)
    {
        // base case: the end of the array is reached
        if (index >= args.length)
        {
            // do nothing
        }
        // recursive case: new AND node
        else if (args[index].equalsIgnoreCase("and") || args[index].equalsIgnoreCase("(and"))
        {
            AndNode n = new AndNode(parent);
            
            parent.addNode(n);
            addQueryNode(args, index + 1, n);
        }
        // recursive case: new OR node
        else if (args[index].equalsIgnoreCase("or") || args[index].equalsIgnoreCase("(or"))
        {
            OrNode n = new OrNode(parent);
            
            parent.addNode(n);
            addQueryNode(args, index + 1, n);
        }
        // recursive case: end of tree level
        else if (args[index].equals(")"))
        {
            // go up a level in the tree
            addQueryNode(args, index + 1, parent.getParent());
        }
        // recursive case: new non-operator node
        else
        {
            QueryNode n;
            
            // determine which node to add
            if (args[index].matches("\\d\\d?\\/\\d\\d?\\/\\d\\d\\d\\d\\-\\-\\d\\d?\\/\\d\\d?\\/\\d\\d\\d\\d"))
            {
                n = new DateRangeNode(parent, args[index]);
            }
            else if (args[index].matches("\\d\\d?\\/\\d\\d?\\/\\d\\d\\d\\d"))
            {
                n = new DateNode(parent, args[index]);
            }
            else
            {
                n = new TagNode(parent, args[index]);
            }
            
            // add the node
            parent.addNode(n);
            
            // continue building the tree
            addQueryNode(args, index + 1, parent);
        }
    }
}
