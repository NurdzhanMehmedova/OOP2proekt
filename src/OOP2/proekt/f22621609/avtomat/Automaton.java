package OOP2.proekt.f22621609.avtomat;

import OOP2.proekt.f22621609.avtomat.State;
import OOP2.proekt.f22621609.avtomat.Transition;

import java.util.List;

public class Automaton {
    private List<State> states;
    private List<String> alphabet;
    private State initialState;
    private List<State> finalStates;
    private List<Transition> transitions;

    public Automaton(List<State> states, List<String> alphabet, State initialState, List<State> finalStates, List<Transition> transitions) {
        this.states = states;
        this.alphabet = alphabet;
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.transitions = transitions;
    }
    public List<Transition> getTransitions() {
        return this.transitions;
    }
    // Гетъри и сетъри
}