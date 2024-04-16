package OOP2.proekt.f22621609.secondary_functions;
import OOP2.proekt.f22621609.FiniteAutomaton;
import OOP2.proekt.f22621609.State;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class SaveCommand {
    private FiniteAutomaton automaton;

    public SaveCommand(FiniteAutomaton automaton) {
        this.automaton = automaton;
    }

    public void execute(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Генериране на уникален идентификатор за автомата
            String automatonId = UUID.randomUUID().toString();

            writer.write("Automaton ID: " + automatonId + "\n");
            writer.write("States: " + automaton.getStates() + "\n");
            writer.write("Alphabet: " + automaton.getAlphabet() + "\n");
            writer.write("Initial State: " + automaton.getInitialState() + "\n");
            writer.write("Accepting States: " + automaton.getAcceptingStates() + "\n");

            writer.write("Transitions:\n");
            for (Map.Entry<State, Map<Character, Set<State>>> entry : automaton.getTransitions().entrySet()) {
                State currentState = entry.getKey();
                Map<Character, Set<State>> transitions = entry.getValue();

                for (Map.Entry<Character, Set<State>> transition : transitions.entrySet()) {
                    char symbol = transition.getKey();
                    Set<State> nextStates = transition.getValue();

                    for (State nextState : nextStates) {
                        writer.write(currentState + " --(" + symbol + ")--> " + nextState + "\n");
                    }
                }
            }

            writer.write("Epsilon Transitions:\n");
            for (Map.Entry<State, Set<State>> entry : automaton.getEpsilonTransitions().entrySet()) {
                State currentState = entry.getKey();
                Set<State> epsilonTransitions = entry.getValue();

                for (State nextState : epsilonTransitions) {
                    writer.write(currentState + " --(ε)--> " + nextState + "\n");
                }
            }

            System.out.println("Automaton successfully saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving automaton to file: " + e.getMessage());
        }
    }
}
