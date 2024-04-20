package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.FiniteAutomaton;
import OOP2.proekt.f22621609.avtomat.State;

public class DeterministicCommand {
    private FiniteAutomaton automaton;

    public DeterministicCommand(FiniteAutomaton automaton) {
        this.automaton = automaton;
    }

    public void execute() {
        // Проверка дали автомата е детерминиран
        boolean isDeterministic = true;

        for (State state : automaton.getStates()) {
            for (char symbol : automaton.getAlphabet()) {
                if (automaton.getTransitions().containsKey(state) && automaton.getTransitions().get(state).containsKey(symbol)) {
                    if (automaton.getTransitions().get(state).get(symbol).size() > 1) {
                        isDeterministic = false;
                        break;
                    }
                }
            }
            if (!isDeterministic) {
                break;
            }
        }

        if (isDeterministic) {
            System.out.println("The automaton is deterministic.");
        } else {
            System.out.println("The automaton is not deterministic.");
        }
    }
}
