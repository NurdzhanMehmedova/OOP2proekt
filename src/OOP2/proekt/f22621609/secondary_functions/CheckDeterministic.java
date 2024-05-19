package OOP2.proekt.f22621609.secondary_functions;
import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * The {@code CheckDeterministic} class implements the {@link FileHandler} interface
 * to check if an automaton described in a file is deterministic.
 */
public class CheckDeterministic implements FileHandler {
    private FileOpener fileOpener;
    /**
     * Constructs a {@code CheckDeterministic} object with the specified {@link FileOpener}.
     *
     * @param fileOpener the {@code FileOpener} instance used to open files
     */
    public CheckDeterministic(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }
    /**
     * Processes the input to check if the automaton described in the file is deterministic.
     * Prompts the user to enter the ID of the automaton to check.
     */
    @Override
    public void processing() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the automaton to check: ");
        String automatonId = scanner.nextLine().trim();

        StringBuilder fileContent = fileOpener.getFileContent();
        if (fileContent != null) {
            if (isDeterministic(fileContent.toString(), automatonId)) {
                System.out.println("The automaton is deterministic.");
            } else {
                System.out.println("The automaton is not deterministic.");
            }
        }
    }
    /**
     * Checks if the automaton with the specified ID is deterministic.
     *
     * @param fileContent the content of the file containing automaton descriptions
     * @param automatonId the ID of the automaton to check
     * @return {@code true} if the automaton is deterministic, {@code false} otherwise
     */
    private boolean isDeterministic(String fileContent, String automatonId) {
        // Check if the automaton with the given ID exists in the file
        if (!fileContent.contains("<automaton id=\"" + automatonId + "\"")) {
            System.out.println("Automaton with ID '" + automatonId + "' not found in the file.");
            return false;
        }

        // Extract transitions for the specified automaton
        String automatonText = extractAutomatonText(fileContent, automatonId);

        // Extract transitions
        Pattern transitionPattern = Pattern.compile("<transition>\\s*<fromState>(\\w+)</fromState>\\s*<toState>(\\w+)</toState>\\s*<inputSymbol>(\\w+)</inputSymbol>\\s*</transition>");
        Matcher transitionMatcher = transitionPattern.matcher(automatonText);
        while (transitionMatcher.find()) {
            String fromStateId = transitionMatcher.group(1);
            String inputSymbol = transitionMatcher.group(3);

            int count = countTransitions(automatonText, fromStateId, inputSymbol);
            if (count > 1) {
                System.out.println("Non-deterministic transition found for state '" + fromStateId + "' and input symbol '" + inputSymbol + "'.");
                return false;
            }
        }

        return true;
    }
    /**
     * Extracts the text of the automaton with the specified ID from the file content.
     *
     * @param fileContent the content of the file containing automaton descriptions
     * @param automatonId the ID of the automaton to extract
     * @return the text of the automaton with the specified ID
     */
    private String extractAutomatonText(String fileContent, String automatonId) {
        int startIndex = fileContent.indexOf("<automaton id=\"" + automatonId + "\"");
        if (startIndex == -1) {
            return ""; // Automaton with the given ID not found
        }
        int endIndex = fileContent.indexOf("</automaton>", startIndex);
        return fileContent.substring(startIndex, endIndex);
    }
    /**
     * Counts the number of transitions with the specified from state ID and input symbol.
     *
     * @param automatonText the text of the automaton
     * @param stateId       the ID of the state to count transitions from
     * @param inputSymbol   the input symbol to count transitions for
     * @return the number of transitions with the specified from state ID and input symbol
     */
    private int countTransitions(String automatonText, String stateId, String inputSymbol) {
        Pattern transitionPattern = Pattern.compile("<transition>\\s*<fromState>" + stateId + "</fromState>\\s*<toState>\\w+</toState>\\s*<inputSymbol>" + inputSymbol + "</inputSymbol>\\s*</transition>");
        Matcher transitionMatcher = transitionPattern.matcher(automatonText);
        int count = 0;
        while (transitionMatcher.find()) {
            count++;
        }
        return count;
    }
}