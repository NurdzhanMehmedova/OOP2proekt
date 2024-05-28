package OOP2.proekt.f22621609.main_functions;
import OOP2.proekt.f22621609.contracts.FileHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The {@code FileSaverAs} class implements the {@link FileHandler} interface
 * to handle the saving of file content to disk with a new file name.
 */
public class FileSaverAs implements FileHandler {
    private StringBuilder fileContent;
    private String fileName;

    /**
     * Constructs a {@code FileSaverAs} object with the specified new file name
     * and the {@code StringBuilder} containing the file content.
     *
     * @param fileName    the new name for the file to be saved
     * @param fileContent the {@code StringBuilder} containing the file content
     */
    public FileSaverAs(String fileName, StringBuilder fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public void setFileContent(StringBuilder fileContent) {
        this.fileContent = fileContent;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Saves the content of the file to disk with the specified new file name.
     */
    @Override
    public void processing() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the new file name: ");
        String newFileName = scanner.nextLine().trim();
        saveToFile(newFileName, fileContent);
    }

    /**
     * Writes the file content to a text file with the specified new file name.
     *
     * @param newFileName the new name for the file to be saved
     * @param fileContent the content of the file to write
     */
    private void saveToFile(String newFileName, StringBuilder fileContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName + ".txt"))) {
            writer.write(fileContent.toString());
            System.out.println("Successfully saved as " + newFileName + ".txt");
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
    }
}
