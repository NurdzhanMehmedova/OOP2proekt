package OOP2.proekt.f22621609.main_functions;
import OOP2.proekt.f22621609.contracts.FileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileOpener implements FileHandler {
    private String fileName;
    private StringBuilder fileContent;

    public FileOpener(String fileName, StringBuilder fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void processing() {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                // If the file doesn't exist, create a new one
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("Failed to create the file.");
                    return;
                }
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
                System.out.println("Successfully opened " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("Error opening the file: " + e.getMessage());
        }
    }

    public StringBuilder getFileContent() {
        return fileContent;
    }
}