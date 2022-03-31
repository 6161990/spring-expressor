package numberGuessing.console;

import numberGuessing.PositiveIntegerGenerator;

public class AppModel {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String SELECT_MODE_MESSAGE = "1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE +
            "3: Exit" + NEW_LINE + "Enter selection: ";
    private boolean completed;
    private String output;
    private static int answer = 0;
    private int tries;
    private boolean isSinglePlayerMode;

    public AppModel(PositiveIntegerGenerator randomGenerator) {
        completed = false;
        output = SELECT_MODE_MESSAGE;
        answer = randomGenerator.generateLessThanEqualToHundred();
        tries = 0;
    }

    public boolean isCompleted() {
        isSinglePlayerMode = false;
        return completed;
    }

    public String flushOutput() {
        return output;
    }

    public void processInput(String input) {
        if(isSinglePlayerMode) {
            processSinglePlayerGame(input);
        } else {
            processModeSelection(input);
        }

    }

    private void processModeSelection(String input) {
        if(input.equals("1")){
            isSinglePlayerMode = true;
            output ="Single player game Start!" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                    + NEW_LINE + "Enter your guess: ";
        } else {
            completed = true;
        }
    }

    private void processSinglePlayerGame(String input) {
        tries++;
        if(Integer.parseInt(input) < answer) {
            output = "Your guess is too low." + NEW_LINE + "Enter your guess: ";
        } else if(Integer.parseInt(input) > answer) {
            output = "Your guess is too high." + NEW_LINE + "Enter your guess: ";
        } else {
            if(tries == 1){
                output = "Correct! " + "1 guess." + NEW_LINE;
            } else{
                output = "Correct! " + tries + " guesses." + NEW_LINE;
            }
        }
    }
}
