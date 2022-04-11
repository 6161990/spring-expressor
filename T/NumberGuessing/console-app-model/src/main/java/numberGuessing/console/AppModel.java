package numberGuessing.console;

import numberGuessing.PositiveIntegerGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AppModel {

    public static final String NEW_LINE = System.lineSeparator();
    public static final String GAME_MODE_SELECT_MESSAGE = "1: Single player game" + NEW_LINE +
            "2: Multiplayer game" + NEW_LINE + "3: Exit" + NEW_LINE + "Enter selection: ";
    private StringBuffer outputBuffer;
    private PositiveIntegerGenerator randomGenerator;
    private boolean isCompleted;
    private Processor processor;

    interface Processor {
        Processor run(String input);
    }

    public AppModel(PositiveIntegerGenerator randomGenerator) {
        outputBuffer = new StringBuffer(GAME_MODE_SELECT_MESSAGE);
        isCompleted = false;
        this.randomGenerator = randomGenerator;
        processor = this::processSelectGameMode;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String flushOutput() {
        String output = outputBuffer.toString();
        outputBuffer.setLength(0);
        return output;
    }

    public void processInput(String input) {
       processor = processor.run(input);
    }

    private Processor processSelectGameMode(String input) {
        if(input == "1") {
            printLines("Single player game", "I'm thinking of a number between 1 and 100.", "Enter your guess: ");
            int answer = randomGenerator.generateLessThanEqualsToHundred();
            return getProcessSingleModeGame(1, answer);
        }else if(input == "2"){
            printLines("Multiplayer game", "Enter player names separated with commas: ");
            return startMultiModeGame();
        }else {
            isCompleted = true;
            return null;
        }
    }

    private void printLines(String... lines) {
        outputBuffer.append(String.join(System.lineSeparator(), lines));
    }

    private void println(String message) {
        outputBuffer.append(message + System.lineSeparator());
    }

    private void print(String message) {
        outputBuffer.append(message);
    }


    private Processor startMultiModeGame() {
        return input -> {
            String[] players = input.split(", ");
            printLines("I'm thinking of a number between 1 and 100.",  "Enter " + players[0] + "'s guess: ");
            return getProcessMultiModeGame(players, 1);
        };
    }

    private Processor getProcessMultiModeGame(String[] players, int tries) {
        String currentPlayer = players[(tries - 1) % players.length];
        String nextPlayer = players[tries % players.length];
        return input -> {
            int answer = randomGenerator.generateLessThanEqualsToHundred();
            if (Integer.parseInt(input) < answer) {
                printLines(currentPlayer + " guess is too low.", "Enter " + nextPlayer + "'s guess:");
            }else if(Integer.parseInt(input) > answer) {
                printLines(currentPlayer + " guess is too high.", "Enter " + nextPlayer + "'s guess:");
            }else {
                printLines("Correct! " + currentPlayer + " wins!!!!!!!!!!", GAME_MODE_SELECT_MESSAGE);
                return this::processSelectGameMode;
            }
            return getProcessMultiModeGame(players, tries + 1);
        };
    }

    private Processor getProcessSingleModeGame(int tries, int answer) {
        return input -> {
            if (Integer.parseInt(input) < answer) {
                printLines("Your guess is too low.", "Enter your guess: ");
                return getProcessSingleModeGame(tries+1, answer);
            } else if (Integer.parseInt(input) > answer) {
                printLines("Your guess is too high.", "Enter your guess: ");
                return getProcessSingleModeGame(tries+1, answer);
            } else {
                printLines("Correct! " + tries + (tries == 1 ? " guess." : " guesses."), GAME_MODE_SELECT_MESSAGE);
                return this::processSelectGameMode;
            }
        };
    }
}
