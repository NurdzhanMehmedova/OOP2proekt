package OOP2.proekt.f22621609.main_functions;

/*import OOP2.proekt.f22621609.avtomat.Automaton;
import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.secondary_functions.*;

import java.util.HashMap;
import java.util.Map;

public class FileMenu {
    private Map<String, FileHandler> commandMap = new HashMap<>();
    private StringBuilder fileContent = new StringBuilder();
    private String currentFileName = "";
    private Automaton automaton; // Declare Automaton instance
    private AutomatonFileSaver automatonFileSaver; // Declare AutomatonFileSaver instance
    private CheckEmptyLanguage checkEmptyLanguage; // Declare CheckEmptyLanguage instance
    private CheckDeterministic checkDeterministic; // Declare CheckDeterministic instance
    private RecognizeWord recognizeWord; // Declare RecognizeWord instance
    private CheckFiniteLanguage checkFiniteLanguage;

    public FileMenu() {
        commandMap.put("close", new FileCloser(fileContent));
        commandMap.put("exit", new ExitProgram());
        commandMap.put("help", new FileHelper());
        commandMap.put("open", new FileOpener("", fileContent));
        commandMap.put("save", new FileSaver("", fileContent));
        commandMap.put("saveas", new FileSaverAs("", fileContent));
        commandMap.put("list", new ListMachines(new FileOpener("", fileContent)));


        // Add PrintTransitions instance to the command map
        FileOpener fileOpenerForPrint = new FileOpener("", fileContent);
        PrintTransitions printTransitions = new PrintTransitions(fileOpenerForPrint);
        commandMap.put("print", printTransitions); // 2

        // Initialize AutomatonFileSaver instance
        automatonFileSaver = new AutomatonFileSaver(fileContent); // Initialize AutomatonFileSaver
        commandMap.put("saveauto", automatonFileSaver); // Add AutomatonFileSaver instance to command map

        // Initialize CheckEmptyLanguage instance
        FileOpener fileOpenerForCheckEmpty = new FileOpener("", fileContent);
        checkEmptyLanguage = new CheckEmptyLanguage(fileOpenerForCheckEmpty);
        commandMap.put("checkempty", checkEmptyLanguage); // Add CheckEmptyLanguage instance to command map

        // Initialize CheckDeterministic instance
        FileOpener fileOpenerForCheckDeterministic = new FileOpener("", fileContent);
        checkDeterministic = new CheckDeterministic(fileOpenerForCheckDeterministic);
        commandMap.put("checkdeterministic", checkDeterministic); // Add CheckDeterministic instance to command map

        // Initialize RecognizeWord instance
        FileOpener fileOpenerForRecognizeWord = new FileOpener("", fileContent);
        recognizeWord = new RecognizeWord(fileOpenerForRecognizeWord);
        commandMap.put("recognize", recognizeWord); // Add RecognizeWord instance to command map

        // Initialize CheckFiniteLanguage instance
        FileOpener fileOpenerForCheckFinite = new FileOpener("", fileContent);
        this.checkFiniteLanguage = new CheckFiniteLanguage(fileOpenerForCheckFinite);
        commandMap.put("checkfinite", checkFiniteLanguage); // Add CheckFiniteLanguage instance to command map
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
        if (commandName.equals("saveauto")) {
            automatonFileSaver.processing(); // Call the processing method of AutomatonFileSaver
            return;
        }
        if (commandName.equals("checkempty")) {
            checkEmptyLanguage.processing(); // Call the processing method of CheckEmptyLanguage
            return;
        }
        if (commandName.equals("checkdeterministic")) {
            checkDeterministic.processing(); // Call the processing method of CheckDeterministic
            return;
        }
        if (commandName.equals("recognize")) {
            recognizeWord.processing(); // Call the processing method of RecognizeWord
            return;
        }
        if (commandName.equals("checkfinite")) {
            checkFiniteLanguage.processing(); // Call the processing method of CheckFiniteLanguage
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
        for (FileHandler handler : commandMap.values()) {
            if (handler instanceof PrintTransitions) {
                PrintTransitions printTransitions = (PrintTransitions) handler;
                String transitionAutomatonId = printTransitions.getAutomatonId();
                if (transitionAutomatonId != null && transitionAutomatonId.equals(automatonId)) {
                    return printTransitions;
                }
            }
        }
        return null;
    }
}*/
import OOP2.proekt.f22621609.avtomat.Automaton;
import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.secondary_functions.*;

import java.util.HashMap;
import java.util.Map;

public class FileMenu {
    private Map<String, FileHandler> commandMap = new HashMap<>();
    private StringBuilder fileContent = new StringBuilder();
    private String currentFileName = "";
    private Automaton automaton;
    private AutomatonFileSaver automatonFileSaver;
    private CheckEmptyLanguage checkEmptyLanguage;
    private CheckDeterministic checkDeterministic;
    private RecognizeWord recognizeWord;
    private CheckFiniteLanguage checkFiniteLanguage;
    private PositiveWrapper positiveWrapper;
    private RegexAutomatonCreator regexAutomatonCreator;
    private ConcatAutomata concatAutomaton;

    public FileMenu() {
        commandMap.put("close", new FileCloser(fileContent));
        commandMap.put("exit", new ExitProgram());
        commandMap.put("help", new FileHelper());
        commandMap.put("open", new FileOpener("", fileContent));
        commandMap.put("save", new FileSaver("", fileContent));
        commandMap.put("saveas", new FileSaverAs("", fileContent));
        commandMap.put("list", new ListMachines(new FileOpener("", fileContent)));


        // Add PrintTransitions instance to the command map
        FileOpener fileOpenerForPrint = new FileOpener("", fileContent);
        PrintTransitions printTransitions = new PrintTransitions(fileOpenerForPrint);
        commandMap.put("print", printTransitions);

        // Initialize AutomatonFileSaver instance
        automatonFileSaver = new AutomatonFileSaver(fileContent);
        commandMap.put("saveauto", automatonFileSaver);

        // Initialize CheckEmptyLanguage instance
        FileOpener fileOpenerForCheckEmpty = new FileOpener("", fileContent);
        checkEmptyLanguage = new CheckEmptyLanguage(fileOpenerForCheckEmpty);
        commandMap.put("checkempty", checkEmptyLanguage);

        // Initialize CheckDeterministic instance
        FileOpener fileOpenerForCheckDeterministic = new FileOpener("", fileContent);
        checkDeterministic = new CheckDeterministic(fileOpenerForCheckDeterministic);
        commandMap.put("checkdeterministic", checkDeterministic);

        // Initialize RecognizeWord instance
        FileOpener fileOpenerForRecognizeWord = new FileOpener("", fileContent);
        recognizeWord = new RecognizeWord(fileOpenerForRecognizeWord);
        commandMap.put("recognize", recognizeWord);

        // Initialize CheckFiniteLanguage instance
        FileOpener fileOpenerForCheckFinite = new FileOpener("", fileContent);
        this.checkFiniteLanguage = new CheckFiniteLanguage(fileOpenerForCheckFinite);
        commandMap.put("checkfinite", checkFiniteLanguage);

        FileOpener fileOpenerForPositiveWrapper = new FileOpener("", fileContent);
        positiveWrapper = new PositiveWrapper(fileOpenerForPositiveWrapper);
        commandMap.put("un", positiveWrapper);

        FileOpener fileOpenerForRegexWrapper = new FileOpener("", fileContent);
        regexAutomatonCreator = new RegexAutomatonCreator(fileOpenerForRegexWrapper);
        commandMap.put("reg",regexAutomatonCreator);

        FileOpener fileOpenerForConcat = new FileOpener("", fileContent);
        concatAutomaton = new ConcatAutomata(fileOpenerForConcat);
        commandMap.put("concat",concatAutomaton);
    }

    public void executeCommand(String command) {
        String[] parts = command.split("\\s+", 2);
        String commandName = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : "";

        if (commandName.equals("print")) {
            String automatonId = argument.trim();
            PrintTransitions printTransitions = findPrintTransitionsInstance(automatonId);
            if (printTransitions != null) {
                printTransitions.processing();
            } else {
                System.out.println("No automaton found with ID: " + automatonId);
            }
            return;
        }
        if (commandName.equals("saveauto")) {
            automatonFileSaver.processing();
            return;
        }
        if (commandName.equals("checkempty")) {
            checkEmptyLanguage.processing();
            return;
        }
        if (commandName.equals("checkdeterministic")) {
            checkDeterministic.processing();
            return;
        }
        if (commandName.equals("recognize")) {
            recognizeWord.processing();
            return;
        }
        if (commandName.equals("checkfinite")) {
            checkFiniteLanguage.processing();
            return;
        }
        if (commandName.equals("un")) {
            positiveWrapper.processing();
            return;
        }
        if (commandName.equals("reg")){
            regexAutomatonCreator.processing();
            return;
        }
        if (commandName.equals("concat")){
            concatAutomaton.processing();
            return;
        }

        FileHandler cmd = commandMap.get(commandName);
        if (cmd != null) {
            if (cmd instanceof FileOpener) {
                ((FileOpener) cmd).setFileName(argument);
                currentFileName = argument;
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
        for (FileHandler handler : commandMap.values()) {
            if (handler instanceof PrintTransitions) {
                PrintTransitions printTransitions = (PrintTransitions) handler;
                String transitionAutomatonId = printTransitions.getAutomatonId();
                if (transitionAutomatonId != null && transitionAutomatonId.equals(automatonId)) {
                    return printTransitions;
                }
            }
        }
        return null;
    }
}