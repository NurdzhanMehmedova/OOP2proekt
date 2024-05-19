package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.avtomat.Automaton;
import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Handles the recognition of a word by an automaton.
 * This class implements the {@link FileHandler} interface to perform processing on file content.
 */
public class RecognizeWord implements FileHandler {
    private FileOpener fileOpener;
    /**
     * Constructs a new RecognizeWord object with the specified FileOpener.
     *
     * @param fileOpener the FileOpener instance to retrieve file content
     */
    public RecognizeWord(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }
    /**
     * Processes the file content to recognize a word by a specified automaton.
     * Prompts the user to input the ID of the automaton and the word to recognize,
     * then checks if the word is in the language of the automaton and prints the result.
     */
    @Override
    public void processing() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the automaton to check: ");
        String automatonId = scanner.nextLine().trim();
        System.out.print("Enter the word to recognize: ");
        String inputSymbol = scanner.nextLine().trim();

        StringBuilder fileContent = fileOpener.getFileContent();
        if (fileContent != null) {
            Automaton automaton = parseAutomaton(fileContent.toString(), automatonId);
            if (automaton != null) {
                if (recognizeInputSymbol(automaton, inputSymbol)) {
                    System.out.println("The word '" + inputSymbol + "' is in the language of the automaton.");
                } else {
                    System.out.println("The word '" + inputSymbol + "' is NOT in the language of the automaton.");
                }
            } else {
                System.out.println("Automaton with ID '" + automatonId + "' not found in the file.");
            }
        }
    }
    /**
     * Parses the automaton from the given file content based on the provided automaton ID.
     *
     * @param fileContent the content of the file containing automaton definitions
     * @param automatonId the ID of the automaton to parse
     * @return the Automaton object representing the parsed automaton, or null if parsing fails
     */
    private Automaton parseAutomaton(String fileContent, String automatonId) {
        List<String> inputSymbols = new ArrayList<>();

        // Find the start and end indices of the automaton with the given ID
        int startIndex = fileContent.indexOf("<automaton id=\"" + automatonId + "\"");
        if (startIndex == -1) {
            return null; // Automaton with the given ID not found
        }
        int endIndex = fileContent.indexOf("</automaton>", startIndex);

        String automatonText = fileContent.substring(startIndex, endIndex);

        // Extract input symbols
        Pattern inputSymbolPattern = Pattern.compile("<inputSymbol>(\\w+)</inputSymbol>");
        Matcher inputSymbolMatcher = inputSymbolPattern.matcher(automatonText);
        while (inputSymbolMatcher.find()) {
            String inputSymbol = inputSymbolMatcher.group(1);
            inputSymbols.add(inputSymbol);
        }

        return new Automaton(inputSymbols);
    }
    /**
     * Checks if the input symbol is recognized by the provided automaton.
     *
     * @param automaton   the automaton to check
     * @param inputSymbol the input symbol to recognize
     * @return true if the input symbol is recognized, false otherwise
     */
    private boolean recognizeInputSymbol(Automaton automaton, String inputSymbol) {
        return automaton.getInputSymbols().contains(inputSymbol);
    }
}