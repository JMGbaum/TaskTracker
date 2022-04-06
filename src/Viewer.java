
/**
 * The viewer portion of the MVC design. Formats and displays data and menu options in a readable manner.
 *
 * @author Jordan Greenbaum
 * @version 25 April 2021
 */
public class Viewer
{
    private static final int LINE_LENGTH = 80;
    
    /**
     * Displays the specified message. Used for prompting and does not add a newline character to the end
     * of the message.
     * 
     * @param msg the message to display
     */
    static void prompt(String msg)
    {
        if (msg.length() <= LINE_LENGTH)
        {
            System.out.print(msg);
        }
        else
        {
            for (int i = LINE_LENGTH-1; i >= 0; i--)
            {
                if (Character.isWhitespace(msg.charAt(i)))
                {
                    System.out.println(msg.substring(0,i));
                    prompt(msg.substring(i+1));
                    return;
                }
            }
        }
    }
    
    /**
     * Displays the specified message indented so it is lined up with other subprompt messages. Used for
     * prompting and does not add a newline character to the end of the message.
     * 
     * @param msg the message to display
     */
    static void subprompt(String msg)
    {
        if (msg.length() <= LINE_LENGTH)
        {
            System.out.print("\t" + msg);
        }
        else
        {
            for (int i = LINE_LENGTH - 1; i >= 0; i--)
            {
                if (Character.isWhitespace(msg.charAt(i)))
                {
                    System.out.println("\t" + msg.substring(0,i));
                    subprompt(msg.substring(i+1));
                    return;
                }
            }
        }
    }
    
    /**
     * Displays the specified message as its own line with a tab space in front of it. Message is one
     * indent level higher than base level and on the same indent level as messages sent with the
     * {@link subprompt} method.
     * 
     * @param msg the message to display
     */
    static void printMsg(String msg)
    {
        if (msg.length() < LINE_LENGTH)
        {
            System.out.println("\t" + msg);
        }
        else
        {
            for (int i = LINE_LENGTH - 1; i >= 0; i--)
            {
                if (Character.isWhitespace(msg.charAt(i)))
                {
                    System.out.println("\t" + msg.substring(0,i));
                    printMsg(msg.substring(i+1));
                    return;
                }
            }
        }
    }
    
    /**
     * Displays the specified message as an error on its own line with a tab space in front of it. Message
     * is one indent level higher than base level and on the same indent level as messages sent with the
     * {@link subprompt} method.
     * 
     * @param msg the error to display
     */
    static void printError(String msg)
    {
        System.err.println("\tERROR: " + msg);
    }
    
    /**
     * Adds an empty line to the bottom of the output screen.
     */
    static void printEmptyLine()
    {
        System.out.println();
    }
}
