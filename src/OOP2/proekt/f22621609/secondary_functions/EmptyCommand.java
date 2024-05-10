package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.FiniteAutomaton;
import OOP2.proekt.f22621609.contracts.FileHandler;

public class EmptyCommand implements FileHandler {
    private FiniteAutomaton automaton;

    public EmptyCommand(FiniteAutomaton automaton) {
        this.automaton = automaton;
    }

    @Override
    public void processing() {
        boolean isEmpty = automaton.isEmptyLanguage();
        if (isEmpty) {
            System.out.println("The language of the automaton is empty.");
        } else {
            System.out.println("The language of the automaton is not empty.");
        }
    }
}