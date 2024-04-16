package OOP2.proekt.f22621609.main_functions;
import OOP2.proekt.f22621609.contracts.FileHandler;

import java.io.*;

public class FileSaverAs implements FileHandler {
    private String newFileName;
    private StringBuilder fileContent;

    public FileSaverAs(String newFileName, StringBuilder fileContent) {
        this.newFileName = newFileName;
        this.fileContent = fileContent;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    @Override
    public void processing() {
        saveToFile(newFileName, fileContent);
    }

    private void saveToFile(String fileName, StringBuilder fileContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(fileContent.toString());
            System.out.println("Successfully saved as " + new File(fileName).getName());
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
    }
}