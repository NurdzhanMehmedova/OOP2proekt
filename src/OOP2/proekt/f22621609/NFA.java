package OOP2.proekt.f22621609;
import java.io.Serializable;
import java.util.*;

public class NFA implements Serializable {
    private Set<Integer> states;
    private Set<Character> alphabet;
    private Map<Integer, Map<Character, Set<Integer>>> transitions;
    private Integer startState;
    private Set<Integer> finalStates;

    public NFA() {
        states = new HashSet<>();
        alphabet = new HashSet<>();
        transitions = new HashMap<>();
        startState = null;
        finalStates = new HashSet<>();
    }

    public void addState(Integer state) {
        states.add(state);
    }

    public void setStartState(Integer state) {
        startState = state;
    }

    public void addFinalState(Integer state) {
        finalStates.add(state);
    }

    public void addTransition(Integer from, Character symbol, Integer to) {
        alphabet.add(symbol);
        transitions.computeIfAbsent(from, k -> new HashMap<>()).computeIfAbsent(symbol, k -> new HashSet<>()).add(to);
    }

    public boolean isDeterministic() {
        for (Map.Entry<Integer, Map<Character, Set<Integer>>> entry : transitions.entrySet()) {
            for (Map.Entry<Character, Set<Integer>> innerEntry : entry.getValue().entrySet()) {
                if (innerEntry.getValue().size() > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEmptyLanguage() {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startState);

        while (!queue.isEmpty()) {
            Integer currentState = queue.poll();
            if (finalStates.contains(currentState)) {
                return false;
            }
            visited.add(currentState);
            transitions.getOrDefault(currentState, new HashMap<>()).entrySet().stream()
                    .filter(entry -> entry.getKey() == null || !entry.getKey().equals('ε'))
                    .flatMap(entry -> entry.getValue().stream())
                    .filter(nextState -> !visited.contains(nextState))
                    .forEach(queue::add);
        }

        return true;
    }

    public void printTransitions() {
        for (Map.Entry<Integer, Map<Character, Set<Integer>>> entry : transitions.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Map.Entry<Character, Set<Integer>> innerEntry : entry.getValue().entrySet()) {
                System.out.print(innerEntry.getKey() + ": " + innerEntry.getValue() + ", ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "NFA{" +
                "states=" + states +
                ", alphabet=" + alphabet +
                ", transitions=" + transitions +
                ", startState=" + startState +
                ", finalStates=" + finalStates +
                '}';
    }

    public boolean isEmpty() {
        return false;
    }
}