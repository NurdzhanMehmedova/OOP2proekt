/*package OOP2.proekt.f22621609;

import OOP2.proekt.f22621609.enums.Command;
import OOP2.proekt.f22621609.main_functions.CommandCenter;
import OOP2.proekt.f22621609.main_functions.FileMenu;
import OOP2.proekt.f22621609.secondary_functions.*;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        FileMenu fileMenu = new FileMenu();
        CommandCenter commandCenter = new CommandCenter();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Current directory of files: " + System.getProperty("user.dir"));

        boolean running = true;
        while (running) {
            System.out.print("Enter command: ");
            String commandInput = scanner.nextLine().trim();
            commandCenter.executeCommand(commandInput, fileMenu);

            if (Command.fromString(commandInput) == Command.EXIT) {
                running = false;
            }
        }
        scanner.close();
    }
}*/

package OOP2.proekt.f22621609;

import OOP2.proekt.f22621609.main_functions.FileMenu;
import OOP2.proekt.f22621609.secondary_functions.*;

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
            String command = scanner.nextLine().trim();

            switch (command.toLowerCase()) {
                case "exit":
                    fileMenu.executeCommand(command);
                    running = false;
                    break;
                case "print":
                    System.out.print("Enter automaton ID: ");
                    String automatonIdForPrint = scanner.nextLine().trim();
                    ((PrintTransitions) fileMenu.getCommandMap().get("print")).setAutomatonId(automatonIdForPrint);
                    System.out.println("Automaton ID set to: " + automatonIdForPrint);
                    fileMenu.executeCommand(command + " " + automatonIdForPrint);
                    break;

                default:
                    fileMenu.executeCommand(command);
                    break;
            }
        }
        scanner.close();
    }
}