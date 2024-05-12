package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;

import java.io.*;

public class AutomatonFileSaver implements FileHandler {
    private StringBuilder fileContent;

    public AutomatonFileSaver(StringBuilder fileContent) {
        this.fileContent = fileContent;
    }

    @Override
    public void processing() {
        try (BufferedReader reader = new BufferedReader(new StringReader(fileContent.toString()))) {
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter automaton ID: ");
            String automatonId = userInputReader.readLine().trim();
            System.out.print("Enter filename to save: ");
            String filename = userInputReader.readLine().trim();

            saveAutomatonToFile(automatonId, filename);
        } catch (IOException e) {
            System.out.println("Error processing input: " + e.getMessage());
        }
    }

    public void saveAutomatonToFile(String automatonId, String filename) {
        try (BufferedReader reader = new BufferedReader(new StringReader(fileContent.toString())); // Update to read from StringBuilder
             BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            boolean foundAutomaton = false;
            boolean foundCorrectId = false;
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("<automaton id=\"" + automatonId + "\"")) {
                    foundAutomaton = true;
                }

                if (foundAutomaton) {
                    writer.write(line);
                    writer.newLine();

                    if (!foundCorrectId && line.contains("name=\"")) {
                        int nameStartIndex = line.indexOf("name=\"") + 6;
                        int nameEndIndex = line.indexOf("\"", nameStartIndex);
                        String automatonName = line.substring(nameStartIndex, nameEndIndex);
                        writer.write("    <name>" + automatonName + "</name>");
                        writer.newLine();
                        foundCorrectId = true;
                    }

                    if (line.trim().equals("</automaton>")) {
                        break;
                    }
                }
            }

            if (!foundAutomaton) {
                System.out.println("Automaton with ID '" + automatonId + "' not found in the file.");
            } else {
                System.out.println("Automaton with ID '" + automatonId + "' saved to " + filename);
            }
        } catch (IOException e) {
            System.out.println("Error saving the automaton: " + e.getMessage());
        }
    }
}