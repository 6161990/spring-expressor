package numberGuessing.console;

import numberGuessing.PositiveIntegerGenerator;

public class AppModel {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String SELECT_MODE_MESSAGE = "1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE +
            "3: Exit" + NEW_LINE + "Enter selection: ";
    private boolean completed;
    private String output;
    private static int answer = 0;

    public AppModel(PositiveIntegerGenerator randomGenerator) {
        completed = false;
        output = SELECT_MODE_MESSAGE;
        answer = randomGenerator.generateLessThanEqualToHundred();
    }

    public boolean isCompleted() {
        return completed;
    }

    public String flushOutput() {
        return output;
    }

    public void processInput(String input) {
        if(input.equals("1")){
            output ="Single player game Start!" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                                     + NEW_LINE + "Enter your guess: ";
            return;
        } else if(input.equals("3")){
            completed = true;
            return;
        }

        if(Integer.parseInt(input) < answer) {
            output = "Your guess is too low." + NEW_LINE + "Enter your guess: ";
        }
    }
}
