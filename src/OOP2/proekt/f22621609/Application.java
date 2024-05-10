package OOP2.proekt.f22621609;

/*import OOP2.proekt.f22621609.avtomat.State;
import OOP2.proekt.f22621609.main_functions.FileMenu;
import OOP2.proekt.f22621609.secondary_functions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        FileMenu fileMenu = new FileMenu();
        FiniteAutomaton automaton1 = new FiniteAutomaton(); // Първи автомат
        FiniteAutomaton automaton2 = new FiniteAutomaton(); // Втори автомат


        // Инициализация на състоянията със зададени идентификатори и имена
        State q0 = new State("q0", "State 0");
        State q1 = new State("q1", "State 1");
        State q2 = new State("q2", "State 2");

        // Добавяне на състоянията към автомата
        automaton1.addState(q0);
        automaton1.addState(q1);
        automaton1.addState(q2);

        // Добавяне на преходите между състоянията
        automaton1.addTransition(q0, 'a', q1);
        automaton1.addTransition(q0, 'b', q2);

        // Задаване на началното състояние
        automaton1.setInitialState(q0);

        // Добавяне на крайното състояние
        automaton1.addAcceptingState(q2);
        fileMenu.setAutomaton(automaton1);
        // Създаване на ConcatCommand инстанция с първия и втория автомат като параметри
        ConcatCommand concatCommand = new ConcatCommand(automaton1, automaton2);

        // Извикване на метода на ConcatCommand
        concatCommand.processing();
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
                case "empty":
                    System.out.print("Please provide automaton ID: ");
                    String automatonIdForEmpty = scanner.nextLine().trim(); // Вземете идентификатора от потребителя
                    fileMenu.executeCommand(command); // Предайте командата "empty"
                    break;
                case "saveauto":
                    System.out.print("Enter automaton ID and filename: ");
                    String input = scanner.nextLine().trim();
                    String[] parts = input.split("\\s+");
                    if (parts.length != 2) {
                        System.out.println("Invalid input format. Please provide automaton ID and filename.");
                    } else {
                        String automatonIdForSave = parts[0];
                        String filename = parts[1];
                        FiniteAutomaton automatonForSave = fileMenu.getAutomaton(automatonIdForSave);
                        if (automatonForSave == null) {
                            System.out.println("No automaton found with ID: " + automatonIdForSave);
                        } else {
                            fileMenu.executeCommand("saveauto " + automatonIdForSave + " " + filename);
                        }
                    }
                    break;

                case "deterministic":
                    System.out.print("Enter automaton ID: ");
                    String automatonIdForDeterministic = scanner.nextLine().trim();
                    FiniteAutomaton automatonForDeterministic = fileMenu.getAutomaton(automatonIdForDeterministic);
                    if (automatonForDeterministic == null) {
                        System.out.println("No automaton found with ID: " + automatonIdForDeterministic);
                    } else {
                        DeterministicCommand deterministicCommand = new DeterministicCommand(automatonForDeterministic); // Подава се конкретния автомат
                        deterministicCommand.setAutomatonId(automatonIdForDeterministic);
                        fileMenu.executeCommand("deterministic " + automatonIdForDeterministic);
                    }
                    break;
                case "recognize":
                    RecognizeCommand recognizeCommand = new RecognizeCommand(automaton1); // Подайте вече инициализирания автомат
                    recognizeCommand.processing();
                    break;

                default:
                    fileMenu.executeCommand(command);
                    break;
            }
        }

        scanner.close();
    }
}*/
import OOP2.proekt.f22621609.avtomat.State;
import OOP2.proekt.f22621609.main_functions.FileMenu;
import OOP2.proekt.f22621609.secondary_functions.*;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        FileMenu fileMenu = new FileMenu();
        FiniteAutomaton automaton1 = new FiniteAutomaton(); // Първи автомат
        FiniteAutomaton automaton2 = new FiniteAutomaton(); // Втори автомат

        // Инициализация на състоянията със зададени идентификатори и имена
        State q0 = new State("q0", "State 0");
        State q1 = new State("q1", "State 1");
        State q2 = new State("q2", "State 2");

        // Добавяне на състоянията към автомата
        automaton1.addState(q0);
        automaton1.addState(q1);
        automaton1.addState(q2);

        // Добавяне на преходите между състоянията
        automaton1.addTransition(q0, 'a', q1);
        automaton1.addTransition(q0, 'b', q2);

        // Задаване на началното състояние
        automaton1.setInitialState(q0);

        // Добавяне на крайното състояние
        automaton1.addAcceptingState(q2);
        fileMenu.setAutomaton(automaton1);

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
                case "empty":
                    System.out.print("Please provide automaton ID: ");
                    String automatonIdForEmpty = scanner.nextLine().trim(); // Вземете идентификатора от потребителя
                    fileMenu.executeCommand(command); // Предайте командата "empty"
                    break;
                case "saveauto":
                    System.out.print("Enter automaton ID and filename: ");
                    String input = scanner.nextLine().trim();
                    String[] parts = input.split("\\s+");
                    if (parts.length != 2) {
                        System.out.println("Invalid input format. Please provide automaton ID and filename.");
                    } else {
                        String automatonIdForSave = parts[0];
                        String filename = parts[1];
                        FiniteAutomaton automatonForSave = fileMenu.getAutomaton(automatonIdForSave);
                        if (automatonForSave == null) {
                            System.out.println("No automaton found with ID: " + automatonIdForSave);
                        } else {
                            fileMenu.executeCommand("saveauto " + automatonIdForSave + " " + filename);
                        }
                    }
                    break;

                case "deterministic":
                    System.out.print("Enter automaton ID: ");
                    String automatonIdForDeterministic = scanner.nextLine().trim();
                    FiniteAutomaton automatonForDeterministic = fileMenu.getAutomaton(automatonIdForDeterministic);
                    if (automatonForDeterministic == null) {
                        System.out.println("No automaton found with ID: " + automatonIdForDeterministic);
                    } else {
                        DeterministicCommand deterministicCommand = new DeterministicCommand(automatonForDeterministic); // Подава се конкретния автомат
                        deterministicCommand.setAutomatonId(automatonIdForDeterministic);
                        fileMenu.executeCommand("deterministic " + automatonIdForDeterministic);
                    }
                    break;
                case "recognize":
                    RecognizeCommand recognizeCommand = new RecognizeCommand(automaton1); // Подайте вече инициализирания автомат
                    recognizeCommand.processing();
                    break;
                case "concat":
                    ConcatCommand concatCommand = new ConcatCommand(automaton1, automaton2); // Създаване на обект от класа ConcatCommand
                    concatCommand.processing(); // Изпълнение на командата
                    break;
                default:
                    fileMenu.executeCommand(command);
                    break;
            }
        }

        scanner.close();
    }
}