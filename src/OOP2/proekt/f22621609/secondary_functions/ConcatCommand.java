package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.FiniteAutomaton;
import OOP2.proekt.f22621609.avtomat.State;
import OOP2.proekt.f22621609.contracts.FileHandler;

import java.util.Map;
import java.util.Set;

public class ConcatCommand implements FileHandler {
    private FiniteAutomaton automaton1;
    private FiniteAutomaton automaton2;

    public ConcatCommand(FiniteAutomaton automaton1, FiniteAutomaton automaton2) {
        this.automaton1 = automaton1;
        this.automaton2 = automaton2;
    }
    public void setAutomaton1(FiniteAutomaton automaton1) {
        this.automaton1 = automaton1;
    }

    public void setAutomaton2(FiniteAutomaton automaton2) {
        this.automaton2 = automaton2;
    }

    @Override
    public void processing() {
        // Намиране на конкатенацията на двата автомата
        FiniteAutomaton concatenatedAutomaton = new FiniteAutomaton();

        // Копиране на състоянията от първия автомат
        for (State state : automaton1.getStates()) {
            concatenatedAutomaton.addState(state);
        }

        // Копиране на състоянията от втория автомат, като се променят ID-тата за уникалност
        for (State state : automaton2.getStates()) {
            State newState = new State(state.getName(),state.getId() + "_2");
            concatenatedAutomaton.addState(newState);
        }

        // Копиране на преходите от първия автомат
        for (Map.Entry<State, Map<Character, Set<State>>> entry : automaton1.getTransitions().entrySet()) {
            State currentState = entry.getKey();
            Map<Character, Set<State>> transitions = entry.getValue();

            for (Map.Entry<Character, Set<State>> transition : transitions.entrySet()) {
                char symbol = transition.getKey();
                Set<State> nextStates = transition.getValue();

                for (State nextState : nextStates) {
                    concatenatedAutomaton.addTransition(currentState, symbol, nextState);
                }
            }
        }

        // Копиране на преходите от втория автомат, като променяме състоянията с новите ID-та
        for (Map.Entry<State, Map<Character, Set<State>>> entry : automaton2.getTransitions().entrySet()) {
            State currentState = entry.getKey();
            Map<Character, Set<State>> transitions = entry.getValue();

            for (Map.Entry<Character, Set<State>> transition : transitions.entrySet()) {
                char symbol = transition.getKey();
                Set<State> nextStates = transition.getValue();

                for (State nextState : nextStates) {
                    State newCurrentState = new State(currentState.getName(), currentState.getId() + "_2");
                    State newNextState = new State(nextState.getName(), nextState.getId() + "_2");
                    concatenatedAutomaton.addTransition(newCurrentState, symbol, newNextState);
                }

            }
        }

        // Задаване на начално състояние
        concatenatedAutomaton.setInitialState(automaton1.getInitialState());

        // Добавяне на преходи от началното състояние на първия автомат към началните състояния на втория
        for (State state : automaton1.getAcceptingStates()) {
            State newState = new State(state.getName(), state.getId() + "_2");
            concatenatedAutomaton.addEpsilonTransition(newState, automaton2.getInitialState());
        }


        // Задаване на новите приемащи състояния
        for (State state : automaton2.getAcceptingStates()) {
            State newState = new State(state.getName(), state.getId() + "_2");
            concatenatedAutomaton.addAcceptingState(newState);
        }

        // Показване на информация за новия автомат
        System.out.println("Concatenated Automaton:");
        PrintTransitions printCommand = new PrintTransitions(concatenatedAutomaton);
        printCommand.processing();
    }
}
