package OOP2.proekt.f22621609.secondary_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;
import OOP2.proekt.f22621609.main_functions.FileOpener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListMachines implements FileHandler {
    private FileOpener fileOpener;

    public ListMachines(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }

    @Override
    public void processing() {
        StringBuilder fileContent = fileOpener.getFileContent(); // Get the content of the file from FileOpener
        if (fileContent != null) {
            // Logic to extract information about the automatons
            Pattern automatonPattern = Pattern.compile("<automaton\\s+id=\"(\\w+)\"\\s+name=\"([^\"]+)\">");
            Matcher matcher = automatonPattern.matcher(fileContent);

            System.out.println("List of automatons:");
            while (matcher.find()) {
                String id = matcher.group(1);
                String name = matcher.group(2);
                System.out.println("ID: " + id + ", Name: " + name);
            }
        }
    }
}