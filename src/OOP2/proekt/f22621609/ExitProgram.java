package OOP2.proekt.f22621609;

public class ExitProgram implements FileHandler {
    @Override
    public void processing() {
        exit();
    }

    private void exit() {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}