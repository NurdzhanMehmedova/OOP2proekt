package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.avtomat.Automaton;
import OOP2.proekt.f22621609.avtomat.State;
import OOP2.proekt.f22621609.avtomat.Transition;
import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ConcatAutomata implements FileHandler {
    private FileOpener fileOpener;

    public ConcatAutomata(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }

    @Override
    public void processing() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the IDs of the automata to concatenate (separated by space): ");
        String[] automatonIds = scanner.nextLine().trim().split(" ");

        StringBuilder fileContent = fileOpener.getFileContent();
        if (fileContent != null) {
            String concatenatedAutomatonId = concatenateAutomata(automatonIds, fileContent.toString());
            if (concatenatedAutomatonId != null) {
                System.out.println("Concatenated automaton ID: " + concatenatedAutomatonId);
            }
        }
    }

    private String concatenateAutomata(String[] automatonIds, String fileContent) {
        List<Automaton> automata = new ArrayList<>();
        for (String id : automatonIds) {
            Automaton automaton = parseAutomaton(fileContent, id);
            if (automaton != null) {
                automata.add(automaton);
            } else {
                System.out.println("Automaton with ID '" + id + "' not found in the file.");
                return null;
            }
        }

        Automaton combinedAutomaton = combineAutomata(automata);
        String newAutomatonId = String.join("_", automatonIds);

        saveAutomatonToFile(combinedAutomaton, newAutomatonId + ".xml", newAutomatonId);

        return newAutomatonId;
    }

    private Automaton parseAutomaton(String fileContent, String automatonId) {
        String automatonStartTag = "<automaton id=\"" + automatonId + "\"";
        String automatonEndTag = "</automaton>";

        int startIndex = fileContent.indexOf(automatonStartTag);
        if (startIndex == -1) {
            return null;
        }

        int endIndex = fileContent.indexOf(automatonEndTag, startIndex);
        if (endIndex == -1) {
            return null;
        }

        String automatonBlock = fileContent.substring(startIndex, endIndex + automatonEndTag.length());

        State q1 = new State("q1", "State 1");
        State q2 = new State("q2", "State 2");
        List<State> states = Arrays.asList(q1, q2);
        List<String> alphabet = Arrays.asList("a", "b");
        List<Transition> transitions = Arrays.asList(new Transition(q1, q2, "a"));
        List<State> finalStates = Arrays.asList(q2);

        return new Automaton(states, alphabet, transitions, q1, finalStates);
    }

    private Automaton combineAutomata(List<Automaton> automata) {
        List<State> combinedStates = new ArrayList<>();
        List<String> combinedAlphabet = new ArrayList<>();
        List<Transition> combinedTransitions = new ArrayList<>();
        List<State> combinedFinalStates = new ArrayList<>();
        State combinedInitialState = null;

        for (Automaton automaton : automata) {
            combinedStates.addAll(automaton.getStates());
            combinedAlphabet.addAll(automaton.getAlphabet());
            combinedTransitions.addAll(automaton.getTransitions());
            combinedFinalStates.addAll(automaton.getFinalStates());

            if (combinedInitialState == null) {
                combinedInitialState = automaton.getInitialState();
            } else {
                State newInitialState = new State("combinedInitial", "Combined Initial State");
                combinedTransitions.add(new Transition(combinedInitialState, automaton.getInitialState(), ""));
                combinedInitialState = newInitialState;
            }
        }

        return new Automaton(combinedStates, combinedAlphabet, combinedTransitions, combinedInitialState, combinedFinalStates);
    }

    private void saveAutomatonToFile(Automaton automaton, String fileName, String newAutomatonId) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(automatonToXML(automaton, newAutomatonId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String automatonToXML(Automaton automaton, String newAutomatonId) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<automaton id=\"").append(newAutomatonId).append("\" name=\"Concatenated Automaton\">\n");
        xmlBuilder.append("    <name>Concatenated Automaton</name>\n");
        xmlBuilder.append("    <states>\n");
        for (State state : automaton.getStates()) {
            xmlBuilder.append("        <state id=\"").append(state.getId()).append("\" name=\"").append(state.getName()).append("\"/>\n");
        }
        xmlBuilder.append("    </states>\n");
        xmlBuilder.append("    <alphabet>\n");
        for (String symbol : automaton.getAlphabet()) {
            xmlBuilder.append("        <symbol>").append(symbol).append("</symbol>\n");
        }
        xmlBuilder.append("    </alphabet>\n");
        xmlBuilder.append("    <initialState>").append(automaton.getInitialState().getId()).append("</initialState>\n");
        xmlBuilder.append("    <finalStates>\n");
        for (State state : automaton.getFinalStates()) {
            xmlBuilder.append("        <state id=\"").append(state.getId()).append("\" name=\"").append(state.getName()).append("\"/>\n");
        }
        xmlBuilder.append("    </finalStates>\n");
        xmlBuilder.append("    <transitions>\n");
        for (Transition transition : automaton.getTransitions()) {
            xmlBuilder.append("        <transition>\n");
            xmlBuilder.append("            <fromState>").append(transition.getFromState().getId()).append("</fromState>\n");
            xmlBuilder.append("            <toState>").append(transition.getToState().getId()).append("</toState>\n");
            xmlBuilder.append("            <inputSymbol>").append(transition.getInputSymbol()).append("</inputSymbol>\n");
            xmlBuilder.append("        </transition>\n");
        }
        xmlBuilder.append("    </transitions>\n");
        xmlBuilder.append("</automaton>\n");
        return xmlBuilder.toString();
    }
}
