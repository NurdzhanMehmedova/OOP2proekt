package OOP2.proekt.f22621609.secondary_functions;
import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckDeterministic implements FileHandler {
    private FileOpener fileOpener;

    public CheckDeterministic(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }

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

    private String extractAutomatonText(String fileContent, String automatonId) {
        int startIndex = fileContent.indexOf("<automaton id=\"" + automatonId + "\"");
        if (startIndex == -1) {
            return ""; // Automaton with the given ID not found
        }
        int endIndex = fileContent.indexOf("</automaton>", startIndex);
        return fileContent.substring(startIndex, endIndex);
    }

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