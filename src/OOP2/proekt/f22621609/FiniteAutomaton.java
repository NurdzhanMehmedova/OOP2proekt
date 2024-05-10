package OOP2.proekt.f22621609;

import OOP2.proekt.f22621609.avtomat.State;

import java.io.*;
import java.util.*;

public class FiniteAutomaton implements Serializable {
    private String automatonId;
    private String automatonName;
    private Set<State> states;
    private Set<Character> alphabet;
    private State initialState;
    private Set<State> acceptingStates;
    private Map<State, Map<Character, Set<State>>> transitions;
    private Map<State, Set<State>> epsilonTransitions; // Ɛ-преходи

    public FiniteAutomaton() {
        states = new HashSet<>();
        alphabet = new HashSet<>();
        acceptingStates = new HashSet<>();
        transitions = new HashMap<>();
        epsilonTransitions = new HashMap<>();
    }

    public Set<State> getStates() {
        return states;
    }

    public Set<Character> getAlphabet() {
        return alphabet;
    }

    public State getInitialState() {
        return initialState;
    }

    public Set<State> getAcceptingStates() {
        return acceptingStates;
    }

    public Map<State, Map<Character, Set<State>>> getTransitions() {
        return transitions;
    }

    public Map<State, Set<State>> getEpsilonTransitions() {
        return epsilonTransitions;
    }

    public boolean isEmptyLanguage() {
        if (initialState == null) {
            // Ако няма начално състояние, езикът се счита за празен
            return true;
        }

        // Проверка дали има път от началното състояние до някое крайно състояние за всяка възможна дума в азбуката
        for (State acceptingState : acceptingStates) {
            if (hasPathToAcceptingState(initialState, acceptingState, new HashSet<>())) {
                // Ако намерим път от началното състояние до някое крайно състояние, езикът не е празен
                return false;
            }
        }

        // Не е намерен път от началното състояние до нито едно крайно състояние
        return true;
    }

    private boolean hasPathToAcceptingState(State currentState, State acceptingState, Set<State> visited) {
        // Проверка за прекъсване на цикъла, ако вече сме посетили текущото състояние
        if (visited.contains(currentState)) {
            return false;
        }

        // Добавяне на текущото състояние към списъка с посетени състояния
        visited.add(currentState);

        // Ако текущото състояние е крайно състояние, връщаме true
        if (acceptingState.equals(currentState)) {
            return true;
        }

        // Разглеждаме всички преходи от текущото състояние
        Map<Character, Set<State>> transitionMap = transitions.getOrDefault(currentState, Collections.emptyMap());
        for (Set<State> nextStates : transitionMap.values()) {
            for (State nextState : nextStates) {
                // Рекурсивно проверяваме дали има път от следващото състояние до крайното състояние
                if (hasPathToAcceptingState(nextState, acceptingState, visited)) {
                    return true;
                }
            }
        }

        // Не е намерен път от текущото състояние до крайното състояние
        return false;
    }

    public void addState(State state) {
        states.add(state);
    }

    public void addTransition(State from, char symbol, State to) {
        alphabet.add(symbol);
        transitions.computeIfAbsent(from, k -> new HashMap<>())
                .computeIfAbsent(symbol, k -> new HashSet<>())
                .add(to);
    }

    public void addEpsilonTransition(State from, State to) {
        epsilonTransitions.computeIfAbsent(from, k -> new HashSet<>()).add(to);
    }

    public void setInitialState(State state) {
        initialState = state;
    }

    public void addAcceptingState(State state) {
        acceptingStates.add(state);
    }

    public boolean isDeterministic() {
        // Проверка за ɛ-преходи
        if (!epsilonTransitions.isEmpty()) {
            return false; // Автоматът не е детерминиран ако има ɛ-преходи
        }

        // Проверка за всеки символ и състояние дали има точно едно преходно състояние
        for (State state : states) {
            for (char symbol : alphabet) {
                if (!transitions.containsKey(state) || !transitions.get(state).containsKey(symbol) || transitions.get(state).get(symbol).size() != 1) {
                    return false; // Автоматът не е детерминиран ако не съществува точно един преход за всеки символ и състояние
                }
            }
        }

        return true; // Автоматът е детерминиран
    }

    public void setAutomatonId(String automatonId) {
        this.automatonId = automatonId;
    }

    public void setAutomatonName(String automatonName) {
        this.automatonName = automatonName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<automaton id=\"").append(automatonId).append("\" name=\"").append(automatonName).append("\">\n");

        // Serialize states
        sb.append("\t<states>\n");
        for (State state : states) {
            sb.append("\t\t<state id=\"").append(state.getId()).append("\" name=\"").append(state.getName()).append("\"/>\n");
        }
        sb.append("\t</states>\n");

        // Serialize alphabet
        sb.append("\t<alphabet>\n");
        for (Character symbol : alphabet) {
            sb.append("\t\t<symbol>").append(symbol).append("</symbol>\n");
        }
        sb.append("\t</alphabet>\n");

        // Serialize initial state
        sb.append("\t<initialState>").append(initialState.getId()).append("</initialState>\n");

        // Serialize final states
        sb.append("\t<finalStates>\n");
        for (State state : acceptingStates) {
            sb.append("\t\t<state id=\"").append(state.getId()).append("\" name=\"").append(state.getName()).append("\"/>\n");
        }
        sb.append("\t</finalStates>\n");

        // Serialize transitions
        sb.append("\t<transitions>\n");
        for (Map.Entry<State, Map<Character, Set<State>>> entry : transitions.entrySet()) {
            State fromState = entry.getKey();
            Map<Character, Set<State>> transitionMap = entry.getValue();
            for (Map.Entry<Character, Set<State>> transitionEntry : transitionMap.entrySet()) {
                Character inputSymbol = transitionEntry.getKey();
                for (State toState : transitionEntry.getValue()) {
                    sb.append("\t\t<transition>\n");
                    sb.append("\t\t\t<fromState>").append(fromState.getId()).append("</fromState>\n");
                    sb.append("\t\t\t<toState>").append(toState.getId()).append("</toState>\n");
                    sb.append("\t\t\t<inputSymbol>").append(inputSymbol).append("</inputSymbol>\n");
                    sb.append("\t\t</transition>\n");
                }
            }
        }
        sb.append("\t</transitions>\n");

        sb.append("</automaton>");
        return sb.toString();
    }
}