package OOP2.proekt.f22621609;

import OOP2.proekt.f22621609.main_functions.FileMenu;
import OOP2.proekt.f22621609.secondary_functions.*;

import java.util.Scanner;

/*public class Application {
    public static void main(String[] args) {
        FileMenu fileMenu = new FileMenu();
        FiniteAutomaton automaton = new FiniteAutomaton();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the File Commander!");

        while (true) {
            System.out.print("Enter a command: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program...");
                break;
            }

            fileMenu.executeCommand(input);

            //  Execute finite automaton commands based on input
            if (input.equalsIgnoreCase("recognize")) {
                RecognizeCommand recognizeCommand = new RecognizeCommand(automaton);
                recognizeCommand.execute("some_word_to_recognize");
            } else if (input.equalsIgnoreCase("deterministic")) {
                DeterministicCommand deterministicCommand = new DeterministicCommand(automaton);
                deterministicCommand.execute();
            } else if (input.equalsIgnoreCase("empty")) {
                EmptyCommand emptyCommand = new EmptyCommand(automaton);
                emptyCommand.execute();
            } else if (input.equalsIgnoreCase("concat")) {
                // Example usage of ConcatCommand
                FiniteAutomaton automaton1 = new FiniteAutomaton();
                FiniteAutomaton automaton2 = new FiniteAutomaton();
                ConcatCommand concatCommand = new ConcatCommand(automaton1, automaton2);
                concatCommand.execute();
            }
            // осталите - un, union, reg, save, print

            System.out.println();
        }


        scanner.close();
    }
}*/
import java.util.Scanner;

public class Application {
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
                    String automatonId = scanner.nextLine().trim();
                    ((PrintTransitions)fileMenu.getCommandMap().get("print")).setAutomatonId(automatonId);
                    System.out.println("Automaton ID set to: " + automatonId);
                    fileMenu.executeCommand(command + " " + automatonId);
                    break;
                default:
                    fileMenu.executeCommand(command);
                    break;
            }
        }

        scanner.close();
    }
}