import java.io.*;
import java.util.Scanner;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * The system that keeps track of all Tasks and tags and allows the user to manipulate
 * data.
 * 
 * @author Jordan Greenbaum
 * @version 25 April 2021
 */
public class Model
{
    private static LinkedList<Task> tasks = new LinkedList<Task>(); // a list of all Tasks in this TaskKeeper system
    private static LinkedList<String> tags = new LinkedList<String>(); // a list of all tags in this TaskKeeper system

    /**
     * Gets the Task object with the specified ID.
     * 
     * @param id the ID of the Task to get
     * @return the Task with the specified ID, or null if there is no Task with that ID
     */
    static Task getTask(int id)
    {
        // iterate through the Tasks
        for (Task t : tasks)
        {
            // if the current Task is the one specified, return it
            if (t.ID == id)
            {
                return t;
            }
        }
        
        return null;
    }
    
    /**
     * Displays the help.txt file.
     */
    static void help()
    {
        try
        {
            Scanner sc = new Scanner(new File("./help.txt"));

            while (sc.hasNextLine()) {
                Viewer.printMsg(sc.nextLine());
            }

            sc.close();
        } catch (IOException err) {
            Viewer.printError("Error reading help.txt file.");
        }
    }
    
    /**
     * Displays help information for the specified command.
     * 
     * @param command the command to display information for
     */
    static void help(String command)
    {
        switch(command.toLowerCase())
        {
            case "exit":
            case "e":
                Viewer.printMsg("Command: exit");
                Viewer.printMsg("Alias:   e");
                Viewer.printMsg("============");
                Viewer.printMsg("Description: Exits the TaskKeeper program.");
                break;
                
            case "help":
            case "h":
                Viewer.printMsg("Command: help");
                Viewer.printMsg("Alias:   h");
                Viewer.printMsg("============");
                Viewer.printMsg("Description: Displays the help.txt file in the terminal window. Once all lines have been added to the display, the user is prompted to type a command name to display more information about a specific command. The detailed command info. is hard coded into the program but the help.txt file may be edited by the user. To exit the help command, the user can press enter without typing a command name.");
                break;
                
            case "add-tag":
            case "atg":
                Viewer.printMsg("Command: add-tag");
                Viewer.printMsg("Alias:   atg");
                Viewer.printMsg("============");
                Viewer.printMsg("Description: Allows the user to add an indefinite number of tags to the TaskKeeper system. To stop adding tags, the user can press enter without typing in a new tag.");
                break;
                
            case "delete-tag":
            case "det":
                Viewer.printMsg("Command: delete-tag");
                Viewer.printMsg("Alias:   det");
                Viewer.printMsg("============");
                Viewer.printMsg("Description: Prompts the user to enter the name of an existing tag, which it then removes from the TaskKeeper system and all tasks it had been added to.");
                break;
                
            case "display-tags":
            case "dit":
                Viewer.printMsg("Command: display-tags");
                Viewer.printMsg("Alias:   dit");
                Viewer.printMsg("============");
                Viewer.printMsg("Description: Lists all tags in the TaskKeeper system.");
                break;
                
            case "add-task":
            case "atk":
                Viewer.printMsg("Command: add-task");
                Viewer.printMsg("Alias:   atk");
                Viewer.printMsg("============");
                Viewer.printMsg("Description: Prompts the user for a description, due date, and indefinite number of tags. Once the user is finished entering tags, a new task is created from the inputted data and added to the TaskKeeper system.");
                break;
                
            case "search-tags":
            case "st":
                Viewer.printMsg("Command: search-tags");
                Viewer.printMsg("Alias:   st");
                Viewer.printMsg("============");
                Viewer.printMsg("Description: Prompts the user for a search query and displays a list of all tasks in the system that match all arguments in the query. Once the results are displayed, the user is able to either add a tag to, edit, or delete a task from the system, or append all search results to the end of a file. Search queries for this command can include tags and logic operators. Search results are displayed in order of when the tasks were added to the system.");
                break;
                
            case "search-dates":
            case "sd":
                Viewer.printMsg("Command: search-dates");
                Viewer.printMsg("Alias:   sd");
                Viewer.printMsg("============");
                Viewer.printMsg("Description: Prompts the user for a search query and displays a list of all tasks in the system that match all due dates in the query. Once the results are displayed, the user is able to either add a tag to, edit, or delete a task from the system, or append all search results to the end of a file. Search queries for this command can include dates, date ranges, and logic operators. Search results are displayed in order of when the tasks were added to the system.");
                break;
                
            case "search-tags-dates":
            case "std":
                Viewer.printMsg("Command: search-tags-dates");
                Viewer.printMsg("Alias:   std");
                Viewer.printMsg("============");
                Viewer.printMsg("Description: Prompts the user for a search query and displays a list of all tasks in the system that match all arguments in the query. Once the results are displayed, the user is able to either add a tag to, edit, or delete a task from the system, or append all search results to the end of a file. Search queries for this command can include tags, dates, date ranges, and logic operators. Search results are displayed in order of when the tasks were added to the system.");
                break;
                
            case "search-text":
            case "stxt":
                Viewer.printMsg("Command: search-text");
                Viewer.printMsg("Alias:   stxt");
                Viewer.printMsg("============");
                Viewer.printMsg("Description: Prompts the user for a search query and displays a list of all tasks in the system whose descriptions include the inputted text. Once the results are displayed, the user is able to either add a tag to, edit, or delete a task from the system, or append all search results to the end of a file. Search queries for this command are case-sensitive and can only include one piece of text. Search results are displayed in order of when the tasks were added to the system.");
                break;
                
            case "save-tasks":
            case "stk":
                Viewer.printMsg("Command: save-tasks");
                Viewer.printMsg("Alias:   stk");
                Viewer.printMsg("============");
                Viewer.printMsg("Description: Prompts the user for a filename (without the extension), then writes all tasks in the TaskKeeper system to that file in a csv format. Pre-existing files will be overwritten, but the user will need to confirm that they would like to overwrite the file’s data before anything is erased.");
                break;
                
            case "load-tasks":
            case "ltk":
                Viewer.printMsg("Command: load-tasks");
                Viewer.printMsg("Alias:   ltk");
                Viewer.printMsg("============");
                Viewer.printMsg("Description: Prompts the user for a filename (without the extension), then loads data from that file into the TaskKeeper system. Loaded data replaces any data currently in the system, but the user will need to confirm that they would like to overwrite the current data before anything is erased (unless the system is empty).");
                break;
                
            default:
                Viewer.printError("Unknown command: " + command);
                break;
        }
    }

    /**
     * Adds the specified tag to the system.
     * 
     * @param tag the tag to add
     * @return whether or not the operation was a success
     */
    static boolean addTag(String tag)
    {
        // if the tag already exists, return an error
        if (tagExists(tag.toLowerCase()))
        {
            Viewer.printError("Operation failed: the specified tag already exists.");
            return false;
        }
        // otherwise, if the doesn't adhere to the naming rules, return an error
        else if (!tagIsValid(tag))
        {
            Viewer.printError("Operation failed: tags cannot contain spaces or commas and must start with a letter.");
            return false;
        }
        // otherwise, add it to the system
        else
        {
            tags.addLast(tag.toLowerCase());
            return true;
        }
    }

    /**
     * Deletes the specified tag from the system and all Tasks associated with it.
     * 
     * @param tag the tag to delete
     * @return whether or not the tag was successfully found and removed from the system
     */
    static boolean deleteTag(String tag)
    {
        // if the tag exists, remove it
        if (tags.contains(tag.toLowerCase()))
        {
            // remove the tag from all tasks that have it
            for (Task t : tasks)
            {
                if (t.hasTag(tag.toLowerCase()))
                {
                    t.removeTag(tag.toLowerCase());
                }
            }
            
            // remove the tag from the system
            tags.remove(tag.toLowerCase());
            return true;
        }
        // otherwise, display an error
        else
        {
            Viewer.printError("Operation failed: the specified tag does not exist");
            return false;
        }
    }

    /**
     * Displays all tags currently in the system.
     */
    static void displayTags()
    {
        for (String t : tags)
        {
            Viewer.printMsg(t);
        }
    }

    /**
     * Adds a new Task with the specified description, due date, and tags to the system.
     * 
     * @param description the new Task's description
     * @param date the new Task's due date in MM/DD/YYYY format
     * @param tags a list of all tags to add to the new Task
     */
    static void addTask(String description, String date, LinkedList<String> tags)
    {
        tasks.addLast(new Task(description, date, tags));
    }

    /**
     * Searches for Tasks based on their tags.
     * 
     * @param query a search query consisting of a tag or a combination of multiple tags using logical operators
     * @return a list of all Tasks in the system that matched the specified query
     */
    static LinkedList<Task> searchTags(String query)
    {
        LinkedList<Task> taskList = tasks.clone();
        String[] args = query.toLowerCase().split("\\s+|(?=\\))"); // split at spaces and closing parenthises
        
        // make sure the search query contains only parseable tags
        for (String s : args)
        {
            if (!parseArgType(s).equals("tag") && !parseArgType(s).equals("logic operator"))
            {
                Viewer.printError("Unable to parse '" + s + "' as a tag.");
                return null;
            }
        }
        
        // if the query wasn't invalid, search the tasks
        return searchTagsDates(query);
    }
    
    /**
     * Searches for Tasks based on their due dates.
     * 
     * @param query a search query consisting of a date or a range of dates or multiple dates/ranges using logical operators
     * @return a list of all Tasks in the system that matched the specified query
     */
    static LinkedList<Task> searchDates(String query)
    {
        LinkedList<Task> taskList = tasks.clone();
        String[] args = query.toLowerCase().split("\\s+|(?=\\))"); // split at spaces
        
        // make sure the search query contains only parseable tags
        for (String s : args)
        {
            if (!parseArgType(s).equals("date") && !parseArgType(s).equals("date range") && !parseArgType(s).equals("logic operator"))
            {
                Viewer.printError("Unable to parse '" + s + "' as a date or date range.");
                return null;
            }
        }
        
        // if the query wasn't invalid, search the tasks
        return searchTagsDates(query);
    }
    
    /**
     * Searches for Tasks based on their tags and due dates.
     * 
     * @param query a search query consisting of a combination of tags and dates/ranges using logical operators
     * @return a list of all Tasks in the system that matched the specified query
     */
    static LinkedList<Task> searchTagsDates(String query)
    {
        QueryNode queryTree = SearchParser.parseQuery(query); // parse the query as a tree
        LinkedList<Task> results = tasks.clone(); // duplicate the list of tasks
        Iterator<Task> listIterator = results.iterator(); // to iterate through the list with the ability to remove nodes
        
        // iterate through each task in the system
        while (listIterator.hasNext())
        {
            // if the task does not match the specified query, remove it from the results
            if (!queryTree.match(listIterator.next()))
            {
                listIterator.remove();
            }
        }
        
        return results;
    }

    /**
     * Searches for Tasks based on their descriptions. Does not parse any logical operators.
     * 
     * @param query a search query consisting of a single piece of text to look for in each Task's description
     * @return a list of all Tasks in the system that matched the specified query
     */
    static LinkedList<Task> searchText(String query)
    {
        LinkedList<Task> results = tasks.clone(); // duplicate the list of tasks
        Iterator<Task> listIterator = results.iterator(); // to iterate through the list with the ability to remove nodes
        
        // iterate through each task in the system
        while (listIterator.hasNext())
        {
            // if the task's description does not contain the specified string of text, remove it from the results
            if (!listIterator.next().getDescription().contains(query))
            {
                listIterator.remove();
            }
        }
        
        return results;
    }

    /**
     * Saves data to the specified file.
     * 
     * @param file the file in which the data will be saved
     */
    static void saveTasks(File file)
    {
        try
        {
            PrintWriter output = new PrintWriter(file);
            
            // print all system tasks in a csv format
            for (Task t : tasks)
            {
                output.println("\"" + t.getDescription().replaceAll("\\\"", "\"\"") + "\"," + t.getDateAsString() + "," + t.getTags().join(" "));
            }
            
            output.close();
        }
        catch(IOException e)
        {
            Viewer.printError("Unable to save to the specified file.");
        }
    }

    /**
     * Replaces data in the system with data loaded from the specified file.
     * 
     * @param file the file from which the data will be loaded
     */
    static void loadTasks(File file)
    {
        try
        {
            Scanner input = new Scanner(file); // to read input
            Pattern p = Pattern.compile("\\\"(.*)\\\",(.*),(.*)");
            
            reset();
            
            // load tasks
            while(input.hasNextLine())
            {
                String line = input.nextLine(); // get the next line
                Matcher parser = p.matcher(line); // set up a matcher for capture groups
                
                // set up capture groups
                if (parser.find())
                {
                    Task t = new Task(
                        parser.group(1).replaceAll("\\\"\\\"", "\""),
                        parser.group(2),
                        new LinkedList<String>(parser.group(3).toLowerCase().split(" "))
                    );
                    
                    // add the task to the system
                    tasks.addLast(t);
                    
                    // add any new tags to the system
                    for (String tag : t.getTags())
                    {
                        if (!Model.tagExists(tag))
                        {
                            Model.addTag(tag);
                        }
                    }
                }
            }
            
            input.close();
        }
        catch(IOException e)
        {
            Viewer.printError("Unable to load the specified file.");
        }
    }
    
    /**
     * Determines whether the specified query argument is a tag, date, or date range.
     * 
     * @param arg the query argument whose type will be determined
     * @return either "tag", "date", or "date range", "logic operator", or an empty String depending on the argument's type
     */
    private static String parseArgType(String arg)
    {
        String type; // the type of the supplied argument
        
        if (arg.matches("\\d\\d?\\/\\d\\d?\\/\\d{4}\\-\\-\\d\\d?\\/\\d\\d?\\/\\d{4}"))
        {
            type = "date range";
        }
        else if (arg.matches("\\d\\d?\\/\\d\\d?\\/\\d\\d\\d\\d"))
        {
            type = "date";
        }
        else if (arg.equals("or") || arg.equals("and") || arg.equals("(or") || arg.equals("(and") || arg.equals(")"))
        {
            type = "logic operator";
        }
        else if (Model.tagIsValid(arg))
        {
            type = "tag";
        }
        else
        {
            type = "";
        }
        
        return type;
    }
    
    /**
     * Parses the specified string as a date.
     * 
     * @param str the String to parse
     * @return the date in the String, parsed as a LocalDate object, or null if the String can't be parsed
     */
    public static LocalDate parseDate(String str)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M[M]/d[d]/yyyy");
        LocalDate date;
        
        try
        {
            date = LocalDate.parse(str, dtf);
        }
        catch(java.time.format.DateTimeParseException e)
        {
            date = null;
        }
        
        return date;
    }
    
    /**
     * Clears all data from the TaskKeeper.
     */
    public static void reset()
    {
        tasks.clear();
        tags.clear();
        Task.reset();
    }
    
    /**
     * Removes a task from the system.
     * 
     * @param task the task to remove
     */
    public static void removeTask(Task task)
    {
        tasks.remove(task);
    }
    
    /**
     * Verifies that a tag follows the naming rules.
     * 
     * @param tag the tag to verify the name of
     * @return whether or not the specified tag has a valid name
     */
    public static boolean tagIsValid(String tag)
    {
        return tag.length() > 0 // string is not empty
            && Character.isLetter(tag.charAt(0)) // must start with a letter
            && !tag.matches(".*\\s.*") // no whitespace
            && !tag.contains(",") // no commas
            && !tag.contains("(") // no parentheses
            && !tag.contains(")") // no parentheses
            && !tag.equalsIgnoreCase("or") // cannot be "or"
            && !tag.equalsIgnoreCase("and"); // cannot be "and"
    }
    
    /**
     * Checks whether or not the specified tag already exists in the system.
     * 
     * @param tag the tag to check for
     * @returns whether or not the tag already exists
     */
    public static boolean tagExists(String tag)
    {
        return tags.contains(tag.toLowerCase());
    }
    
    /**
     * Checks if the system currently contains any data.
     * 
     * @return whether or not the system contains any data
     */
    public static boolean hasData()
    {
        return tags.size() > 0 || tasks.size() > 0;
    }
}