package numberGuessing.console;

import numberGuessing.PositiveIntegerGenerator;

public class AppModel {

    public static final String NEW_LINE = System.lineSeparator();
    public static final String GAME_SELECT_MESSAGE = "1: Single player game" + NEW_LINE +
            "2: Multiplayer game" + NEW_LINE + "3: Exit" + NEW_LINE + "Enter selection: ";
    public static String SINGLE_GAME_START_MESSAGE = "Single player game" + NEW_LINE + "I'm thinking of a number between 1 and 100."
            + NEW_LINE + "Enter your guess: ";
    private PositiveIntegerGenerator positiveIntegerGenerator;
    private Processor processor;
    private String output;
    private boolean isCompleted;

    interface Processor {
        Processor run(String input);
    }

    public AppModel(PositiveIntegerGenerator positiveIntegerGenerator) {
        this.positiveIntegerGenerator = positiveIntegerGenerator;
        output = GAME_SELECT_MESSAGE;
        isCompleted = false;
        processor = this::selectGameMode;
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

    private Processor selectGameMode(String input) {
        if(input == "1") {
            int answer = positiveIntegerGenerator.generateLessThanEqualsToHundred();
            output = SINGLE_GAME_START_MESSAGE;
            return getProcessSingleGameMode(1, answer);
        }else {
            isCompleted = true;
            return null;
        }
    }

    private Processor getProcessSingleGameMode(int tries, int answer) {
        return input -> {
            if (Integer.parseInt(input) < answer) {
                output = "Your guess is too low." + NEW_LINE + "Enter your guess: ";
                return getProcessSingleGameMode(tries+1, answer);
            } else if (Integer.parseInt(input) > answer) {
                output = "Your guess is too high." + NEW_LINE + "Enter your guess: ";
                return getProcessSingleGameMode(tries+1, answer);
            } else {
                output = "Correct! + " + tries + (tries == 1 ? " guess." : " guesses.") + NEW_LINE + GAME_SELECT_MESSAGE;
                return this::selectGameMode;
            }
        };
    }
}
