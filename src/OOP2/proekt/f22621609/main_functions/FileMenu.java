package OOP2.proekt.f22621609.main_functions;

import OOP2.proekt.f22621609.FiniteAutomaton;
import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.secondary_functions.*;

import java.util.HashMap;
import java.util.Map;

public class FileMenu {
    private FiniteAutomaton automaton;
    private Map<String, FileHandler> commandMap = new HashMap<>();
    private StringBuilder fileContent = new StringBuilder();
    private String currentFileName = "";
    private Map<String, FiniteAutomaton> automatonMap = new HashMap<>();


    public void setAutomaton(FiniteAutomaton automaton) {
        this.automaton = automaton;
    }
    public FiniteAutomaton getAutomaton(String automatonId) {
        return automatonMap.get(automatonId); // Вземане на автомат по идентификатор
    }
    public FileMenu() {
        commandMap.put("close", new FileCloser(fileContent));
        commandMap.put("exit", new ExitProgram());
        commandMap.put("help", new FileHelper());
        commandMap.put("open", new FileOpener("", fileContent));
        commandMap.put("save", new FileSaver("", fileContent));
        commandMap.put("saveas", new FileSaverAs("", fileContent));
        commandMap.put("list", new ListMachines(new FileOpener("", fileContent)));
        commandMap.put("empty", new EmptyCommand(automaton));
        commandMap.put("deterministic", new DeterministicCommand(automaton));
        commandMap.put("recognize", new RecognizeCommand(automaton));
        commandMap.put("concat", new ConcatCommand(null, null)); // null за сега, ще се зададат по-късно

        // Add PrintTransitions instance to the command map
        FileOpener fileOpenerForPrint = new FileOpener("", fileContent);
        PrintTransitions printTransitions = new PrintTransitions(fileOpenerForPrint);
        commandMap.put("print", printTransitions);
        commandMap.put("saveauto", new SaveAutomaton(automatonMap, null, null));

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
        if (commandName.equals("empty")) {
            if (automaton != null) { // Check if automaton is not null
                EmptyCommand emptyCommand = new EmptyCommand(automaton);
                emptyCommand.processing();
            } else {
                System.out.println("Automaton not initialized.");
            }
            return;
        }

        if (commandName.equals("deterministic")) {
            // Проверка дали автоматът е инициализиран преди да се извика DeterministicCommand
            if (automaton == null) {
                System.out.println("No automaton is set. Please set an automaton first.");
                return;
            }
            // Извикване на DeterministicCommand със съответния автомат
            DeterministicCommand deterministicCommand = (DeterministicCommand) commandMap.get("deterministic");
            deterministicCommand.setAutomaton(automaton);
            deterministicCommand.setAutomatonId(argument);
            deterministicCommand.processing();
            return;
        }
        if (commandName.equals("recognize")) {
            RecognizeCommand recognizeCommand = (RecognizeCommand) commandMap.get("recognize");
            // Извикване на метод за разпознаване на дума от автомата
            recognizeCommand.processing();
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
                String transitionAutomatonId = printTransitions.getAutomatonId();
                if (transitionAutomatonId != null && transitionAutomatonId.equals(automatonId)){
                    return printTransitions;
                }
            }
        }
        return null;
    }
}