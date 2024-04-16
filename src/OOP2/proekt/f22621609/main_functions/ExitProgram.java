package OOP2.proekt.f22621609.main_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;

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