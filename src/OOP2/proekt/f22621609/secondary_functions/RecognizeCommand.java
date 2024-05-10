package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.FiniteAutomaton;
import OOP2.proekt.f22621609.avtomat.State;
import OOP2.proekt.f22621609.contracts.FileHandler;

import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class RecognizeCommand implements FileHandler {
    private final FiniteAutomaton automaton;

    public RecognizeCommand(FiniteAutomaton automaton) {
        this.automaton = automaton;
    }

    public void processing() {
        // Логика за разпознаване на дума от автомата
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter word to recognize: ");
        String word = scanner.nextLine().trim();

        boolean isAccepted = recognizeWord(word);
        if (!isAccepted) {
            System.out.println("The word \"" + word + "\" is in the language of the automaton.");
        } else {
            System.out.println("The word \"" + word + "\" is not in the language of the automaton.");
        }
    }


    private boolean recognizeWord(String word) {
        // Проверка за валидност на автомата
        if (automaton == null) {
            //System.out.println("Automaton is not initialized.");
            return false;
        }

        State currentState = automaton.getInitialState();
        for (int i = 0; i < word.length(); i++) {
            char symbol = word.charAt(i);
            // Проверка дали символът принадлежи на азбуката на автомата
            if (!automaton.getAlphabet().contains(symbol)) {
                System.out.println("The word contains symbols not in the automaton's alphabet.");
                return false;
            }
            // Проверка дали има преход от текущото състояние по дадения символ
            Map<Character, Set<State>> transitionMap = automaton.getTransitions().getOrDefault(currentState, Collections.emptyMap());
            if (!transitionMap.containsKey(symbol)) {
                System.out.println("No transition from the current state using symbol '" + symbol + "'.");
                return false;
            }
            // Преход към следващото състояние
            currentState = transitionMap.get(symbol).iterator().next();
        }
        // Проверка дали последното състояние е крайно състояние
        if (automaton.getAcceptingStates().contains(currentState)) {
            return true;
        } else {
            System.out.println("The word does not end in an accepting state.");
            return false;
        }
    }
}