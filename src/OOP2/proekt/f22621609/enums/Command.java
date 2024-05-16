/*package OOP2.proekt.f22621609.enums;

public enum Command {
    OPEN,
    CLOSE,
    SAVE,
    SAVE_AS,
    HELP,
    LIST,
    PRINT,
    SAVE_AUTO,
    CHECK_EMPTY,
    CHECK_DETERMINISTIC,
    RECOGNIZE,
    UN,
    CHECK_FINITE,
    EXIT,
    POSITIVE_WRAPPER,
    PRINT_TRANSITIONS,
    RECOGNIZE_WORD,
    CREATE_AUTOMATON,
    UNKNOWN;

    public static Command fromString(String commandStr) {
        String[] parts = commandStr.split(" ", 2); // Split the command into two parts
        try {
            return Command.valueOf(parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}*/