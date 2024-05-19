package OOP2.proekt.f22621609.main_functions;
import OOP2.proekt.f22621609.contracts.FileHandler;

import java.io.*;
/**
 * The {@code FileSaverAs} class implements the {@link FileHandler} interface
 * to handle the saving of file content to disk with a new file name.
 */
public class FileSaverAs implements FileHandler {
    private String newFileName;
    private StringBuilder fileContent;
    /**
     * Constructs a {@code FileSaverAs} object with the specified new file name
     * and a {@code StringBuilder} containing the file content.
     *
     * @param newFileName the new name for the file to be saved
     * @param fileContent the {@code StringBuilder} containing the file content
     */
    public FileSaverAs(String newFileName, StringBuilder fileContent) {
        this.newFileName = newFileName;
        this.fileContent = fileContent;
    }
    /**
     * Sets the new file name for saving.
     *
     * @param newFileName the new name for the file to be saved
     */
    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }
    /**
     * Saves the content of the file to disk with the specified new file name.
     */
    @Override
    public void processing() {
        saveToFile(newFileName, fileContent);
    }
    /**
     * Writes the file content to a text file with the specified new file name.
     *
     * @param fileName the new name for the file to be saved
     * @param fileContent the content of the file to write
     */
    private void saveToFile(String fileName, StringBuilder fileContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".txt"))) {
            writer.write(fileContent.toString());
            System.out.println("Successfully saved as " + fileName + ".txt");
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
    }
}