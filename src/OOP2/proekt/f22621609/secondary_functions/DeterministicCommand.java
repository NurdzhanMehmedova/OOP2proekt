package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.FiniteAutomaton;
import OOP2.proekt.f22621609.contracts.FileHandler;

public class DeterministicCommand implements FileHandler {
    private FiniteAutomaton automaton;
    private String automatonId;

    public DeterministicCommand(FiniteAutomaton automaton) {
        this.automaton = automaton;
    }

    public void setAutomatonId(String automatonId) {
        this.automatonId = automatonId;
    }
    public void setAutomaton(FiniteAutomaton automaton) {
        this.automaton = automaton;
    }

    @Override
    public void processing() {
        if (automaton.isDeterministic()) {
            System.out.println("Automaton with ID " + automatonId + " is deterministic.");
        } else {
            System.out.println("Automaton with ID " + automatonId + " is not deterministic.");
        }
    }
}

