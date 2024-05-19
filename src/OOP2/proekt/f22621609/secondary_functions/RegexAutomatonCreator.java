package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
/**
 * Handles the creation of an automaton from a regular expression.
 * This class implements the {@link FileHandler} interface to perform processing on file content.
 */
public class RegexAutomatonCreator implements FileHandler {
    private int stateCount;
    private Map<Character, Integer> symbolMap;
    private FileOpener fileOpener;
    /**
     * Constructs a new RegexAutomatonCreator object with the specified FileOpener.
     *
     * @param fileOpener the FileOpener instance to retrieve file content
     */
    public RegexAutomatonCreator(FileOpener fileOpener) {
        stateCount = 0;
        symbolMap = new HashMap<>();
    }
    /**
     * Creates a new state and returns its ID.
     *
     * @return the ID of the newly created state
     */
    private int createState() {
        return ++stateCount;
    }

    /**
     * Adds a transition between two states with the specified symbol.
     *
     * @param from   the ID of the source state
     * @param to     the ID of the destination state
     * @param symbol the transition symbol (null represents an epsilon transition)
     */
    private void addTransition(int from, int to, Character symbol) {
        if (symbol == null) return; // Null symbol represents an epsilon transition
        if (!symbolMap.containsKey(symbol)) {
            symbolMap.put(symbol, symbolMap.size());
        }
        int symbolIndex = symbolMap.get(symbol);
        // Print or store the transition information as needed
        System.out.println("Transition from state " + from + " to state " + to + " on symbol " + symbol);
    }
    /**
     * Processes the regular expression provided by the user to create an automaton.
     * Prompts the user to input the regular expression, then constructs the automaton
     * based on the given expression and saves it to a file.
     */
    @Override
    public void processing() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the regular expression: ");
        String regex = scanner.nextLine().trim(); // Read the regular expression from input

        Stack<Integer> stack = new Stack<>();
        char[] chars = regex.toCharArray();
        int startState = createState();
        int finalState = createState();
        int currentState = startState;
        stack.push(currentState);

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '(') {
                stack.push(currentState);
            } else if (c == ')') {
                currentState = stack.pop();
            } else if (c == '*') {
                int from = currentState;
                int to = createState();
                addTransition(from, to, null);
                addTransition(to, from, null);
                stack.push(to);
            } else if (c == '|') {
                currentState = stack.peek();
            } else {
                int from = currentState;
                int to = createState();
                addTransition(from, to, c);
                currentState = to;
            }
        }

        addTransition(startState, finalState, null);

        // Save the automaton to a file
        saveAutomatonToFile(finalState);

        // Display the ID of the automaton
        System.out.println("Automaton created successfully with ID: auto" + finalState);
    }
    /**
     * Saves the automaton to an XML file.
     *
     * @param finalState the ID of the final state of the automaton
     */
    private void saveAutomatonToFile(int finalState) {
        try (FileWriter writer = new FileWriter("automaton_auto" + finalState + ".xml")) {
            // Construct the XML content representing the automaton
            StringBuilder xmlContent = new StringBuilder();
            xmlContent.append("<automaton id=\"auto").append(finalState).append("\" name=\"Automaton ").append(finalState).append("\">\n");
            xmlContent.append("\t<name>Automaton ").append(finalState).append("</name>\n");
            xmlContent.append("\t<states>\n");
            for (int i = 0; i <= stateCount; i++) {
                xmlContent.append("\t\t<state id=\"q").append(i).append("\" name=\"State ").append(i).append("\"/>\n");
            }
            xmlContent.append("\t</states>\n");
            xmlContent.append("\t<alphabet>\n");
            for (char symbol : symbolMap.keySet()) {
                xmlContent.append("\t\t<symbol>").append(symbol).append("</symbol>\n");
            }
            xmlContent.append("\t</alphabet>\n");
            xmlContent.append("\t<initialState>q0</initialState>\n");
            xmlContent.append("\t<finalStates>\n");
            xmlContent.append("\t\t<state id=\"q").append(finalState).append("\" name=\"State ").append(finalState).append("\"/>\n");
            xmlContent.append("\t</finalStates>\n");
            xmlContent.append("\t<transitions>\n");
            xmlContent.append("\t</transitions>\n");
            xmlContent.append("</automaton>");

            // Write the XML content to the file
            writer.write(xmlContent.toString());
            writer.flush();
            System.out.println("Automaton saved successfully to automaton_auto" + finalState + ".xml");
        } catch (IOException e) {
            System.err.println("Error occurred while saving the automaton to file: " + e.getMessage());
        }
    }

}

