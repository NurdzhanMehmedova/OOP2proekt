package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.FiniteAutomaton;

public class EmptyCommand {
    private FiniteAutomaton automaton;

    public EmptyCommand(FiniteAutomaton automaton) {
        this.automaton = automaton;
    }

    public void execute() {
        boolean isEmpty = automaton.isEmptyLanguage();
        if (isEmpty) {
            System.out.println("The language of the automaton is empty.");
        } else {
            System.out.println("The language of the automaton is not empty.");
        }
    }

}
