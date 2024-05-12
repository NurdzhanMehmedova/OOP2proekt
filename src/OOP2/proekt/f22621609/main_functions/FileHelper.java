package OOP2.proekt.f22621609.main_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;

public class FileHelper implements FileHandler {
    @Override
    public void processing() {
        printHelp();
    }

    public void printHelp() {
        System.out.println("The following commands are supported:");
        System.out.println("open <file>\t- opens <file>");
        System.out.println("close\t\t- closes currently opened file");
        System.out.println("save\t\t- saves the currently open file");
        System.out.println("saveas <file>\t- saves the currently open file in <file>");
        System.out.println("help\t\t- prints this information");

        System.out.println("list\t\t- prints the id of machines");//1
        System.out.println("print\t\t-displays information about all transitions in the automaton");//2
        System.out.println("saveauto\t\t-saves an automaton to a file");//3
        System.out.println("checkempty\t\t-checks if the machine language is empty");//4
        System.out.println("checkdeterministic\t\t-checks whether an automaton is deterministic");//5
        System.out.println("recognize\t\t-checks if a word is in the machine language");//6

        System.out.println("print\t\t- prints the transitions");
        System.out.println("exit\t\t- exits the program");
    }
}