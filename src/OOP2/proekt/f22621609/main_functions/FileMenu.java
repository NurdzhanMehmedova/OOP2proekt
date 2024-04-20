package OOP2.proekt.f22621609.main_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.secondary_functions.ListMachines;
import OOP2.proekt.f22621609.secondary_functions.PrintTransitions;

import java.util.HashMap;
import java.util.Map;

public class FileMenu {
    private Map<String, FileHandler> commandMap = new HashMap<>();
    private StringBuilder fileContent = new StringBuilder();
    private String currentFileName = "";

    public FileMenu() {
        commandMap.put("close", new FileCloser(fileContent));
        commandMap.put("exit", new ExitProgram());
        commandMap.put("help", new FileHelper());
        commandMap.put("open", new FileOpener("", fileContent));
        commandMap.put("save", new FileSaver("", fileContent));
        commandMap.put("saveas", new FileSaverAs("", fileContent));
        commandMap.put("list", new ListMachines(new FileOpener("", fileContent))); // Passing FileOpener instance

        // Add PrintTransitions instance to the command map
        FileOpener fileOpenerForPrint = new FileOpener("", fileContent);
        PrintTransitions printTransitions = new PrintTransitions(fileOpenerForPrint);
        commandMap.put("print", printTransitions);
    }

    public void executeCommand(String command) {
        String[] parts = command.split("\\s+", 2);

        String commandName = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : "";

        if (commandName.equals("print")) {
            // Find the automaton ID in the command
            String automatonId = argument.trim();
            // Search for the PrintTransitions instance associated with the automaton ID
            PrintTransitions printTransitions = findPrintTransitionsInstance(automatonId);
            // If found, process the transitions
            if (printTransitions != null) {
                printTransitions.processing();
            } else {
                System.out.println("No automaton found with ID: " + automatonId);
            }
            return;
        }

        FileHandler cmd = commandMap.get(commandName);
        if (cmd != null) {
            if (cmd instanceof FileOpener) {
                ((FileOpener) cmd).setFileName(argument);
                currentFileName = argument; // Set the current file name after opening
            } else if (cmd instanceof FileSaver) {
                if (currentFileName.isEmpty()) {
                    System.out.println("File is not open yet.");
                    return;
                }
                ((FileSaver) cmd).setFileName(currentFileName);
            } else if (cmd instanceof FileSaverAs) {
                ((FileSaverAs) cmd).setNewFileName(argument);
            }

            cmd.processing();
        } else {
            System.out.println("Unknown command: " + commandName);
        }
    }
    public Map<String, FileHandler> getCommandMap() {
        return commandMap;
    }

    private PrintTransitions findPrintTransitionsInstance(String automatonId) {
        // Search for the PrintTransitions instance associated with the automaton ID
        for (FileHandler handler : commandMap.values()) {
            if (handler instanceof PrintTransitions) {
                PrintTransitions printTransitions = (PrintTransitions) handler;
                if (printTransitions.getAutomatonId().equals(automatonId)) {
                    return printTransitions;
                }
            }
        }
        return null;
    }
}
