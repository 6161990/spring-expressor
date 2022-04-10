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
    private final TextOutput textOutput;
    private boolean isCompleted;
    private Processor processor;

    interface Processor {
        Processor run(String input);
    }

    public AppModel(PositiveIntegerGenerator randomGenerator) {
        textOutput = new TextOutput(GAME_MODE_SELECT_MESSAGE);
        isCompleted = false;
        this.randomGenerator = randomGenerator;
        processor = this::processSelectGameMode;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String flushOutput() {
        return textOutput.flushOutput();
    }

    public void processInput(String input) {
       processor = processor.run(input);
    }

    private Processor processSelectGameMode(String input) {
        if(input == "1"){
            printLines("Single player game", "I'm thinking of a number between 1 and 100.", "Enter your guess: ");
            int answer = randomGenerator.generateLessThanEqualsToHundred();
            return getProcessSingleModeGame(1, answer);
        }else if(input == "2"){
            printLines("Multiplayer game", "Enter player names separated with commas:");
            return startMultiGameMode();
        } else{
            isCompleted = true;
            return null;
        }
    }

    private Processor startMultiGameMode() {
        return input -> {
            List<String> players = Arrays.stream(input.split(",")).map(String::trim).collect(Collectors.toList());
            printLines("I'm thinking of a number between 1 and 100.", "Enter " + players.get(0) + "'s guess:");
            int answer = randomGenerator.generateLessThanEqualsToHundred();
            return getProcessMultiModeGame(players, answer ,1);
        };
    }

    private Processor getProcessMultiModeGame(List<String> players, int answer, int tries) {
        String currentPlayer = players.get((tries - 1) % players.size());
        String nextPlayer = players.get(tries % players.size());
        return input -> {
            if(Integer.parseInt(input) < answer){
                printLines(currentPlayer + " guess is too low.", "Enter " + nextPlayer + "'s guess:");
            }else if(Integer.parseInt(input) > answer){
                printLines(currentPlayer + " guess is too high.", "Enter " + nextPlayer + "'s guess:");
            } else {
                printLines("Correct! "+ currentPlayer + " wins!!!!!!!!!!", GAME_MODE_SELECT_MESSAGE);
                return this::processSelectGameMode;
            }
            return getProcessMultiModeGame(players, answer, tries + 1);
        };
    }

    private void printLines(String... lines) {
        textOutput.printLines(lines);
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
