package numberGuessing.console;

import numberGuessing.PositiveIntegerGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AppModel {

    public static final String NEW_LINE = System.lineSeparator();
    public static final String GAME_MODE_SELECT_MESSAGE = "1: Single player game" + NEW_LINE +
            "2: Multiplayer game" + NEW_LINE + "3: Exit" + NEW_LINE + "Enter selection: ";

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

    private Processor processSelectGameMode(String input) {
        if(input == "1"){
            println("Single player game" );
            println("I'm thinking of a number between 1 and 100.");
            print("Enter your guess: ");
            int answer = randomGenerator.generateLessThanEqualsToHundred();
            return getProcessSingleModeGame(1, answer);
        }else if(input == "2"){
            println("Multiplayer game");
            print("Enter player names separated with commas:");
            return startMultiGameMode();
        } else{
            isCompleted = true;
            return null;
        }
    }

    private Processor startMultiGameMode() {
        return input -> {
            List<String> players = Arrays.stream(input.split(",")).map(String::trim).collect(Collectors.toList());
            println("I'm thinking of a number between 1 and 100.");
            return getProcessMultiModeGame(players, 1);
        };
    }

    private Processor getProcessMultiModeGame(List<String> players, int tries) {
        String player = players.get((tries - 1) % players.size());
        print("Enter " + player + "'s guess:");
        return input -> {
            int answer = randomGenerator.generateLessThanEqualsToHundred();
            if(Integer.parseInt(input) < answer){
                println(player + " guess is too low.");
            }else if(Integer.parseInt(input) > answer){
                println(player + " guess is too high.");
            } else {
                println("Correct! "+ player + " wins!!!!!!!!!!");
                print(GAME_MODE_SELECT_MESSAGE);
                return this::processSelectGameMode;
            }
            return getProcessMultiModeGame(players, tries + 1);
        };
    }

    private void println(String message) {
        outputBuffer.append(message + System.lineSeparator());
    }

    private void print(String message) {
        outputBuffer.append(message);
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
}
