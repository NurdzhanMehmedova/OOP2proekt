package OOP2.proekt.f22621609;

import OOP2.proekt.f22621609.secondary_functions.ConcatCommand;
import OOP2.proekt.f22621609.secondary_functions.DeterministicCommand;
import OOP2.proekt.f22621609.secondary_functions.EmptyCommand;
import OOP2.proekt.f22621609.secondary_functions.RecognizeCommand;

import java.util.Scanner;

public class Application {
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
}
