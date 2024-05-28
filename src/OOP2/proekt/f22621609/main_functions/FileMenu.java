package OOP2.proekt.f22621609.main_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.enums.CommandType;
import OOP2.proekt.f22621609.secondary_functions.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code FileMenu} class manages a set of file-related commands for an automaton application.
 * It initializes various command handlers and executes commands based on user input.
 */
public class FileMenu {
    private Map<CommandType, FileHandler> commandMap = new HashMap<>();
    private StringBuilder fileContent = new StringBuilder();
    private String currentFileName = "";
    private AutomatonFileSaver automatonFileSaver;
    private CheckEmptyLanguage checkEmptyLanguage;
    private CheckDeterministic checkDeterministic;
    private RecognizeWord recognizeWord;
    private CheckFiniteLanguage checkFiniteLanguage;
    private PositiveWrapper positiveWrapper;
    private RegexAutomatonCreator regexAutomatonCreator;
    private ConcatAutomata concatAutomaton;
    private UnionAutomata unionAutomata;

    /**
     * Constructs a {@code FileMenu} and initializes the command handlers.
     */
    public FileMenu() {
        commandMap.put(CommandType.CLOSE, new FileCloser(fileContent));
        commandMap.put(CommandType.EXIT, new ExitProgram());
        commandMap.put(CommandType.HELP, new FileHelper());
        commandMap.put(CommandType.OPEN, new FileOpener("", fileContent));
        commandMap.put(CommandType.SAVE, new FileSaver("", fileContent));
        commandMap.put(CommandType.SAVEAS, new FileSaverAs("", fileContent));
        commandMap.put(CommandType.LIST, new ListMachines(new FileOpener("", fileContent)));

        // Add PrintTransitions instance to the command map
        FileOpener fileOpenerForPrint = new FileOpener("", fileContent);
        PrintTransitions printTransitions = new PrintTransitions(fileOpenerForPrint);
        commandMap.put(CommandType.PRINT, printTransitions);

        // Initialize AutomatonFileSaver instance
        automatonFileSaver = new AutomatonFileSaver(fileContent);
        commandMap.put(CommandType.SAVEAUTO, automatonFileSaver);

        // Initialize CheckEmptyLanguage instance
        FileOpener fileOpenerForCheckEmpty = new FileOpener("", fileContent);
        checkEmptyLanguage = new CheckEmptyLanguage(fileOpenerForCheckEmpty);
        commandMap.put(CommandType.CHECKEMPTY, checkEmptyLanguage);

        // Initialize CheckDeterministic instance
        FileOpener fileOpenerForCheckDeterministic = new FileOpener("", fileContent);
        checkDeterministic = new CheckDeterministic(fileOpenerForCheckDeterministic);
        commandMap.put(CommandType.CHECKDETERMINISTIC, checkDeterministic);

        // Initialize RecognizeWord instance
        FileOpener fileOpenerForRecognizeWord = new FileOpener("", fileContent);
        recognizeWord = new RecognizeWord(fileOpenerForRecognizeWord);
        commandMap.put(CommandType.RECOGNIZE, recognizeWord);

        // Initialize CheckFiniteLanguage instance
        FileOpener fileOpenerForCheckFinite = new FileOpener("", fileContent);
        this.checkFiniteLanguage = new CheckFiniteLanguage(fileOpenerForCheckFinite);
        commandMap.put(CommandType.CHECKFINITE, checkFiniteLanguage);

        FileOpener fileOpenerForPositiveWrapper = new FileOpener("", fileContent);
        positiveWrapper = new PositiveWrapper(fileOpenerForPositiveWrapper);
        commandMap.put(CommandType.UN, positiveWrapper);

        FileOpener fileOpenerForRegexWrapper = new FileOpener("", fileContent);
        regexAutomatonCreator = new RegexAutomatonCreator(fileOpenerForRegexWrapper);
        commandMap.put(CommandType.REG, regexAutomatonCreator);

        FileOpener fileOpenerForConcat = new FileOpener("", fileContent);
        concatAutomaton = new ConcatAutomata(fileOpenerForConcat);
        commandMap.put(CommandType.CONCAT, concatAutomaton);

        FileOpener fileOpenerForUnion = new FileOpener("",fileContent);
        unionAutomata = new UnionAutomata(fileOpenerForUnion);
        commandMap.put(CommandType.UNION, unionAutomata);
    }

    /**
     * Executes the specified command.
     *
     * @param command the command to execute
     * @param argument the argument for the command
     */
    public void executeCommand(CommandType command, String argument) {
        if (command == CommandType.PRINT) {
            PrintTransitions printTransitions = (PrintTransitions) commandMap.get(command);
            printTransitions.setAutomatonId(argument);
            printTransitions.processing();
            return;
        }

        if (command == CommandType.SAVEAUTO) {
            automatonFileSaver.processing();
            return;
        }
        if (command == CommandType.CHECKEMPTY) {
            checkEmptyLanguage.processing();
            return;
        }
        if (command == CommandType.CHECKDETERMINISTIC) {
            checkDeterministic.processing();
            return;
        }
        if (command == CommandType.RECOGNIZE) {
            recognizeWord.processing();
            return;
        }
        if (command == CommandType.CHECKFINITE) {
            checkFiniteLanguage.processing();
            return;
        }
        if (command == CommandType.UN) {
            positiveWrapper.processing();
            return;
        }
        if (command == CommandType.REG) {
            regexAutomatonCreator.processing();
            return;
        }
        if (command == CommandType.CONCAT) {
            concatAutomaton.processing();
            return;
        }
        if(command == CommandType.UNION){
            unionAutomata.processing();
            return;
        }

        FileHandler cmd = commandMap.get(command);
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
                ((FileSaverAs) cmd).setFileName(argument);
            }

            cmd.processing();
        } else {
            System.out.println("Unknown command: " + command);
        }
    }
    /**
     * Finds the {@code PrintTransitions} instance associated with the specified automaton ID.
     *
     * @param automatonId the ID of the automaton
     * @return the {@code PrintTransitions} instance, or null if not found
     */
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