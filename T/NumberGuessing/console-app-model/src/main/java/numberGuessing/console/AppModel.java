package numberGuessing.console;

import numberGuessing.PositiveIntegerGenerator;

public class AppModel {

    public static final String NEW_LINE = System.lineSeparator();
    public static final String GAME_MODE_SELECT_MESSAGE = "1: Single player game" + NEW_LINE +
            "2: Multiplayer game" + NEW_LINE + "3: Exit" + NEW_LINE + "Enter selection: ";
    private final PositiveIntegerGenerator randomGenerator;
    private boolean isCompleted;
    private String output;
    private Processor processor;

    interface Processor {
        Processor run(String input);
    }

    public AppModel(PositiveIntegerGenerator generator) {
        randomGenerator = generator;
        isCompleted = false;
        output = GAME_MODE_SELECT_MESSAGE;
        processor = this::selectMode;
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

    private Processor selectMode(String input) {
        if(input == "1") {
            int answer = randomGenerator.generateLessThanOrEqualToHundred();
            output = "Single player game" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                    + NEW_LINE + "Enter your guess: ";
            return singleGameProcess(answer , 1);
        } else {
            isCompleted = true;
            return null;
        }
    }

    private Processor singleGameProcess(int answer, int tries) {
        return input -> {
            if (Integer.parseInt(input) < answer) {
                output = "Your guess is too low." + NEW_LINE + "Enter your guess: ";
                return singleGameProcess(answer, tries + 1);
            } else if (Integer.parseInt(input) > answer) {
                output = "Your guess is too high." + NEW_LINE + "Enter your guess: ";
                return singleGameProcess(answer, tries + 1);
            } else {
                output = "Correct! ";
                output += tries + (tries == 1 ? " guess." : " guesses.") + NEW_LINE + GAME_MODE_SELECT_MESSAGE;
                return this::selectMode;
            }
        };
    }
}
