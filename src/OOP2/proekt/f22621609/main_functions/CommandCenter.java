/*package OOP2.proekt.f22621609.main_functions;

import OOP2.proekt.f22621609.enums.Command;
import OOP2.proekt.f22621609.main_functions.FileMenu;
import OOP2.proekt.f22621609.secondary_functions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class CommandCenter {
    private final Map<Command, Consumer<FileMenu>> commandMap = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public CommandCenter() {
        initializeCommands();
    }

    private void initializeCommands() {
        commandMap.put(Command.OPEN, fileMenu -> {
            System.out.print("Enter file path: ");
            String filePath = scanner.nextLine().trim();
            fileMenu.executeCommand("open " + filePath);
        });

        commandMap.put(Command.CLOSE, fileMenu -> fileMenu.executeCommand("close"));

        commandMap.put(Command.SAVE, fileMenu -> fileMenu.executeCommand("save"));

        commandMap.put(Command.SAVE_AS, fileMenu -> {
            System.out.print("Enter new file name: ");
            String newFileName = scanner.nextLine().trim();
            FileSaverAs fileSaverAs = (FileSaverAs) fileMenu.getCommandMap().get("saveas");
            fileSaverAs.setNewFileName(newFileName);
            fileMenu.executeCommand("saveas " + newFileName);
        });
        commandMap.put(Command.HELP, fileMenu -> {
            FileHelper fileHelper = new FileHelper();
            fileHelper.processing();
        });
        commandMap.put(Command.LIST, fileMenu -> fileMenu.executeCommand("list"));
        commandMap.put(Command.PRINT, fileMenu -> {
            System.out.print("Enter automaton ID: ");
            String automatonId = scanner.nextLine().trim();
            fileMenu.executeCommand("print " + automatonId);
        });
        commandMap.put(Command.SAVE_AUTO, fileMenu -> {
            System.out.print("Enter filename to save: ");
            String filename = scanner.nextLine().trim();
            ((AutomatonFileSaver) fileMenu.getCommandMap().get("saveauto")).saveAutomatonToFile("", filename);
            fileMenu.executeCommand("saveauto " + filename);
        });
        commandMap.put(Command.CHECK_EMPTY, fileMenu -> {
            System.out.print("Enter the ID of the automaton to check: ");
            String automatonId = scanner.nextLine().trim();
            ((CheckEmptyLanguage) fileMenu.getCommandMap().get("checkempty")).processing();
        });
        commandMap.put(Command.CHECK_DETERMINISTIC, fileMenu -> {
            System.out.print("Enter the ID of the automaton to check: ");
            String automatonId = scanner.nextLine().trim();
            ((CheckDeterministic) fileMenu.getCommandMap().get("checkdeterministic")).processing();
        });
        commandMap.put(Command.RECOGNIZE, fileMenu -> fileMenu.executeCommand("recognize"));
        commandMap.put(Command.UN, fileMenu -> fileMenu.executeCommand("un"));
        commandMap.put(Command.CHECK_FINITE, fileMenu -> {
            System.out.print("Enter the ID of the automaton to check: ");
            String automatonId = scanner.nextLine().trim();
            ((CheckFiniteLanguage) fileMenu.getCommandMap().get("checkfinite")).processing();
        });
        commandMap.put(Command.EXIT, fileMenu -> fileMenu.executeCommand("exit"));
        commandMap.put(Command.POSITIVE_WRAPPER, fileMenu -> fileMenu.executeCommand("un"));
        commandMap.put(Command.PRINT_TRANSITIONS, fileMenu -> fileMenu.executeCommand("print"));
        commandMap.put(Command.RECOGNIZE_WORD, fileMenu -> fileMenu.executeCommand("recognize"));
        commandMap.put(Command.CREATE_AUTOMATON, fileMenu -> fileMenu.executeCommand("reg"));
        commandMap.put(Command.UNKNOWN, fileMenu -> System.out.println("Unknown command. Please try again."));
    }

    public void executeCommand(String commandStr, FileMenu fileMenu) {
        Command command = Command.fromString(commandStr);
        commandMap.getOrDefault(command, commandMap.get(Command.UNKNOWN)).accept(fileMenu);
    }
}
*/