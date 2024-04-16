package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.FiniteAutomaton;
import OOP2.proekt.f22621609.State;

import java.util.*;

public class PositiveClosureCommand {
    private FiniteAutomaton automaton;

    public PositiveClosureCommand(FiniteAutomaton automaton) {
        this.automaton = automaton;
    }

    public void execute() {
        // Намиране на позитивна обвивка на автомата
        Set<State> positiveClosure = new HashSet<>();
        Queue<State> queue = new LinkedList<>();

        // Начално състояние на опашката
        queue.addAll(automaton.getEpsilonTransitions().getOrDefault(automaton.getInitialState(), Collections.emptySet()));
        positiveClosure.addAll(queue);

        // Обхождане на състоянията с епсилон преходи
        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            Set<State> epsilonTransitions = automaton.getEpsilonTransitions().getOrDefault(currentState, Collections.emptySet());
            for (State nextState : epsilonTransitions) {
                if (!positiveClosure.contains(nextState)) {
                    positiveClosure.add(nextState);
                    queue.add(nextState);
                }
            }
        }

        System.out.println("Positive closure of the automaton: " + positiveClosure);
    }
}
