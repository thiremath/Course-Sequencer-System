package courseSequencer.driver;

import courseSequencer.projectmanager.ProjectManager;
import courseSequencer.util.ExceptionHandler;
/**
 * The Driver class is the entry point for the studentCourseSequencer application.
 * It validates the command-line arguments and initializes the ProjectManager to run the program.
 */
public class Driver {
    /**
     * The main method of the application.
     * It checks the number of arguments before initializing the ProjectManager.
     */
    public static void main(String[] args) {

        if (args.length != 3) {
            // If the number of arguments is not equal to 3, display an error message.
            String errorMessage = "Error: Incorrect number of arguments. Program accepts 3 arguments.";
            ExceptionHandler.handleException(null, errorMessage);
        } 

        else if (args[0].equals("") || args[1].equals("") || args[2].equals("")) {
            // If any of the arguments is empty, display an error message.
            String errorMessage = "Empty Arguments!";
            ExceptionHandler.handleException(null, errorMessage);
        }

        // Create a new ProjectManager instance with the provided arguments and run the program.
        ProjectManager projectManager = new ProjectManager(args[0], args[1], args[2]);
        projectManager.run();
        
    }
}
