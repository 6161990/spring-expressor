package numberGuessing.console;

import numberGuessing.PositiveIntegerGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AppModel {

    public static final String NEW_LINE = System.lineSeparator();
    public static final String GAME_MODE_SELECT_MESSAGE = "1: Single player game" + NEW_LINE +
            "2: Multiplayer game" + NEW_LINE + "3: Exit" + NEW_LINE + "Enter selection: ";
    public static final String SINGLE_GAME_START_MESSAGE = "Single player game" + NEW_LINE + "I'm thinking of a number between 1 and 100."
            + NEW_LINE + "Enter your guess: ";
    public static final String MULTI_GAME_START_MESSAGE = "Multiplayer game" + NEW_LINE + "Enter player names separated with commas: ";
    private PositiveIntegerGenerator randomGenerator;
    private final StringBuffer outputBuffer;
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

    private void print(String message) {
        outputBuffer.append(message);
    }

    private Processor processSelectGameMode(String input) {
        if(input == "1"){
            print(SINGLE_GAME_START_MESSAGE);
            int answer = randomGenerator.generateLessThanEqualsToHundred();
            return getProcessSingleModeGame(1, answer);
        }else if(input == "2"){
            print(MULTI_GAME_START_MESSAGE);
            return startMultiModeGame();
        } else {
            isCompleted = true;
            return null;
        }
    }


    private Processor startMultiModeGame() {
        return input -> {
            List<String> players = Arrays.stream(input.split(",")).map(String::trim).collect(Collectors.toList());
            print("I'm thinking of a number between 1 and 100.");
            return getProcessMultiModeGame(players, 1);
        };
    }

    private Processor getProcessMultiModeGame(List<String> players, int tries) {
        String player = players.get((tries - 1) % players.size());
        print("Enter " + player + "'s guess:");
        return input -> {
            int answer = randomGenerator.generateLessThanEqualsToHundred();
            if(Integer.parseInt(input) < answer) {
                println(player + " guess is too low.");
            } else if(Integer.parseInt(input) > answer) {
                println(player + " guess is too high.");
            } else {
                print("Correct! ");
                println(player + " wins!!!!!!!!!!");
                print(GAME_MODE_SELECT_MESSAGE);
                return this::processSelectGameMode;
            }
            return getProcessMultiModeGame(players, tries + 1);
        };
    }

    private Processor getProcessSingleModeGame(int tries, int answer) {
        return input -> {
            if (Integer.parseInt(input) < answer) {
                println("Your guess is too low.");
                print("Enter your guess: ");
                return getProcessSingleModeGame(tries+1, answer);
            } else if (Integer.parseInt(input) > answer) {
                println("Your guess is too high.");
                print("Enter your guess: ");
                return getProcessSingleModeGame(tries+1, answer);
            } else {
                println("Correct! " + tries + (tries == 1 ? " guess." : " guesses."));
                print(GAME_MODE_SELECT_MESSAGE);
                return this::processSelectGameMode;
            }
        };
    }

    private void println(String message) {
        outputBuffer.append(message + NEW_LINE);
    }
}
