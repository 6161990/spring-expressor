package numberguessing.console;

import numberguessing.PositiveIntegerGenerator;

public final class AppModel {
    private static final String NEW_LINE = System.lineSeparator();

    public AppModel(PositiveIntegerGenerator generator) {
    }

    public boolean isCompleted() {
        return false;
    }

    public String flushOutput() {
        return "1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE +
                "3: Exit" + NEW_LINE + "Enter selection: ";
    }

    public void processInput(String input) {
    }
}
