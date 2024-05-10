package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.FiniteAutomaton;
import OOP2.proekt.f22621609.avtomat.State;

import java.util.Map;
import java.util.Set;

public class PrintCommand {
    private FiniteAutomaton automaton;

    public PrintCommand(FiniteAutomaton automaton) {
        this.automaton = automaton;
    }

    public void execute() {
        System.out.println("States: " + automaton.getStates());
        System.out.println("Alphabet: " + automaton.getAlphabet());
        System.out.println("Initial State: " + automaton.getInitialState());
        System.out.println("Accepting States: " + automaton.getAcceptingStates());

        System.out.println("Transitions:");
        for (Map.Entry<State, Map<Character, Set<State>>> entry : automaton.getTransitions().entrySet()) {
            State currentState = entry.getKey();
            Map<Character, Set<State>> transitions = entry.getValue();

            for (Map.Entry<Character, Set<State>> transition : transitions.entrySet()) {
                char symbol = transition.getKey();
                Set<State> nextStates = transition.getValue();

                for (State nextState : nextStates) {
                    System.out.println(currentState + " --(" + symbol + ")--> " + nextState);
                }
            }
        }

        System.out.println("Epsilon Transitions:");
        for (Map.Entry<State, Set<State>> entry : automaton.getEpsilonTransitions().entrySet()) {
            State currentState = entry.getKey();
            Set<State> epsilonTransitions = entry.getValue();

            for (State nextState : epsilonTransitions) {
                System.out.println(currentState + " --(ε)--> " + nextState);
            }
        }
    }


}