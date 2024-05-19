package OOP2.proekt.f22621609.main_functions;
import OOP2.proekt.f22621609.contracts.FileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * The {@code FileOpener} class implements the {@link FileHandler} interface
 * to handle the opening of files and reading their content.
 */
public class FileOpener implements FileHandler {
    private String fileName;
    private StringBuilder fileContent;
    /**
     * Constructs a {@code FileOpener} object with the specified file name and
     * a {@code StringBuilder} to store the file content.
     *
     * @param fileName    the name of the file to open
     * @param fileContent the {@code StringBuilder} to store the file content
     */
    public FileOpener(String fileName, StringBuilder fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }
    /**
     * Sets the name of the file to open.
     *
     * @param fileName the name of the file to open
     */

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * Reads the content of the file and stores it in the {@code StringBuilder}.
     * If the file does not exist, it creates a new file.
     */
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
    /**
     * Returns the {@code StringBuilder} containing the content of the file.
     *
     * @return the {@code StringBuilder} containing the content of the file
     */
    public StringBuilder getFileContent() {
        return fileContent;
    }

}