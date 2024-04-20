package OOP2.proekt.f22621609.main_functions;
import OOP2.proekt.f22621609.contracts.FileHandler;

import java.io.*;

public class FileSaver implements FileHandler {
    private String fileName;
    private StringBuilder fileContent;

    public FileSaver(String fileName, StringBuilder fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void processing() {
        if (fileName != null) {
            saveToFile(fileName, fileContent);
        } else {
            System.out.println("No file is currently open. Use 'open' to open a file.");
        }
    }

    private void saveToFile(String fileName, StringBuilder fileContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(fileContent.toString());
            System.out.println("Successfully saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
    }
}