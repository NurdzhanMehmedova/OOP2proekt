package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.FiniteAutomaton;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class SaveAutomaton implements FileHandler {
    private String automatonId;
    private String filename;
    private FiniteAutomaton automaton;
    private Map<String, FiniteAutomaton> automatonMap;

    public SaveAutomaton(Map<String, FiniteAutomaton> automatonMap, String automatonId, String filename) {
        this.automatonMap = automatonMap;
        this.automatonId = automatonId;
        this.filename = filename;
        if (automatonMap != null && automatonId != null) {
            this.automaton = automatonMap.get(automatonId);
        }
    }

    @Override
    public void processing() {
        if (automaton == null) {
            System.out.println("Automaton not found with ID: " + automatonId);
            return;
        }

        if (filename == null) {
            System.out.println("Filename not provided.");
            return;
        }

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(automaton.toString()); // Assuming your FiniteAutomaton class has a toString() method
            System.out.println("Automaton saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving the automaton: " + e.getMessage());
        }
    }
}
