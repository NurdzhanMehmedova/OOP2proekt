package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * A class to list the automatons present in a given file.
 * Implements the {@link FileHandler} interface to perform file handling operations.
 */
public class ListMachines implements FileHandler {
    /**
     * Instance of {@link FileOpener} used to retrieve the content of the file.
     */
    private FileOpener fileOpener;
    /**
     * Constructs a new ListMachines object with the specified FileOpener.
     *
     * @param fileOpener the FileOpener instance to use for accessing the file content
     */
    public ListMachines(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }
    /**
     * Processes the content of the file to list the automatons present in it.
     * Uses regular expressions to extract information about the automatons.
     * Prints the ID and name of each automaton found.
     */
    @Override
    public void processing() {
        StringBuilder fileContent = fileOpener.getFileContent(); // Get the content of the file from FileOpener
        if (fileContent != null) {
            // Logic to extract information about the automatons
            Pattern automatonPattern = Pattern.compile("<automaton\\s+id=\"(\\w+)\"\\s+name=\"([^\"]+)\">");
            Matcher matcher = automatonPattern.matcher(fileContent);

            System.out.println("List of automatons:");
            while (matcher.find()) {
                String id = matcher.group(1);
                String name = matcher.group(2);
                System.out.println("ID: " + id + ", Name: " + name);
            }
        }
    }
}