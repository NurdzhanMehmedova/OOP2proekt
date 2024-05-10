package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.FiniteAutomaton;
import OOP2.proekt.f22621609.avtomat.Automaton;
import OOP2.proekt.f22621609.avtomat.State;
import OOP2.proekt.f22621609.avtomat.Transition;
import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintTransitions implements FileHandler {
    private FileOpener fileOpener;
    private String automatonId;

    public PrintTransitions(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }

    public PrintTransitions(FiniteAutomaton concatenatedAutomaton) {
    }

    public void setAutomatonId(String automatonId) {
        this.automatonId = automatonId;
    }

    public String getAutomatonId() {
        return automatonId;
    }

    @Override
    public void processing() {
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

    private Automaton parseAutomaton(String fileContent, String automatonId) {
        List<State> states = new ArrayList<>();
        List<String> alphabet = new ArrayList<>();
        State initialState = null;
        List<State> finalStates = new ArrayList<>();
        List<Transition> transitions = new ArrayList<>();

        String automatonRegex = "<automaton id=\"" + automatonId + "\"(?:.|\\s)*?</automaton>";
        String stateRegex = "<state id=\"(\\w+)\" name=\"(\\w+)\"\\s*/>";
        String transitionRegex = "<transition>\\s*<fromState>(\\w+)</fromState>\\s*<toState>(\\w+)</toState>\\s*<inputSymbol>(\\w+)</inputSymbol>\\s*</transition>";

        Pattern automatonPattern = Pattern.compile(automatonRegex);
        Matcher automatonMatcher = automatonPattern.matcher(fileContent);

        if (automatonMatcher.find()) {
            String automatonText = automatonMatcher.group(0);

            // Извличане на състоянията
            Pattern statePattern = Pattern.compile(stateRegex);
            Matcher stateMatcher = statePattern.matcher(automatonText);
            while (stateMatcher.find()) {
                String stateId = stateMatcher.group(1);
                String stateName = stateMatcher.group(2);
                states.add(new State(stateId, stateName));
            }

            // Извличане на преходите
            Pattern transitionPattern = Pattern.compile(transitionRegex);
            Matcher transitionMatcher = transitionPattern.matcher(automatonText);
            while (transitionMatcher.find()) {
                String fromStateId = transitionMatcher.group(1);
                String toStateId = transitionMatcher.group(2);
                String inputSymbol = transitionMatcher.group(3);
                State fromState = new State(fromStateId, "");
                State toState = new State(toStateId, "");
                transitions.add(new Transition(fromState, toState, inputSymbol));
                if (!alphabet.contains(inputSymbol)) {
                    alphabet.add(inputSymbol);
                }
            }

            // Създаване на обект Automaton
            return new Automaton(states, alphabet, initialState, finalStates, transitions);
        }

        return null;
    }
}