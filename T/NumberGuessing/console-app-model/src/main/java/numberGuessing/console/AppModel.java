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
    private Processor processor;

    interface Processor {
        Processor run(String input);
    }

    public AppModel(PositiveIntegerGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
        isCompleted = false;
        output = SELECT_MODE_MESSAGE;
        processor = this::selectionGameMode;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String flushOutput() {
        return output;
    }

    public void processInput(String input) {
        processor = processor.run(input);
    }

    private Processor selectionGameMode(String input) {
        if (input == "1") {
            int answer = randomGenerator.generateLessThanEqualsToHundred();
            output = SINGLE_PLAYER_START_MESSAGE;
            return getSinglePlayGameProcessor(1, answer);
        } else {
            isCompleted = true;
            return null;
        }
    }

    private Processor getSinglePlayGameProcessor(int tries, int answer) {
        return input -> {
            if (Integer.parseInt(input) < answer) {
                output = "Your guess is too low." + NEW_LINE + "Enter your guess: ";
                return getSinglePlayGameProcessor(tries+1, answer);
            } else if (Integer.parseInt(input) > answer) {
                output = "Your guess is too high." + NEW_LINE + "Enter your guess: ";
                return getSinglePlayGameProcessor(tries+1, answer);
            } else {
                output = "Correct! + " + tries + (tries == 1 ? " guess." : " guesses.") + NEW_LINE + SELECT_MODE_MESSAGE;
                return this::selectionGameMode;
            }
        };
    }
}
