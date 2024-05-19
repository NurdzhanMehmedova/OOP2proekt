package OOP2.proekt.f22621609.main_functions;

import OOP2.proekt.f22621609.contracts.FileHandler;

/**
 * The ExitProgram class implements the FileHandler interface to handle
 * the exit operation for the program. When the processing method is invoked,
 * the program will terminate.
 */
public class ExitProgram implements FileHandler {

    /**
     * Invokes the exit operation, terminating the program.
     */
    @Override
    public void processing() {
        exit();
    }

    /**
     * Exits the program by printing a message and terminating the JVM.
     */
    private void exit() {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
