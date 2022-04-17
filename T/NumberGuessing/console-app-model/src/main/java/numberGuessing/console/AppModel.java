package numberGuessing.console;

import numberGuessing.PositiveIntegerGenerator;

public class AppModel {

    public static final String NEW_LINE = System.lineSeparator();
    public static final String GAME_MODE_SELECT_MESSAGE = "1: Single player game" + NEW_LINE +
            "2: Multiplayer game" + NEW_LINE + "3: Exit" + NEW_LINE + "Enter selection: ";
    private boolean isCompleted;
    private String output;
    private int answer;
    private boolean isSingleGameProcess;
    private int tries = 0;

    public AppModel(PositiveIntegerGenerator generator) {
        isCompleted = false;
        output = GAME_MODE_SELECT_MESSAGE;
        answer = generator.generateLessThanOrEqualToHundred();
        isSingleGameProcess = false;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String flushOutput() {
        return output;
    }

    public void processInput(String input) {
        if(isSingleGameProcess){
            singleGameProcess(input);
        } else {
            selectMode(input);
        }
    }

    private void selectMode(String input) {
        if(input == "1") {
            output = "Single player game" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                    + NEW_LINE + "Enter your guess: ";
            isSingleGameProcess = true;
        } else if(input == "3"){
            isCompleted = true;
        }
    }

    private void singleGameProcess(String input) {
        tries++;
        if(Integer.parseInt(input) < answer) {
            output = "Your guess is too low." + NEW_LINE + "Enter your guess: ";
        } else if(Integer.parseInt(input) > answer) {
            output = "Your guess is too high." + NEW_LINE + "Enter your guess: ";
        } else {
            output = "Correct! ";
            output += tries + (tries == 1? " guess." : " guesses.") + NEW_LINE + GAME_MODE_SELECT_MESSAGE;
        }
    }
}
