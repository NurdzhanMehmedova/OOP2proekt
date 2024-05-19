package OOP2.proekt.f22621609.main_functions;
import OOP2.proekt.f22621609.contracts.FileHandler;

import java.io.*;
/**
 * The {@code FileSaver} class implements the {@link FileHandler} interface
 * to handle the saving of file content to disk.
 */
public class FileSaver implements FileHandler {
    private String fileName;
    private StringBuilder fileContent;
    /**
     * Constructs a {@code FileSaver} object with the specified file name and
     * a {@code StringBuilder} containing the file content.
     *
     * @param fileName    the name of the file to save
     * @param fileContent the {@code StringBuilder} containing the file content
     */
    public FileSaver(String fileName, StringBuilder fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }
    /**
     * Sets the name of the file to save.
     *
     * @param fileName the name of the file to save
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * Saves the content of the file to disk with the specified file name.
     * If no file name is set, it prints an error message.
     */
    @Override
    public void processing() {
        if (fileName != null) {
            saveToFile(fileName, fileContent);
        } else {
            System.out.println("No file is currently open. Use 'open' to open a file.");
        }
    }
    /**
     * Writes the file content to a text file with the specified file name.
     *
     * @param fileName    the name of the file to save
     * @param fileContent the content of the file to write
     */
    private void saveToFile(String fileName, StringBuilder fileContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".txt"))) {
            writer.write(fileContent.toString());
            System.out.println("Successfully saved to " + fileName + ".txt");
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
    }
}