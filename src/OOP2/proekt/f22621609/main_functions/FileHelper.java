package OOP2.proekt.f22621609.main_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;
/**
 * The FileHelper class implements the FileHandler interface to provide
 * help information for file-related commands. When the processing method
 * is invoked, it prints a list of supported commands.
 */
public class FileHelper implements FileHandler {
    /**
     * Invokes the help operation, printing a list of supported commands.
     */
    @Override
    public void processing() {
        printHelp();
    }
    /**
     * Prints a list of supported commands and their descriptions.
     */

    public void printHelp() {
        System.out.println("The following commands are supported:");
        System.out.println("open <file>\t- opens <file>");
        System.out.println("close\t\t- closes currently opened file");
        System.out.println("save\t\t- saves the currently open file");
        System.out.println("saveas <file>\t- saves the currently open file in <file>");
        System.out.println("help\t\t- prints this information");

        System.out.println("list\t\t- prints the id of machines");//1
        System.out.println("print\t\t-displays information about all transitions in the automaton");//2
        System.out.println("saveauto\t\t-saves an automaton to a file");//3
        System.out.println("checkempty\t\t-checks if the machine language is empty");//4
        System.out.println("checkdeterministic\t\t-checks whether an automaton is deterministic");//5
        System.out.println("recognize\t\t-checks if a word is in the machine language");//6
        System.out.println("un\t\t-finds the positive envelope of an automaton and creates a new automaton");//7
        System.out.println("checkfinite\t\t-checks whether the language of a given automaton is finite");//8
        System.out.println("concat\t\t-finds the concatenation of two automata and creates a new automata. Prints the ID of the new automaton");//9
        System.out.println("reg\t\t-Creates a new automaton according to a given regular expression (Cliny's theorem). Prints the ID of the new automaton");//10

        System.out.println("exit\t\t- exits the program");
    }
}