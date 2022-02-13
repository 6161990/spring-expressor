package numberguessing.console;

import numberguessing.PositiveIntegerGenerator;

public final class AppModel {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SELECT_MODE = "1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE +
            "3: Exit" + NEW_LINE + "Enter selection: ";

    interface Processor {
        Processor run(String input);
    }

    private final PositiveIntegerGenerator generator;
    boolean completed;
    private String output;
    private int answer;
    private boolean singlePlayerMode;
    private int tries;

    public AppModel(PositiveIntegerGenerator generator) {
        this.generator = generator;
        completed = false;
        output = SELECT_MODE;
        singlePlayerMode = false;
        tries = 0;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String flushOutput() {
        return output;
    }

    public void processInput(String input) {
        if(singlePlayerMode){
            processSinglePlayerGame(input);
        } else {
            processModelSelection(input);
        }
    }

    private void processSinglePlayerGame(String input) {
        tries++;
        int guess = Integer.parseInt(input);
        if(guess < answer){
            output = "Your guess is too low." + NEW_LINE + "Enter your guess: ";
        } else if (guess > answer) {
            output = "Your guess is too high." + NEW_LINE + "Enter your guess: ";
        } else {
            output = "Correct! " + tries + (tries == 1 ? " guess." : " guesses.") + NEW_LINE + SELECT_MODE;
            singlePlayerMode = false;
        }
    }

    private void processModelSelection(String input) {
        if(input.equals("1")) {
            output = "Single player game" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                    + NEW_LINE + "Enter your guess: ";
            singlePlayerMode = true;
            answer = generator.generateLessThanOrEqualToHundred();
        } else {
            completed = true;
        }
    }
}
