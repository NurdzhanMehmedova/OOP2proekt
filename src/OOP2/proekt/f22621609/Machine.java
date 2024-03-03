package OOP2.proekt.f22621609;

public interface Machine {
    void open(String file);
    void close();
    void save();
    void saveAs(String file);
    void help();
    void exit();
}