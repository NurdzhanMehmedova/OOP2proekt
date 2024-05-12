package OOP2.proekt.f22621609.avtomat;

import java.util.List;

public class Automaton {
    private List<State> states;
    private List<String> alphabet;
    private List<Transition> transitions;
    private State initialState;
    private List<State> finalStates;
    private List<String> inputSymbols;

    public Automaton(List<State> states, List<String> alphabet, List<Transition> transitions, State initialState, List<State> finalStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public Automaton(List<String> inputSymbols) {
        this.inputSymbols = inputSymbols;
    }

    public List<State> getStates() {
        return states;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public List<String> getInputSymbols() {
        return inputSymbols;
    }

    public List<State> getFinalStates() {
        return finalStates;
    }

}