package OOP2.proekt.f22621609.main_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;

/**
 * The FileCloser class implements the FileHandler interface to handle
 * the closing of a file. When the processing method is invoked, the file
 * content is cleared.
 */
public class FileCloser implements FileHandler {
    private StringBuilder fileContent;

    /**
     * Constructs a FileCloser with the specified file content.
     *
     * @param fileContent the content of the file to be closed
     */
    public FileCloser(StringBuilder fileContent) {
        this.fileContent = fileContent;
    }

    /**
     * Processes the file by clearing its content and printing a confirmation message.
     */
    @Override
    public void processing() {
        fileContent.setLength(0);
        System.out.println("Successfully closed the file.");
    }
}
