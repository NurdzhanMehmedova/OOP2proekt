package OOP2.proekt.f22621609.avtomat;

import OOP2.proekt.f22621609.avtomat.State;

public class Transition {
    private State fromState;
    private State toState;
    private String inputSymbol;

    public Transition(State fromState, State toState, String inputSymbol) {
        this.fromState = fromState;
        this.toState = toState;
        this.inputSymbol = inputSymbol;
    }

    public State getFromState() {
        return this.fromState;
    }

    public State getToState() {
        return this.toState;
    }

    public String getInputSymbol() {
        return this.inputSymbol;
    }
}