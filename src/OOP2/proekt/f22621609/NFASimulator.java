package OOP2.proekt.f22621609;
import java.util.*;
import java.io.*;
public class NFASimulator implements Machine, NfaMachine{
    public static void main(String[] args) {
        Map<String, NFA> machines = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to NFA Simulator!");

        while (true) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program...");
                break;
            }

            if (command.startsWith("open")) {
                String[] parts = command.split("\\s+");
                if (parts.length != 2) {
                    System.out.println("Invalid command. Use: open <filename>");
                    continue;
                }

                String filename = parts[1];
                try {
                    FileInputStream fileStream = new FileInputStream(filename);
                    ObjectInputStream objectStream = new ObjectInputStream(fileStream);
                    NFA machine = (NFA) objectStream.readObject();
                    objectStream.close();

                    String id = UUID.randomUUID().toString();
                    machines.put(id, machine);

                    System.out.println("Successfully opened " + filename + " with ID " + id);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (command.startsWith("close")) {
                String[] parts = command.split("\\s+");
                if (parts.length != 2) {
                    System.out.println("Invalid command. Use: close <id>");
                    continue;
                }

                String id = parts[1];
                NFA machine = machines.get(id);
                if (machine == null) {
                    System.out.println("Error: Machine with ID " + id + " not found.");
                    continue;
                }

                machines.remove(id);
                System.out.println("Successfully closed machine with ID " + id);
            }
            else if (command.startsWith("save")) {
                String[] parts = command.split("\\s+");
                if (parts.length != 2) {
                    System.out.println("Invalid command. Use: save <id>");
                    continue;
                }

                String id = parts[1];
                NFA machine = machines.get(id);
                if (machine == null) {
                    System.out.println("Error: Machine with ID " + id + " not found.");
                    continue;
                }

                try {
                    FileOutputStream fileStream = new FileOutputStream(id + ".nfa");
                    ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
                    objectStream.writeObject(machine);
                    objectStream.close();

                    System.out.println("Successfully saved machine with ID " + id + " as " + id + ".nfa");
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            else if (command.startsWith("save as")) {
                String[] parts = command.split("\\s+");
                if (parts.length != 3) {
                    System.out.println("Invalid command. Use: save as <id> <filename>");
                    continue;
                }

                String id = parts[1];
                NFA machine = machines.get(id);
                if (machine == null) {
                    System.out.println("Error: Machine with ID " + id + " not found.");
                    continue;
                }

                String filename = parts[2];
                try {
                    FileOutputStream fileStream = new FileOutputStream(filename);
                    ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
                    objectStream.writeObject(machine);
                    objectStream.close();

                    System.out.println("Successfully saved machine with ID " + id + " as " + filename);
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            else if (command.startsWith("help")) {
                System.out.println("The following commands are supported:");
                System.out.println("open <file> - opens <file>");
                System.out.println("close - closes currently opened file");
                System.out.println("save - saves the currently open file");
                System.out.println("save as <file> - saves the currently open file as <file>");
                System.out.println("list - prints a list of currently open files");
                System.out.println("print <id> - prints the contents of the NFA with ID <id>");
                System.out.println("empty <id> - checks if the language of NFA with ID <id> is empty");
                System.out.println("deterministic <id> - checks if the NFA with ID <id> is deterministic");
                System.out.println("exit - exits the program");
            }
            else if (command.startsWith("list")) {
                System.out.println("List of currently open machines:");
                for (Map.Entry<String, NFA> entry : machines.entrySet()) {
                    System.out.println(entry.getKey());
                }
            }
            else if (command.startsWith("print")) {
                String[] parts = command.split("\\s+");
                if (parts.length != 2) {
                    System.out.println("Invalid command. Use: print <id>");
                    continue;
                }

                String id = parts[1];
                NFA machine = machines.get(id);
                if (machine == null) {
                    System.out.println("Error: Machine with ID " + id + " not found.");
                    continue;
                }

                System.out.println("Printing NFA with ID " + id + ":");
                System.out.println(machine);
            }
            else if (command.startsWith("empty")) {
                String[] parts = command.split("\\s+");
                if (parts.length != 2) {
                    System.out.println("Invalid command. Use: empty <id>");
                    continue;
                }

                String id = parts[1];
                NFA machine = machines.get(id);
                if (machine == null) {
                    System.out.println("Error: Machine with ID " + id + " not found.");
                    continue;
                }

                boolean isEmpty = machine.isEmpty();
                System.out.println("The language of NFA with ID " + id + " is " + (isEmpty ? "empty" : "not empty"));
            }
            else if (command.startsWith("deterministic")) {
                String[] parts = command.split("\\s+");
                if (parts.length != 2) {
                    System.out.println("Invalid command. Use: deterministic <id>");
                    continue;
                }

                String id = parts[1];
                NFA machine = machines.get(id);
                if (machine == null) {
                    System.out.println("Error: Machine with ID " + id + " not found.");
                    continue;
                }

                boolean isDeterministic = machine.isDeterministic();
                System.out.println("The NFA with ID " + id + " is " + (isDeterministic ? "deterministic" : "non-deterministic"));
            }
            else {
                System.out.println("Invalid command.");
            }
        }
    }

    @Override
    public void open(String file) {

    }

    @Override
    public void close() {

    }

    @Override
    public void save() {

    }

    @Override
    public void saveAs(String file) {

    }

    @Override
    public void help() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void list() {

    }

    @Override
    public void print(String id) {

    }

    @Override
    public void empty(String id) {

    }

    @Override
    public void deterministic(String id) {

    }
}