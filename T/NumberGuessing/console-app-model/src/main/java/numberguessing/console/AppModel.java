package numberguessing.console;

import numberguessing.PositiveIntegerGenerator;

public class AppModel {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String SELECT_MODE_MESSAGE = "1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE +
            "3: Exit" + NEW_LINE + "Enter selection: ";
    private final PositiveIntegerGenerator randomGenerator;
    private String output;
    private boolean completed;
    private int answer;
    private boolean isSinglePlayerMode;
    private Processor processor;

    interface Processor {
        Processor run(String input); /** 입력을 받아서 처리한 다음에 다음 Processor 를 반환하 는 식으로 */
    }

    public AppModel(PositiveIntegerGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
        completed = false;
        isSinglePlayerMode = false;
        output = SELECT_MODE_MESSAGE;
        processor = this::processModeSelection;
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
        if (input.equals("1")) {
            isSinglePlayerMode = true;
            answer = randomGenerator.generateLessThanOrEqualToHundred();
            output = "Single player game Start!" + NEW_LINE + "I'm thinking of a number between 1 and 100." + NEW_LINE + "Enter your guess: ";
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
                return getSinglePlayGameProcessor(tries +1 );
            } else if (Integer.parseInt(input) > answer) {
                output = "Your guess is too high." + NEW_LINE + "Enter your guess: ";
                return getSinglePlayGameProcessor(tries +1 );
            } else {
                output = "Correct! " + tries + (tries == 1 ? " guess." : " guesses.") + NEW_LINE + SELECT_MODE_MESSAGE;
                isSinglePlayerMode = false;
                return this::processModeSelection;
            }
        };
    }

}
