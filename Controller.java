import java.util.Scanner;
import java.io.*;

/**
 * The controller portion of the MVC design. Reads commands from the user and prompts for all information
 * needed to run each command before sending it to the model for execution.
 * 
 * @author Jordan Greenbaum
 * @version 25 April 2021
 */
public class Controller
{
    private static Scanner input = new Scanner(System.in);
    
    /**
     * Runs the TaskKeeper project.<br>
     * Uses user-inputted commands to determine which methods should be run next.
     * 
     * @param args commandline arguments
     */
    public static void main(String[] args)
    {
        String command = null;

        // loop the program until the user wants to exit
        mainloop: // label for nested break statement
        while (true)
        {
            // get next command
            Viewer.prompt("> ");
            command = input.nextLine();
            
            Viewer.printEmptyLine();            
            
            // run the specified command
            switch (command.toLowerCase())
            {
                case "exit":
                case "e":
                    Viewer.printMsg("Bye!");
                    break mainloop;
                    
                case "help":
                case "h":
                    runHelp();
                    break;
                    
                case "add-tag":
                case "atg":
                    runAddTag();
                    break;
                    
                case "delete-tag":
                case "det":
                    runDeleteTag();
                    break;
                    
                case "display-tags":
                case "dit":
                    runDisplayTags();
                    break;
                    
                case "add-task":
                case "atk":
                    runAddTask();
                    break;
                    
                case "search-tags":
                case "st":
                    runSearchTags();
                    break;
                    
                case "search-dates":
                case "sd":
                    runSearchDates();
                    break;
                    
                case "search-tags-dates":
                case "std":
                    runSearchTagsDates();
                    break;
                    
                case "search-text":
                case "stxt":
                    runSearchText();
                    break;
                    
                case "save-tasks":
                case "stk":
                    runSaveTasks();
                    break;
                    
                case "load-tasks":
                case "ltk":
                    runLoadTasks();
                    break;
                    
                default:
                    Viewer.printError("Unknown command: " + command);
                    break;
            }
            
            Viewer.printEmptyLine();
        }
    }

    /**
     * Calls the help command.
     */
    private static void runHelp()
    {
        String subcommand = "";
        Model.help();
        
        Viewer.printEmptyLine();
        Viewer.subprompt("Enter the name of a command to see more information about it, or press enter to return to the main menu: ");
        subcommand = input.nextLine();
        
        while (!subcommand.equals(""))
        {
            Viewer.printEmptyLine();
            Model.help(subcommand);
            Viewer.printEmptyLine();
            Viewer.subprompt("Enter the name of a command to see more information about it, or press enter to return to the main menu: ");
            subcommand = input.nextLine();
        }
    }

    /**
     * Prompts the user to input a tag name, then runs the command to add it to the system.
     */
    private static void runAddTag()
    {
        String tag;
        
        Viewer.subprompt("Tag: ");
        tag = input.nextLine();
        
        while (!tag.equals(""))
        {
            Model.addTag(tag);
            Viewer.subprompt("Tag: ");
            tag = input.nextLine();
        }
    }

    /**
     * Prompts the user to input a tag name, then runs the command to delete it from the system.
     */
    private static void runDeleteTag()
    {
        Viewer.subprompt("Tag: ");
        Model.deleteTag(input.nextLine());
    }

    /**
     * Runs the command to display all tags in the system.
     */
    private static void runDisplayTags()
    {
        Model.displayTags();
    }

    /**
     * Prompts the user to input a description, due date, and optional tags. Then, runs the command to add
     * a new Task with the collected data to the system.
     */
    private static void runAddTask()
    {
        String description; // the new Task's description
        String date; // the new Task's due date
        LinkedList<String> tags = new LinkedList<String>(); // the new Task's tags
        String currentTag; // the tag most recently added by the user
        
        // get the description from the user
        Viewer.subprompt("Description: ");
        description = input.nextLine();
        
        // get the date from the user
        Viewer.subprompt("Due Date (MM/DD/YYYY): ");
        date = input.nextLine();
        
        if (Model.parseDate(date) == null)
        {
            Viewer.printError("Cannot parse '" + date + "' as a date.");
            return;
        }
        
        // get the tags from the user
        Viewer.subprompt("[Optional] Tag: ");
        currentTag = input.nextLine();
        
        // add multiple tags to the Task
        while (!currentTag.equals(""))
        {
            // if the tag is valid and is in the system, add it to the Task
            if (!Model.tagExists(currentTag))
            {
                Viewer.printError("The tag '" + currentTag + "' does not exist. Please add it to the system before attaching it to a Task.");
            }
            else if (!Model.tagIsValid(currentTag))
            {
                Viewer.printError("The name '" + currentTag + "' does not follow the rules for naming tags. Tags must begin with a letter and cannot have any whitespace.");
            }
            else
            {
                tags.addLast(currentTag);
            }
            
            // prompt for next tag
            Viewer.subprompt("[Optional] Tag: ");
            currentTag = input.nextLine();
        }
        
        Model.addTask(description, date, tags);
    }

    /**
     * Prompts the user for a query and runs the command to search for Tasks based on their tags.
     * Then, allows the user to modify the Tasks returned in the search result.
     */
    private static void runSearchTags()
    {
        LinkedList<Task> results; // the results of the search
        
        // prompt the user for the query and run the search
        Viewer.subprompt("Tags Query: ");
        results = Model.searchTags(input.nextLine());
        Viewer.printEmptyLine();
        
        // allow the user to modify tasks from the search result
        parseResults(results);
    }
    
    /**
     * Prompts the user for a query and runs the command to search for Tasks based on their due dates.
     * Then, allows the user to modify the Tasks returned in the search result.
     */
    private static void runSearchDates()
    {
        LinkedList<Task> results; // the results of the search
        
        // prompt the user for the query and run the search
        Viewer.subprompt("Date Query: ");
        results = Model.searchDates(input.nextLine());
        Viewer.printEmptyLine();
        
        // allow the user to modify tasks from the search result
        parseResults(results);
    }
    
    /**
     * Prompts the user for a query and runs the command to search for Tasks based on their tags and due dates.
     * Then, allows the user to modify the Tasks returned in the search result.
     */
    private static void runSearchTagsDates()
    {
        LinkedList<Task> results; // the results of the search
        
        // prompt the user for the query and run the search
        Viewer.subprompt("Query: ");
        results = Model.searchTagsDates(input.nextLine());
        Viewer.printEmptyLine();
        
        // allow the user to modify tasks from the search result
        parseResults(results);
    }

    /**
     * Prompts the user for a query and runs the command to search for Tasks based on their descriptions.
     * Then, allows the user to modify the Tasks returned in the search result.
     */
    private static void runSearchText()
    {
        LinkedList<Task> results; // the results of the search
        
        // prompt the user for the query and run the search
        Viewer.subprompt("Text Query: ");
        results = Model.searchText(input.nextLine());
        Viewer.printEmptyLine();
        
        // allow the user to modify tasks from the search result
        parseResults(results);
    }

    /**
     * Prompts the user for a filename. If it already exists, confirm they want to overwrite the data.
     * Then, runs the command to save data to the specified file.
     */
    private static void runSaveTasks()
    {
        File f; // the file to save to
        
        // get the filename
        Viewer.subprompt("Filename (no extension): ");
        f = new File(input.nextLine() + ".csv");
        
        // check if the file already exists
        if (f.exists())
        {
            // confirm that the user wants to overwrite the file's data if it already exixts
            if (confirm("The file '" + f.getName() + "' already exists. Would you like to overwrite its contents?"))
            {
                Model.saveTasks(f);
            }
            else
            {
                Viewer.printMsg("Command cancelled.");
            }
        }
        // if the file doesn't already exist, save the data automatically
        else
        {
            Model.saveTasks(f);
        }
    }

    /**
     * Prompts the user for a filename. If there is data in the system already, confirm that the user
     * wants to overwrite it. Then, runs the command to load data from the specified file.
     */
    private static void runLoadTasks()
    {
        File f; // the file load from
        
        // check if the model has data
        if (Model.hasData())
        {
            // if it does, confirm that the user wants to overwrite data currently in the system
            if (!confirm("Are you sure you want to overwrite the data in the system?"))
            {
                // do not load data if the user does not confirm
                return;
            }
        }
        
        // get the filename
        Viewer.subprompt("Filename (no extension): ");
        f = new File(input.nextLine() + ".csv");
        
        // check if the file exists
        if (f.exists())
        {
            Model.loadTasks(f);
        }
        // if the file doesn't exist, print an error
        else
        {
            Viewer.printError("The file '" + f.getName() + "' does not exist.");
        }
    }
    
    /**
     * Displays the specified prompt and asks the user if they would like to continue. Returns true if the
     * user responds with "yes". Used to add an extra layer of confirmation before an action is performed.
     * 
     * @return true if the user responds with "y", otherwise returns false
     */
    public static boolean confirm(String prompt)
    {
        String response; // the user's response
        
        // prompt the user
        Viewer.printMsg(prompt);
        Viewer.subprompt("yes/no: ");
        response = input.nextLine().toLowerCase();
        
        // reprompt until the user responds with yes or no
        while (!response.equals("yes") && !response.equals("no"))
        {
            Viewer.printError("You must respond with 'yes' or 'no'.");
            Viewer.subprompt("yes/no: ");
            response = input.nextLine().toLowerCase();
        }
        
        // if the user responds with yes, return true
        if (response.equals("yes"))
        {
            return true;
        }
        // otherwise, return false
        else
        {
            return false;
        }
    }
    
    /**
     * A helper method for when searches find more than one matching Task as a result.
     * Asks the user to specify which result from a previously printed itemized list
     * they would like to select.
     * 
     * @param tasks a list of Tasks from which the user will select one
     * @return the Task selected by the user
     */
    public static Task chooseTask(LinkedList<Task> tasks)
    {
        // if there is only one task in the list, return it
        if (tasks.size() == 1)
        {
            return tasks.get(0);
        }
        // otherwise, hava the user choose a task from the list
        else
        {
            String choice; // the user's choice
            int parsed = 0; // the user's choice, parsed as an int
            boolean valid = false; // whether or not the user's choice is parseable and within range
            
            Viewer.subprompt("Task: ");
            choice = input.nextLine();
            
            // prompt the user until they enter a valid number
            while (!valid)
            {
                // try to parse the choice
                try
                {
                    parsed = Integer.parseInt(choice);
                    
                    // check if the user's choice is within range
                    if (parsed > 0 && parsed <= tasks.size() && tasks.get(parsed) != null)
                    {
                        valid = true;
                    }
                    else
                    {
                        Viewer.printError("Option '" + choice + "' was either deleted or is out of range.");
                    }
                }
                catch (NumberFormatException e)
                {
                    Viewer.printError("Unable to parse '" + choice + "'.");
                }
                finally
                {
                    // if the user's choice was invalid, reprompt them
                    if (!valid)
                    {
                        Viewer.subprompt("Task: ");
                        choice = input.nextLine();
                    }
                }
            }
            
            return tasks.get(parsed - 1);
        }
    }
    
    /**
     * Provides a menu for modifying the specified Tasks.
     * Allows the user to:<br>
     * <ul>
     * <li>Add a new tag to a Task.</li>
     * <li>Change the description and due date of a Task.</li>
     * <li>Delete a Task from the TaskKeeper system.</li>
     * <li>Append the specified Tasks' data to the end of a file.</li>
     * </ul>
     * 
     * @param task the Task to modify
     */
    private static void modifyTasks(LinkedList<Task> tasks)
    {
        int counter = 1;
        String option; // the user's choice
        Task current; // the task the user wants to modify
        
        // print tasks
        Viewer.printMsg("===== Search Results =====");
        for (Task t : tasks)
        {
            System.out.printf("\t\t%2d. %s (Due %s)\n", counter, t.getDescription(), t.getDateAsString());
            counter++;
        }
        
        // print subcommand options
        Viewer.printEmptyLine();
        Viewer.printMsg("a. add tag to task, b. edit task, c. delete task, d. save tasks");
        Viewer.printEmptyLine();
        
        // get user option
        Viewer.subprompt("Option: ");
        option = input.nextLine();
        
        while (!option.equals(""))
        {
            switch(option.charAt(0))
            {
                // add tag to task
                case 'A':
                case 'a':
                {
                    String tag; // the tag to add
                    
                    // get data
                    current = chooseTask(tasks);
                    Viewer.subprompt("Tag: ");
                    tag = input.nextLine();
                    
                    // attempt to add the tag and print an error if it fails
                    if (!current.addTag(tag))
                    {
                        Viewer.printError("The tag '" + tag + "' either does not exist or has already been added to this task.");
                    }
                    
                    break;
                }
                    
                // edit task
                case 'B':
                case 'b':
                {
                    String description;
                    String date;
                    
                    // get data
                    current = chooseTask(tasks);
                    
                    Viewer.subprompt("[Optional] Description: ");
                    description = input.nextLine();
                    
                    Viewer.subprompt("[Optional] Due Date (MM/DD/YYYY): ");
                    date = input.nextLine();
                    
                    // modify description
                    if (!description.equals(""))
                    {
                        current.setDescription(description);
                    }
                    
                    // modify due date
                    if (date == null)
                    {
                        Viewer.printError("Cannot parse '" + date + "' as a date.");
                    }
                    else if (!date.equals(""))
                    {
                        current.setDate(date);
                    }
                    
                    break;
                }
                
                // delete task
                case 'C':
                case 'c':
                {
                    current = chooseTask(tasks);
                    
                    // remove the task from the system
                    Model.removeTask(current);
                    
                    // remove the task from the results
                    tasks.set(tasks.indexOf(current), null);
                    
                    break;
                }
                    
                // save tasks
                case 'D':
                case 'd':
                {
                    File f; // the file to save to
                    FileWriter output = null; // to write to the file
                    
                    Viewer.subprompt("Filename: ");
                    f = new File(input.nextLine() + ".csv");
                    
                    // append the data to the end of the file
                    try
                    {
                        output = new FileWriter(f, true);
                    
                        for (Task t : tasks)
                        {
                            output.write("\"" + t.getDescription().replaceAll("\\\"", "\"\"") + "\"," + t.getDateAsString() + "," + t.getTags().join(" ") + "\n");
                        }
                        
                        output.close();
                    }
                    catch (IOException e)
                    {
                        Viewer.printError("There was an issue writing to the specified file.");
                    }
                    
                    break;
                }
                    
                default:
                    Viewer.printError("'" + option + "' is not a valid option.");
                    break;
            }
            
            // get user option
            Viewer.printEmptyLine();
            Viewer.subprompt("Option: ");
            option = input.nextLine();
        }
    }
    
    /**
     * A helper method that prints a message if a search returns 0 results.
     * Otherwise, allows the user to modify tasks in the results according
     * to the {@link modifyTasks} method.
     * 
     * @param results the results of the search
     */
    private static void parseResults(LinkedList<Task> results)
    {
        if (results.size() == 0)
        {
            Viewer.printMsg("The search returned no results.");
        }
        else {
            results.removeDuplicates();
            modifyTasks(results);
        }
    }
}
