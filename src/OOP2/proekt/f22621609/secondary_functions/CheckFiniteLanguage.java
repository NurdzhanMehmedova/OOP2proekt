package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckFiniteLanguage implements FileHandler {
    private FileOpener fileOpener;

    public CheckFiniteLanguage(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }

    @Override
    public void processing() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the automaton to check: ");
        String automatonId = scanner.nextLine().trim();

        StringBuilder fileContent = fileOpener.getFileContent();
        if (fileContent != null) {
            if (!fileContent.toString().contains("<automaton id=\"" + automatonId + "\"")) {
                System.out.println("Automaton with ID '" + automatonId + "' not found in the file.");
                return;
            }

            if (isFiniteAutomaton(automatonId)) {
                System.out.println("The language recognized by the automaton is finite.");
            } else {
                System.out.println("The language recognized by the automaton is not finite.");
            }
        } else {
            System.out.println("No automaton found. Please open an automaton first.");
        }
    }

    private boolean isFiniteAutomaton(String automatonId) {
        StringBuilder fileContent = fileOpener.getFileContent();
        if (fileContent == null) {
            System.out.println("No automaton found. Please open an automaton first.");
            return false;
        }

        String automatonXml = fileContent.toString();

        // Define the pattern to search for the automaton ID
        String pattern = "<automaton id=\"" + automatonId + "\"";

        // Check if the pattern exists in the file content
        if (!automatonXml.contains(pattern)) {
            System.out.println("Automaton with ID '" + automatonId + "' not found in the file.");
            return false;
        }

        // Extract automaton content based on automatonId
        int startIndex = automatonXml.indexOf(pattern);
        int endIndex = automatonXml.indexOf("</automaton>", startIndex);
        if (endIndex == -1) {
            System.out.println("Error: Malformed automaton content.");
            return false;
        }

        String automatonContent = automatonXml.substring(startIndex, endIndex);

        // Extract initial and final states
        String initialState = extractTagContent(automatonContent, "initialState");
        String finalState = extractFinalState(automatonContent);

        // Check if the initial and final states are the same
        if (initialState != null && finalState != null && initialState.equals(finalState)) {
            System.out.println("Automaton with ID '" + automatonId + "' is infinite.");
            return false;
        } else {
            System.out.println("Automaton with ID '" + automatonId + "' is finite.");
            return true;
        }
    }

    private String extractFinalState(String automatonContent) {
        String finalState = null;
        Pattern pattern = Pattern.compile("<finalStates>.*?<state.*?id=\"(.*?)\"", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(automatonContent);
        if (matcher.find()) {
            finalState = matcher.group(1);
        }
        return finalState;
    }


    private String extractTagContent(String input, String tagName) {
        // Define the pattern to match the opening and closing tags
        Pattern pattern = Pattern.compile("<" + tagName + ">(.*?)</" + tagName + ">");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
