package numberGuessing.console;

import numberGuessing.PositiveIntegerGenerator;

public class AppModel {

    public static final String NEW_LINE = System.lineSeparator();
    public static final String GAME_MODE_SELECT_MESSAGE = "1: Single player game" + NEW_LINE +
            "2: Multiplayer game" + NEW_LINE + "3: Exit" + NEW_LINE + "Enter selection: ";
    private boolean isCompleted;
    private String output;

    public AppModel(PositiveIntegerGenerator generator) {
        isCompleted = false;
        output = GAME_MODE_SELECT_MESSAGE;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String flushOutput() {
        return output;
    }

    public void processInput(String input) {

        if(input == "1") {
            output = "Single player game" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                    + NEW_LINE + "Enter your guess: ";
        } else if(input == "3"){
            isCompleted = true;
        }
    }
}
