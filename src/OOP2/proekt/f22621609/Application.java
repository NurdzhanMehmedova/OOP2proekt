package OOP2.proekt.f22621609;
import OOP2.proekt.f22621609.enums.CommandType;
import OOP2.proekt.f22621609.main_functions.FileMenu;
import java.util.Scanner;

/**
 * Main class representing the application entry point.
 */
public class Application {
    /**
     * The main method to start the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        FileMenu fileMenu = new FileMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Current directory of files: " + System.getProperty("user.dir"));
        boolean running = true;
        while (running) {
            System.out.print("Enter command: ");
            String commandInput = scanner.nextLine().trim();
            String[] parts = commandInput.split("\\s+", 2);
            String commandName = parts[0].toUpperCase();
            String argument = parts.length > 1 ? parts[1] : "";

            CommandType commandType;
            try {
                commandType = CommandType.valueOf(commandName);
            } catch (IllegalArgumentException e) {
                System.out.println("Unknown command: " + commandName);
                continue;
            }

            if (commandType == CommandType.EXIT) {
                fileMenu.executeCommand(commandType, argument);
                running = false;
            } else {
                fileMenu.executeCommand(commandType, argument);
            }
        }
        scanner.close();
    }
}