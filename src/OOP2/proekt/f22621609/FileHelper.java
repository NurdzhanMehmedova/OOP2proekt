package OOP2.proekt.f22621609;

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
        System.out.println("exit\t\t- exits the program");
    }
}