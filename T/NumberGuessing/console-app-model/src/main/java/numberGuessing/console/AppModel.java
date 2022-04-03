package numberGuessing.console;

import numberGuessing.PositiveIntegerGenerator;

public class AppModel {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String SELECTION_MODE_MESSAGE = "1: Single player game" + NEW_LINE +
            "2: Multiplayer game" + NEW_LINE + "3: Exit" + NEW_LINE + "Enter selection: ";
    private static final String SINGLE_PLAYER_START_MESSAGE = "Single player game" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                + NEW_LINE + "Enter your guess: ";
    private boolean isCompleted;
    private String output = SELECTION_MODE_MESSAGE;
    private int answer;
    private boolean isSinglePlayerGameMode;

    public AppModel(PositiveIntegerGenerator randomGenerator) {
        this.isCompleted = false;
        answer = randomGenerator.generateLessThanEqualsToHundred();
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
        } else if(input == "1") {
            isSinglePlayerGameMode = true;
            this.output = SINGLE_PLAYER_START_MESSAGE;
        } else if(input == "3"){
            isCompleted = true;
        }
    }

    private void singlePlayerMode(String input) {
        if (Integer.parseInt(input) < answer) {
            output = "Your guess is too low." + NEW_LINE + "Enter your guess: ";
        } else if (Integer.parseInt(input) > answer) {
            output = "Your guess is too high." + NEW_LINE + "Enter your guess: ";
        }

    }
}
