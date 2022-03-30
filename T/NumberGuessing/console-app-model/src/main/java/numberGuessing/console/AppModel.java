package numberGuessing.console;

import numberGuessing.PositiveIntegerGenerator;

public class AppModel {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String SELECT_MODE_MESSAGE = "1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE +
            "3: Exit" + NEW_LINE + "Enter selection: ";
    public AppModel(PositiveIntegerGenerator randomGenerator) {
    }

    public boolean isCompleted() {
        return false;
    }

    public String flushOutput() {
        return SELECT_MODE_MESSAGE;
    }

    public void processInput(String input) {
    }
}
