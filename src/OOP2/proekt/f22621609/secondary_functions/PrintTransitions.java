package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.avtomat.Automaton;
import OOP2.proekt.f22621609.avtomat.State;
import OOP2.proekt.f22621609.avtomat.Transition;
import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.util.ArrayList;
import java.util.List;

public class PrintTransitions implements FileHandler {
    private FileOpener fileOpener;
    private String automatonId;

    public PrintTransitions(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
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

    // Implement a method to parse Automaton from file content for a specific ID
    private Automaton parseAutomaton(String fileContent, String automatonId) {
        List<State> states = new ArrayList<>();
        List<String> alphabet = new ArrayList<>();
        State initialState = null;
        List<State> finalStates = new ArrayList<>();
        List<Transition> transitions = new ArrayList<>();

        // Инициализираме променливи за преходите
        boolean transitionsSection = false;
        boolean transitionsStarted = false;

        String[] lines = fileContent.split("\n");
        for (String line : lines) {
            String trimmedLine = line.trim();
            if (trimmedLine.isEmpty()) {
                continue;
            }

            if (!transitionsSection) {
                if (trimmedLine.startsWith("<automaton id=\"" + automatonId + "\"")) {
                    transitionsSection = true; // Намерихме секцията с автомата
                }
            } else {
                if (!transitionsStarted) {
                    if (trimmedLine.startsWith("<states>")) {
                        continue; // Пропускаме тага <states>
                    } else if (trimmedLine.startsWith("<alphabet>")) {
                        continue; // Пропускаме тага <alphabet>
                    } else if (trimmedLine.startsWith("<initialState>")) {
                        String initialStateId = trimmedLine.substring(trimmedLine.indexOf(">") + 1, trimmedLine.indexOf("</"));
                        initialState = new State(initialStateId, "State " + initialStateId);
                    } else if (trimmedLine.startsWith("<finalStates>")) {
                        continue; // Пропускаме тага <finalStates>
                    } else if (trimmedLine.startsWith("<transitions>")) {
                        transitionsStarted = true; // Намерихме началото на секцията с преходите
                    }
                } else {
                    if (trimmedLine.startsWith("</transitions>")) {
                        break; // Край на секцията с преходите
                    } else {
                        // Извличаме информацията за преходите
                        if (trimmedLine.startsWith("<transition>")) {
                            String[] parts = trimmedLine.split("\">|<");
                            if (parts.length == 5) {
                                String fromStateId = parts[1].substring(parts[1].indexOf(">") + 1);
                                String inputSymbol = parts[3];
                                String toStateId = parts[4].substring(0, parts[4].indexOf("<"));
                                State fromState = new State(fromStateId, "State " + fromStateId);
                                State toState = new State(toStateId, "State " + toStateId);
                                transitions.add(new Transition(fromState, toState, inputSymbol));
                            }
                        }
                    }
                }
            }
        }

        return new Automaton(states, alphabet, initialState, finalStates, transitions);
    }
}
