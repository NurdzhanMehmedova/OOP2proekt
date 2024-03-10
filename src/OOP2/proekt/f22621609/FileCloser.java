package OOP2.proekt.f22621609;
import java.io.Closeable;
import java.io.IOException;

public class FileCloser implements FileHandler {
    private StringBuilder fileContent;

    public FileCloser(StringBuilder fileContent) {
        this.fileContent = fileContent;
    }

    @Override
    public void processing() {
        fileContent.setLength(0);
        System.out.println("Successfully closed the file.");
    }
}