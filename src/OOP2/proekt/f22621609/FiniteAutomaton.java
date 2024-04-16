package OOP2.proekt.f22621609;

import java.io.*;
import java.util.*;

public class FiniteAutomaton implements Serializable {
    // Полета за представяне на автомата
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
        // Логика за проверка дали езикът на автомата е празен
        return false;
    }

    public boolean recognizeWord(String word) {
        // Логика за разпознаване на дума от автомата
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

    public void removeAcceptingState(State state) {
        acceptingStates.remove(state);
    }

    public void removeState(State state) {
        states.remove(state);
        transitions.remove(state);
        epsilonTransitions.remove(state);
        acceptingStates.remove(state);
    }

    public void removeTransition(State from, char symbol, State to) {
        if (transitions.containsKey(from) && transitions.get(from).containsKey(symbol)) {
            transitions.get(from).get(symbol).remove(to);
        }
    }

    public void removeEpsilonTransition(State from, State to) {
        if (epsilonTransitions.containsKey(from)) {
            epsilonTransitions.get(from).remove(to);
        }
    }

    // Методи за сериализация и десериализация на автомата
    public void serialize(String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(this);
            System.out.println("Automaton successfully serialized to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error serializing automaton to file: " + e.getMessage());
        }
    }

    public static FiniteAutomaton deserialize(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            FiniteAutomaton automaton = (FiniteAutomaton) inputStream.readObject();
            System.out.println("Automaton successfully deserialized from file: " + filename);
            return automaton;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing automaton from file: " + e.getMessage());
            return null;
        }
    }
}
