package numberGuessing.console;

import numberGuessing.PositiveIntegerGenerator;

public class AppModel {

    private final PositiveIntegerGenerator randomGenerator;
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SELECT_MODE_MESSAGE = "1: Single player game" + NEW_LINE +
            "2: Multiplayer game" + NEW_LINE + "3: Exit" + NEW_LINE + "Enter selection: ";
    private static final String SINGLE_PLAYER_START_MESSAGE = "Single player game" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                + NEW_LINE + "Enter your guess: ";
    private boolean isCompleted;
    private String output;
    private int answer;
    private boolean isSinglePlayerGameMode;
    private int tries;

    public AppModel(PositiveIntegerGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
        this.isCompleted = false;
        output = SELECT_MODE_MESSAGE;
        tries = 0;
        answer = 0;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String flushOutput() {
        return output;
    }

    public void processInput(String input) {
        if(isSinglePlayerGameMode) {
            singlePlayerMode(input);
        } else {
            selectionGameMode(input);
        }
    }

    private void selectionGameMode(String input) {
        if (input == "1") {
            isSinglePlayerGameMode = true;
            answer = randomGenerator.generateLessThanEqualsToHundred();
            output = SINGLE_PLAYER_START_MESSAGE;
        } else {
            isCompleted = true;
        }
    }

    private void singlePlayerMode(String input) {
        tries++;
        if (Integer.parseInt(input) < answer) {
            output = "Your guess is too low." + NEW_LINE + "Enter your guess: ";
        } else if (Integer.parseInt(input) > answer) {
            output = "Your guess is too high." + NEW_LINE + "Enter your guess: ";
        } else if(Integer.parseInt(input) == answer) {
            output = "Correct! + " + tries + (tries == 1 ? " guess." : " guesses." ) + NEW_LINE + SELECT_MODE_MESSAGE;
            isSinglePlayerGameMode = false;
        }
    }
}
