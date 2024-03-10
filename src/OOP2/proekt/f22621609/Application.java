package OOP2.proekt.f22621609;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        FileMenu fileMenu = new FileMenu();
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

            System.out.println();
        }

        scanner.close();
    }
}