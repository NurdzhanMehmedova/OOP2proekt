package OOP2.proekt.f22621609;
import java.util.HashMap;
import java.util.Map;public class FileMenu {
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
    }

    public void executeCommand(String command) {
        String[] parts = command.split("\\s", 2);

        String commandName = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : "";

        FileHandler cmd = commandMap.get(commandName);

        if (cmd != null) {
            if (cmd instanceof FileOpener) {
                ((FileOpener) cmd).setFileName(argument);
            } else if (cmd instanceof FileSaver) {
                ((FileSaver) cmd).setFileName(argument);
            } else if (cmd instanceof FileSaverAs) {
                ((FileSaverAs) cmd).setNewFileName(argument);
            }

            cmd.processing();
        } else {
            System.out.println("Unknown command: " + commandName);
        }
    }
}