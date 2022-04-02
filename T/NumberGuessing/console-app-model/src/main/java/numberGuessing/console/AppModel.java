package numberGuessing.console;

import numberGuessing.PositiveIntegerGenerator;

public class AppModel {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String SELECT_MODE_MESSAGE = "1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE +
            "3: Exit" + NEW_LINE + "Enter selection: ";
    private final PositiveIntegerGenerator randomGenerator;
    private boolean completed;
    private String output;
    private static int answer = 0;
    private boolean isSinglePlayerMode;
    private Processor processor;

    interface Processor {
        Processor run(String input);
    }

    public AppModel(PositiveIntegerGenerator randomGenerator) {
        processor = this::processModeSelection;
        this.randomGenerator = randomGenerator;
        completed = false;
        output = SELECT_MODE_MESSAGE;
        isSinglePlayerMode = false;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String flushOutput() {
        return output;
    }

    public void processInput(String input) {
        processor = processor.run(input);
    }

    private Processor processModeSelection(String input) {
        if(input.equals("1")){
            isSinglePlayerMode = true;
            answer = randomGenerator.generateLessThanEqualToHundred();
            output ="Single player game Start!" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                    + NEW_LINE + "Enter your guess: ";
            return getSinglePlayGameProcessor(1);
        } else {
            completed = true;
            return null;
        }
    }

    private Processor getSinglePlayGameProcessor(int tries) {
        return input -> {
            if (Integer.parseInt(input) < answer) {
                output = "Your guess is too low." + NEW_LINE + "Enter your guess: ";
                return getSinglePlayGameProcessor(tries + 1);
            } else if (Integer.parseInt(input) > answer) {
                output = "Your guess is too high." + NEW_LINE + "Enter your guess: ";
                return getSinglePlayGameProcessor(tries + 1);
            } else {
                output = "Correct! " + tries + (tries == 1 ? " guess." : " guesses.") + NEW_LINE + SELECT_MODE_MESSAGE;
                isSinglePlayerMode = false;
                return this::processModeSelection;
            }
        };
    }
}
