package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.avtomat.Automaton;
import OOP2.proekt.f22621609.avtomat.State;
import OOP2.proekt.f22621609.avtomat.Transition;
import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Handles the printing of transitions for a given automaton ID.
 * This class implements the {@link FileHandler} interface to perform processing on file content.
 */
public class PrintTransitions implements FileHandler {
    private FileOpener fileOpener;
    private String automatonId;
    /**
     * Constructs a new PrintTransitions object with the specified FileOpener.
     *
     * @param fileOpener the FileOpener instance to retrieve file content
     */
    public PrintTransitions(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }
    /**
     * Sets the automaton ID to be processed.
     *
     * @param automatonId the ID of the automaton
     */
    public void setAutomatonId(String automatonId) {
        this.automatonId = automatonId;
    }
    /**
     * Retrieves the automaton ID.
     *
     * @return the automaton ID
     */
    public String getAutomatonId() {
        return automatonId;
    }
    /**
     * Processes the file content to print transitions for the specified automaton ID.
     * It extracts transitions from the file content based on the provided automaton ID
     * and prints them to the console.
     */
    @Override
    public void processing() {
        System.out.println("Automaton ID: " + automatonId); // Debug output
        StringBuilder fileContent = fileOpener.getFileContent();
        if (fileContent != null) {
            if (automatonId != null && !automatonId.isEmpty()) {
                Automaton automaton = parseAutomaton(fileContent.toString(), automatonId);
                if (automaton != null) {
                    List<Transition> transitions = automaton.getTransitions();
                    System.out.println("Transitions:");
                    for (Transition transition : transitions) {
                        System.out.println("From: " + transition.getFromState().getId() +
                                " To: " + transition.getToState().getId() +
                                " Symbol: " + transition.getInputSymbol());
                    }
                } else {
                    System.out.println("Failed to parse the automaton with ID: " + automatonId);
                }
            } else {
                System.out.println("Automaton ID is not set.");
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
        String automatonRegex = "<automaton\\s+id=\"" + automatonId + "\"(?:.|\\s)*?</automaton>";
        Pattern automatonPattern = Pattern.compile(automatonRegex);
        Matcher automatonMatcher = automatonPattern.matcher(fileContent);

        if (automatonMatcher.find()) {
            String automatonText = automatonMatcher.group(0);

            List<State> states = new ArrayList<>();
            List<String> alphabet = new ArrayList<>();
            List<Transition> transitions = new ArrayList<>();

            // Extract states
            Pattern statePattern = Pattern.compile("<state id=\"(\\w+)\" name=\"(\\w+)\"\\s*/>");
            Matcher stateMatcher = statePattern.matcher(automatonText);
            while (stateMatcher.find()) {
                String stateId = stateMatcher.group(1);
                String stateName = stateMatcher.group(2);
                states.add(new State(stateId, stateName));
            }

            // Extract transitions
            Pattern transitionPattern = Pattern.compile("<transition>\\s*<fromState>(\\w+)</fromState>\\s*<toState>(\\w+)</toState>\\s*<inputSymbol>(\\w+)</inputSymbol>\\s*</transition>");
            Matcher transitionMatcher = transitionPattern.matcher(automatonText);
            while (transitionMatcher.find()) {
                String fromStateId = transitionMatcher.group(1);
                String toStateId = transitionMatcher.group(2);
                String inputSymbol = transitionMatcher.group(3);
                transitions.add(new Transition(new State(fromStateId, ""), new State(toStateId, ""), inputSymbol));
                if (!alphabet.contains(inputSymbol)) {
                    alphabet.add(inputSymbol);
                }
            }

            return new Automaton(states, alphabet, transitions, null, null);
        }

        return null;
    }

}