package numberGuessing.console;

import numberGuessing.PositiveIntegerGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AppModel {

    public static final String NEW_LINE = System.lineSeparator();
    public static final String GAME_MODE_SELECT_MESSAGE = "1: Single player game" + NEW_LINE +
            "2: Multiplayer game" + NEW_LINE + "3: Exit" + NEW_LINE + "Enter selection: ";
    private final PositiveIntegerGenerator randomGenerator;
    private boolean isCompleted;
    private TextOutput textOutput;
    private Processor processor;

    interface Processor {
        Processor run(String input);
    }

    public AppModel(PositiveIntegerGenerator generator) {
        randomGenerator = generator;
        isCompleted = false;
        textOutput = new TextOutput(GAME_MODE_SELECT_MESSAGE);
        processor = this::selectMode;
    }

    public boolean isCompleted() {
        return isCompleted;
    }


    public void processInput(String input) {
        processor = processor.run(input);
    }

    private void printLines(String... message) {
        textOutput.printLines(message);
    }

    public String flushOutput() {
        return textOutput.flushOutput();
    }

    private Processor selectMode(String input) {
        if(input == "1") {
            int answer = randomGenerator.generateLessThanOrEqualToHundred();
            printLines("Single player game", "I'm thinking of a number between 1 and 100.", "Enter your guess: ");
            return singleGameProcess(answer, 1);
        } else if(input == "2") {
            printLines("Multiplayer game", "Enter player names separated with commas: ");
            return multiGameStartProcessor();
        } else {
            isCompleted = true;
            return null;
        }
    }

    private Processor multiGameStartProcessor() {
        return input -> {
            List<String> players = Arrays.stream(input.split(", ")).map(String::trim).collect(Collectors.toList());
            printLines("I'm thinking of a number between 1 and 100.", "Enter " + players.get(0)+ "'s guess:");
            return multiGameProcess(players, 1);
        };
    }

    private Processor multiGameProcess(List<String> players, int tries) {
        String currentPlayer = players.get((tries - 1) % players.size());
        String nextPlayer = players.get((tries) % players.size());
        return input -> {
            int answer = randomGenerator.generateLessThanOrEqualToHundred();
            if(Integer.parseInt(input) < answer) {
                printLines(currentPlayer + " guess is too low.", "Enter " + nextPlayer + "'s guess:");
            } else if(Integer.parseInt(input) > answer) {
                printLines(currentPlayer+ " guess is too high.", "Enter " + nextPlayer + "'s guess:");
            } else {
                printLines("Correct! "+ currentPlayer + " wins!!!!!!!!!!", GAME_MODE_SELECT_MESSAGE);
                return this::selectMode;
            }
           return multiGameProcess(players, tries+1);
        };
    }

    private Processor singleGameProcess(int answer, int tries) {
        return input -> {
            if (Integer.parseInt(input) < answer) {
                printLines("Your guess is too low.", "Enter your guess: ");
                return singleGameProcess(answer, tries + 1);
            } else if (Integer.parseInt(input) > answer) {
                printLines("Your guess is too high.", "Enter your guess: ");
                return singleGameProcess(answer, tries + 1);
            } else {
                printLines("Correct! " + tries + (tries == 1 ? " guess." : " guesses."), GAME_MODE_SELECT_MESSAGE);
                return this::selectMode;
            }
        };
    }
}
