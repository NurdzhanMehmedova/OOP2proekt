package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.FiniteAutomaton;

public class RecognizeCommand {
    private FiniteAutomaton automaton;

    public RecognizeCommand(FiniteAutomaton automaton) {
        this.automaton = automaton;
    }

    public void execute(String word) {
        boolean isRecognized = automaton.recognizeWord(word);
        if (isRecognized) {
            System.out.println("The word \"" + word + "\" is recognized by the automaton.");
        } else {
            System.out.println("The word \"" + word + "\" is not recognized by the automaton.");
        }
    }

}
