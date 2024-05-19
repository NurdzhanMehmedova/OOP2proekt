package OOP2.proekt.f22621609.avtomat;

import java.util.ArrayList;
import java.util.List;

/**
 * The Automaton class represents a finite automaton with states, alphabet, transitions,
 * an initial state, and a set of final states.
 */
public class Automaton {
    private List<State> states;
    private List<String> alphabet;
    private List<Transition> transitions;
    private State initialState;
    private List<State> finalStates;
    private List<String> inputSymbols;
    /**
     * Constructs an Automaton with the specified states, alphabet, transitions,
     * initial state, and final states.
     *
     * @param states      the list of states in the automaton
     * @param alphabet    the list of symbols in the automaton's alphabet
     * @param transitions the list of transitions between states
     * @param initialState the initial state of the automaton
     * @param finalStates the list of final states in the automaton
     */
    public Automaton(List<State> states, List<String> alphabet, List<Transition> transitions, State initialState, List<State> finalStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    /**
     * Constructs an Automaton with the specified input symbols.
     *
     * @param inputSymbols the list of input symbols for the automaton
     */
    public Automaton(List<String> inputSymbols) {
        this.inputSymbols = inputSymbols;
    }

    /**
     * Returns the list of states in the automaton.
     *
     * @return the list of states
     */
    public List<State> getStates() {
        return states;
    }
    /**
     * Returns the alphabet of the automaton.
     *
     * @return the list of symbols in the alphabet
     */

    public List<String> getAlphabet() {
        return alphabet;
    }
    /**
     * Returns the list of transitions in the automaton.
     *
     * @return the list of transitions
     */

    public List<Transition> getTransitions() {
        return transitions;
    }
    /**
     * Returns the list of input symbols for the automaton.
     *
     * @return the list of input symbols
     */

    public List<String> getInputSymbols() {
        return inputSymbols;
    }
    /**
     * Returns the list of final states in the automaton.
     *
     * @return the list of final states
     */

    public List<State> getFinalStates() {
        return finalStates;
    }
    /**
     * Returns the initial state of the automaton.
     *
     * @return the initial state
     */

    public State getInitialState() {
        return initialState;
    }
}