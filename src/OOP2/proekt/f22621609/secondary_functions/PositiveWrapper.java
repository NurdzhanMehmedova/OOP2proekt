package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * A class to create a positive wrapper for an automaton based on a given ID.
 * Implements the {@link FileHandler} interface to perform file handling operations.
 */
public class PositiveWrapper implements FileHandler {
    /**
     * Instance of {@link FileOpener} used to retrieve the content of the file.
     */
    private FileOpener fileOpener;
    /**
     * Constructs a new PositiveWrapper object with the specified FileOpener.
     *
     * @param fileOpener the FileOpener instance to use for accessing the file content
     */
    public PositiveWrapper(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }
    /**
     * Processes the content of the file to create a positive wrapper for an automaton.
     * Prompts the user to enter the ID of the automaton.
     * Checks if the provided automaton ID is valid.
     * If valid, creates a positive wrapper for the automaton and writes it to a file.
     */
    @Override
    public void processing() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the automaton to create a positive wrapper of: ");
        String automatonId = scanner.nextLine().trim();

        StringBuilder fileContent = fileOpener.getFileContent();
        if (fileContent != null) {
            if (isValidAutomatonId(fileContent.toString(), automatonId)) {
                String positiveWrapper = findPositiveWrapper(fileContent.toString(), automatonId);
                if (positiveWrapper != null) {
                    String fileName = "positive" + automatonId + ".txt";
                    try (FileWriter writer = new FileWriter(fileName)) {
                        writer.write(positiveWrapper);
                        System.out.println("Positive Wrapper for Automaton '" + automatonId + "' has been created in file: " + fileName);
                    } catch (IOException e) {
                        System.out.println("Failed to write positive wrapper to file.");
                    }
                } else {
                    System.out.println("Failed to create positive wrapper for Automaton '" + automatonId + "'.");
                }
            } else {
                System.out.println("Invalid automaton ID: '" + automatonId + "'.");
            }
        } else {
            System.out.println("No file content found.");
        }
    }
    /**
     * Checks if the provided automaton ID exists in the given file content.
     *
     * @param fileContent the content of the file containing automaton definitions
     * @param automatonId the ID of the automaton to check
     * @return true if the automaton ID is valid, false otherwise
     */
    private boolean isValidAutomatonId(String fileContent, String automatonId) {
        String automatonPattern = "<automaton\\s+id=\"" + automatonId + "\"[^>]*>";
        return fileContent.matches("(?s).*" + automatonPattern + ".*");
    }
    /**
     * Finds the automaton block corresponding to the provided ID and creates a positive wrapper for it.
     * The positive wrapper is created by swapping the fromState and toState in each transition of the automaton.
     *
     * @param fileContent the content of the file containing automaton definitions
     * @param automatonId the ID of the automaton to create a positive wrapper of
     * @return the positive wrapper XML string if successful, null otherwise
     */
    private String findPositiveWrapper(String fileContent, String automatonId) {
        String automatonStartPattern = "<automaton\\s+id=\"" + automatonId + "\"[^>]*>";
        String transitionsStartPattern = "<transitions>";
        String automatonEndPattern = "</automaton>";

        Pattern automatonStartRegex = Pattern.compile(automatonStartPattern);
        Matcher automatonStartMatcher = automatonStartRegex.matcher(fileContent);
        if (!automatonStartMatcher.find()) {
            return null;
        }
        int automatonStartIndex = automatonStartMatcher.start();
        int automatonEndIndex = fileContent.indexOf(automatonEndPattern, automatonStartIndex);

        String automatonXml = fileContent.substring(automatonStartIndex, automatonEndIndex + automatonEndPattern.length());

        int transitionsStartIndex = automatonXml.indexOf(transitionsStartPattern);
        if (transitionsStartIndex == -1) {
            return null;
        }

        StringBuilder positiveWrapperXml = new StringBuilder(automatonXml);
        String positiveWrapperTransitions = createPositiveWrapperTransitions(automatonXml.substring(transitionsStartIndex));
        if (positiveWrapperTransitions == null) {
            return null;
        }

        positiveWrapperXml.insert(
                transitionsStartIndex + transitionsStartPattern.length(),
                "<transition>\n"
                        + "    <fromState>qε</fromState>\n"
                        + "    <toState>" + extractInitialStates(automatonXml) + "</toState>\n"
                        + "    <inputSymbol>ε</inputSymbol>\n"
                        + "</transition>"
        );
        positiveWrapperXml.insert(
                transitionsStartIndex + transitionsStartPattern.length(),
                "\n" + positiveWrapperTransitions + "\n"
        );
        return positiveWrapperXml.toString();
    }
    /**
     * Extracts the initial state ID from the given automaton XML.
     *
     * @param automatonXml the XML string representing the automaton
     * @return the ID of the initial state if found, or null if not found
     */
    private String extractInitialStates(String automatonXml) {
        Pattern pattern = Pattern.compile("<initialState>(.*?)</initialState>");
        Matcher matcher = pattern.matcher(automatonXml);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
    /**
     * Creates positive wrapper transitions by swapping the fromState and toState in each transition XML string.
     *
     * @param transitionsXml the XML string representing the transitions of the automaton
     * @return the XML string representing the positive wrapper transitions
     */
    private String createPositiveWrapperTransitions(String transitionsXml) {
        String transitionPattern = "<transition>.*?</transition>";

        Pattern transitionRegex = Pattern.compile(transitionPattern, Pattern.DOTALL);
        Matcher transitionMatcher = transitionRegex.matcher(transitionsXml);

        StringBuilder positiveWrapperTransitions = new StringBuilder();

        while (transitionMatcher.find()) {
            String transitionXml = transitionMatcher.group();

            String fromState = extractTagContent(transitionXml, "fromState");
            String toState = extractTagContent(transitionXml, "toState");
            String inputSymbol = extractTagContent(transitionXml, "inputSymbol");

            positiveWrapperTransitions.append("<transition>\n");
            positiveWrapperTransitions.append("    <fromState>").append(toState).append("</fromState>\n");
            positiveWrapperTransitions.append("    <toState>").append(fromState).append("</toState>\n");
            positiveWrapperTransitions.append("    <inputSymbol>").append(inputSymbol).append("</inputSymbol>\n");
            positiveWrapperTransitions.append("</transition>\n");
        }

        return positiveWrapperTransitions.toString();
    }
    /**
     * Extracts the content of the specified XML tag from the input XML string.
     *
     * @param input    the XML string from which to extract the content
     * @param tagName  the name of the XML tag to extract the content from
     * @return the content of the specified XML tag if found, or null if not found
     */
    private String extractTagContent(String input, String tagName) {
        Pattern pattern = Pattern.compile("<" + tagName + ">(.*?)</" + tagName + ">");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
