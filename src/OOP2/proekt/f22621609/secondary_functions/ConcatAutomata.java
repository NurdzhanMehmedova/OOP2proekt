package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.avtomat.Automaton;
import OOP2.proekt.f22621609.avtomat.State;
import OOP2.proekt.f22621609.avtomat.Transition;
import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Concatenates multiple automata into a single automaton.
 */
public class ConcatAutomata implements FileHandler {
    /**
     * FileOpener instance to open and read automaton files.
     */
    private FileOpener fileOpener;
    /**
     * Constructs a ConcatAutomata object with the specified FileOpener instance.
     *
     * @param fileOpener the FileOpener instance to use for opening automaton files
     */
    public ConcatAutomata(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }
    /**
     * Processes the concatenation of automata.
     * Asks the user to enter the IDs of the automata to concatenate.
     * Concatenates the specified automata and saves the result to a new file.
     */
    @Override
    public void processing() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the IDs of the automata to concatinate (separated by space): ");
        String[] automatonIds = scanner.nextLine().trim().split(" ");

        StringBuilder fileContent = fileOpener.getFileContent();
        if (fileContent != null) {
            String concatinatedAutomatonId = concatAutomata(automatonIds, fileContent.toString());
            if (concatinatedAutomatonId != null) {
                System.out.println("Concatinated automaton ID: " + concatinatedAutomatonId);
            }
        }
    }

    /**
     * Concats the automata with the specified IDs extracted from the file content.
     *
     * @param automatonIds the IDs of the automata to concat
     * @param fileContent  the content of the file containing the automata
     * @return the ID of the concatinated automaton if successful, {@code null} otherwise
     */
    private String concatAutomata(String[] automatonIds, String fileContent) {
        List<Automaton> automata = new ArrayList<>();
        for (String id : automatonIds) {
            Automaton automaton = parseAutomaton(fileContent, id);
            if (automaton != null) {
                automata.add(automaton);
            } else {
                System.out.println("Automaton with ID '" + id + "' not found in the file.");
                return null;
            }
        }

        Automaton concatinatedAutomaton = concat(automata);
        String newAutomatonId = "Concat_" + String.join("_", automatonIds);

        saveAutomatonToFile(concatinatedAutomaton, newAutomatonId + ".xml", newAutomatonId);

        return newAutomatonId;
    }


    /**
     * Parses the automaton with the specified ID from the file content.
     *
     * @param fileContent the content of the file containing the automaton
     * @param automatonId the ID of the automaton to parse
     * @return the parsed Automaton object if found, {@code null} otherwise
     */
    private Automaton parseAutomaton(String fileContent, String automatonId) {
        // Find the start and end tags of the automaton block based on the automatonId
        String automatonStartTag = "<automaton id=\"" + automatonId + "\"";
        String automatonEndTag = "</automaton>";

        int startIndex = fileContent.indexOf(automatonStartTag);
        if (startIndex == -1) {
            // Automaton with the specified ID not found
            return null;
        }

        int endIndex = fileContent.indexOf(automatonEndTag, startIndex);
        if (endIndex == -1) {
            // End tag of the automaton not found
            return null;
        }

        // Extract the automaton block from the file content
        String automatonBlock = fileContent.substring(startIndex, endIndex + automatonEndTag.length());

        // Implement parsing logic to extract automaton information from the automaton block
        List<State> states = new ArrayList<>();
        List<String> alphabet = new ArrayList<>();
        List<Transition> transitions = new ArrayList<>();
        State initialState = null;
        List<State> finalStates = new ArrayList<>();

        Pattern statePattern = Pattern.compile("<state id=\"(.*?)\" name=\"(.*?)\"/>");
        Matcher stateMatcher = statePattern.matcher(automatonBlock);
        while (stateMatcher.find()) {
            String stateId = stateMatcher.group(1);
            String stateName = stateMatcher.group(2);
            State state = new State(stateId, stateName);
            states.add(state);
        }

        Pattern transitionPattern = Pattern.compile("<transition>\\s*<fromState>(.*?)</fromState>\\s*<toState>(.*?)</toState>\\s*<inputSymbol>(.*?)</inputSymbol>\\s*</transition>");
        Matcher transitionMatcher = transitionPattern.matcher(automatonBlock);
        while (transitionMatcher.find()) {
            String fromStateId = transitionMatcher.group(1);
            String toStateId = transitionMatcher.group(2);
            String inputSymbol = transitionMatcher.group(3);
            State fromState = findStateById(states, fromStateId);
            State toState = findStateById(states, toStateId);
            if (fromState != null && toState != null) {
                Transition transition = new Transition(fromState, toState, inputSymbol);
                transitions.add(transition);
            }
        }

        initialState = states.get(0);
        finalStates.add(states.get(states.size() - 1));

        // Create and return the Automaton object
        return new Automaton(states, alphabet, transitions, initialState, finalStates);
    }

    // Helper method to find a state by ID in a list of states
    private State findStateById(List<State> states, String id) {
        for (State state : states) {
            if (state.getId().equals(id)) {
                return state;
            }
        }
        return null;
    }


    /**
     * Concats the list of automata into a single automaton.
     *
     * @param automata the list of automata to concat
     * @return the concatinated Automaton object
     */
    private Automaton concat(List<Automaton> automata) {
        List<State> combinedStates = new ArrayList<>();
        List<String> combinedAlphabet = new ArrayList<>();
        List<Transition> combinedTransitions = new ArrayList<>();
        List<State> combinedFinalStates = new ArrayList<>();
        State combinedInitialState = null;

        // Combine states, alphabet, transitions, and final states of the input automata
        for (Automaton automaton : automata) {
            combinedStates.addAll(automaton.getStates());
            combinedAlphabet.addAll(automaton.getAlphabet());
            combinedTransitions.addAll(automaton.getTransitions());
            combinedFinalStates.addAll(automaton.getFinalStates());

            // Set the initial state of the concatinated automaton
            if (combinedInitialState == null) {
                combinedInitialState = automaton.getInitialState();
            } else {
                // Add transition from the combined initial state to the initial state of the current automaton
                State newInitialState = new State("combinedInitial", "Combined Initial State");
                combinedTransitions.add(new Transition(combinedInitialState, automaton.getInitialState(), ""));
                combinedInitialState = newInitialState;
            }
        }

        // Create and return the concatinated Automaton object
        return new Automaton(combinedStates, combinedAlphabet, combinedTransitions, combinedInitialState, combinedFinalStates);
    }


    /**
     * Saves the automaton to a file with the specified file name.
     *
     * @param automaton     the Automaton object to save
     * @param fileName      the name of the file to save to
     * @param newAutomatonId the ID of the concatinated automaton
     */
    private void saveAutomatonToFile(Automaton automaton, String fileName, String newAutomatonId) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Convert Automaton object to XML format and write to file
            writer.write(automatonToXML(automaton, newAutomatonId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts an Automaton object to XML format.
     *
     * @param automaton     the Automaton object to convert to XML
     * @param newAutomatonId the ID of the new concatinated automaton
     * @return the XML representation of the Automaton object
     */
    private String automatonToXML(Automaton automaton, String newAutomatonId) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<automaton id=\"").append(newAutomatonId).append("\" name=\"Concatinated Automaton\">\n");

        // Append states
        xmlBuilder.append("    <states>\n");
        for (State state : automaton.getStates()) {
            xmlBuilder.append("        <state id=\"").append(state.getId()).append("\" name=\"").append(state.getName()).append("\"/>\n");
        }
        xmlBuilder.append("    </states>\n");

        // Append alphabet
        xmlBuilder.append("    <alphabet>\n");
        for (String symbol : automaton.getAlphabet()) {
            xmlBuilder.append("        <symbol>").append(symbol).append("</symbol>\n");
        }
        xmlBuilder.append("    </alphabet>\n");

        // Append initial state
        xmlBuilder.append("    <initialState>").append(automaton.getInitialState().getId()).append("</initialState>\n");

        // Append final states
        xmlBuilder.append("    <finalStates>\n");
        for (State state : automaton.getFinalStates()) {
            xmlBuilder.append("        <state id=\"").append(state.getId()).append("\" name=\"").append(state.getName()).append("\"/>\n");
        }
        xmlBuilder.append("    </finalStates>\n");

        // Append transitions
        xmlBuilder.append("    <transitions>\n");
        for (Transition transition : automaton.getTransitions()) {
            xmlBuilder.append("        <transition>\n");
            xmlBuilder.append("            <fromState>").append(transition.getFromState().getId()).append("</fromState>\n");
            xmlBuilder.append("            <toState>").append(transition.getToState().getId()).append("</toState>\n");
            xmlBuilder.append("            <inputSymbol>").append(transition.getInputSymbol()).append("</inputSymbol>\n");
            xmlBuilder.append("        </transition>\n");
        }
        xmlBuilder.append("    </transitions>\n");

        xmlBuilder.append("</automaton>\n");

        return xmlBuilder.toString();
    }

}