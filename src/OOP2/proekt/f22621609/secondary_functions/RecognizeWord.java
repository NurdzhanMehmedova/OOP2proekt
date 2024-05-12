package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.avtomat.Automaton;
import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecognizeWord implements FileHandler {
    private FileOpener fileOpener;

    public RecognizeWord(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }

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

    private boolean recognizeInputSymbol(Automaton automaton, String inputSymbol) {
        return automaton.getInputSymbols().contains(inputSymbol);
    }
}