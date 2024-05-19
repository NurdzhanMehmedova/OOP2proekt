package OOP2.proekt.f22621609.avtomat;

import OOP2.proekt.f22621609.avtomat.State;

/**
 * The Transition class represents a transition in a finite automaton,
 * characterized by a starting state, an ending state, and an input symbol.
 */
public class Transition {
    private State fromState;
    private State toState;
    private String inputSymbol;

    /**
     * Constructs a Transition with the specified starting state, ending state, and input symbol.
     *
     * @param fromState   the state from which the transition starts
     * @param toState     the state to which the transition goes
     * @param inputSymbol the input symbol that triggers the transition
     */
    public Transition(State fromState, State toState, String inputSymbol) {
        this.fromState = fromState;
        this.toState = toState;
        this.inputSymbol = inputSymbol;
    }

    /**
     * Returns the state from which the transition starts.
     *
     * @return the starting state of the transition
     */
    public State getFromState() {
        return this.fromState;
    }

    /**
     * Returns the state to which the transition goes.
     *
     * @return the ending state of the transition
     */
    public State getToState() {
        return this.toState;
    }

    /**
     * Returns the input symbol that triggers the transition.
     *
     * @return the input symbol of the transition
     */
    public String getInputSymbol() {
        return this.inputSymbol;
    }
}
