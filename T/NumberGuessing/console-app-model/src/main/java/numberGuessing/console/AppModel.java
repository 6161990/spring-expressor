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
    private StringBuffer outputBuffer;
    private Processor processor;

    interface Processor {
        Processor run(String input);
    }

    public AppModel(PositiveIntegerGenerator generator) {
        randomGenerator = generator;
        isCompleted = false;
        outputBuffer = new StringBuffer(GAME_MODE_SELECT_MESSAGE);
        processor = this::selectMode;
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

    private Processor selectMode(String input) {
        if(input == "1") {
            int answer = randomGenerator.generateLessThanOrEqualToHundred();
            outputBuffer.append("Single player game" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                    + NEW_LINE + "Enter your guess: ");
            return singleGameProcess(answer, 1);
        } else if(input == "2") {
            int answer = randomGenerator.generateLessThanOrEqualToHundred();
            outputBuffer.append("Multiplayer game" + NEW_LINE + "Enter player names separated with commas: ");
            return multiGameStartProcessor();
        } else {
            isCompleted = true;
            return null;
        }
    }

    private Processor multiGameStartProcessor() {
        return input -> {
            List<String> players = Arrays.stream(input.split(", ")).map(String::trim).collect(Collectors.toList());
            outputBuffer.append("I'm thinking of a number between 1 and 100." + NEW_LINE);
            return multiGameProcess(players, 1);
        };
    }

    private Processor multiGameProcess(List<String> players, int tries) {
        outputBuffer.append("Enter " + players.get((tries-1) % players.size())+ "'s guess:");
        return input -> multiGameProcess(players, tries+1);
    }

    private Processor singleGameProcess(int answer, int tries) {
        return input -> {
            if (Integer.parseInt(input) < answer) {
                outputBuffer.append("Your guess is too low." + NEW_LINE + "Enter your guess: ");
                return singleGameProcess(answer, tries + 1);
            } else if (Integer.parseInt(input) > answer) {
                outputBuffer.append("Your guess is too high." + NEW_LINE + "Enter your guess: ");
                return singleGameProcess(answer, tries + 1);
            } else {
                outputBuffer.append("Correct! ");
                outputBuffer.append(tries + (tries == 1 ? " guess." : " guesses.") + NEW_LINE + GAME_MODE_SELECT_MESSAGE);
                return this::selectMode;
            }
        };
    }
}
