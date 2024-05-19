package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * The {@code CheckEmptyLanguage} class implements the {@link FileHandler} interface
 * to check if the language of an automaton described in a file is empty.
 */
public class CheckEmptyLanguage implements FileHandler {
    private FileOpener fileOpener;
    /**
     * Constructs a {@code CheckEmptyLanguage} object with the specified {@link FileOpener}.
     *
     * @param fileOpener the {@code FileOpener} instance used to open files
     */
    public CheckEmptyLanguage(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }
    /**
     * Processes the input to check if the language of the automaton described in the file is empty.
     * Prompts the user to enter the ID of the automaton to check.
     */
    @Override
    public void processing() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the automaton to check: ");
        String automatonId = scanner.nextLine().trim();

        StringBuilder fileContent = fileOpener.getFileContent();
        if (fileContent != null) {
            // Check if the automaton ID exists in the file content
            if (!fileContent.toString().contains("<automaton id=\"" + automatonId + "\"")) {
                System.out.println("Automaton with ID '" + automatonId + "' not found in the file.");
                return;
            }

            if (isLanguageEmpty(fileContent.toString())) {
                System.out.println("The language of the automaton is empty.");
            } else {
                System.out.println("The language of the automaton is not empty.");
            }
        }
    }
    /**
     * Checks if the language of the automaton described in the file is empty.
     *
     * @param fileContent the content of the file containing automaton descriptions
     * @return {@code true} if the language is empty, {@code false} otherwise
     */

    private boolean isLanguageEmpty(String fileContent) {
        // Checking if there are any transitions
        if (!fileContent.contains("<transition>")) {
            return true; // No transitions found, language is empty
        }

        // Checking if there are any symbols in the alphabet
        Pattern symbolPattern = Pattern.compile("<symbol>(\\w+)</symbol>");
        Matcher symbolMatcher = symbolPattern.matcher(fileContent);
        return !symbolMatcher.find(); // Language is empty if no symbols found in the alphabet
    }
}