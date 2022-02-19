package numberguessing.console;

import numberguessing.PositiveIntegerGenerator;

import java.util.stream.Stream;

public final class AppModel {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SELECT_MODE_MESSAGE = "1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE +
            "3: Exit" + NEW_LINE + "Enter selection: ";

    interface Processor {
        Processor run(String input);
    }

    private final PositiveIntegerGenerator generator;
    private final StringBuffer outputBuffer;
    boolean completed;
    private Processor processor;

    public AppModel(PositiveIntegerGenerator generator) {
        this.generator = generator;
        completed = false;
        outputBuffer = new StringBuffer(SELECT_MODE_MESSAGE); // 초기화
        processor = this::processModeSelection;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String flushOutput() {
        String flushOutput = outputBuffer.toString();
        outputBuffer.setLength(0); // 버퍼 비워주기
        return flushOutput;
    }

    public void processInput(String input) { // 프로세서를 실행하고 결과값으로 프로세스를 다시 교체하는 역할로 재구현
        processor = processor.run(input);
    }

    private void println(String message) {
        outputBuffer.append(message + NEW_LINE);
    }

    private void print(String message) {
        outputBuffer.append(message);
    }

    private Processor processModeSelection(String input) {
        if(input.equals("1")) {
            outputBuffer.append("Single player game" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                    + NEW_LINE + "Enter your guess: ");
            int answer = generator.generateLessThanOrEqualToHundred();
            return getSinglePlayerGameProcessor(answer, 1);
        } else if(input.equals("2")) {
            println("Multiplayer game");
            print("Enter player names separated with commas: ");
            return startMultiPlayerGame();
        } else {
            completed = true;
            return null;
        }
    }


    private Processor startMultiPlayerGame() {
        return input -> {
            Object[] players = Stream.of(input.split(",")).map(String::trim).toArray(); // 플레이어 이름 옆에 공백이 하나 더 들어가서 처리(CsvSource 에 들어간 배열값을 자세히보라), toArray의 반환타입이 Object이기 때문에 반환타입 수정.
            outputBuffer.append("I'm thinking of a number between 1 and 100.");
            int answer = generator.generateLessThanOrEqualToHundred();
            return getMultiPlayerGameProcessor(players, answer, 1);
        };
    }

    private Processor getMultiPlayerGameProcessor(Object[] players, int answer, int tries) {
        Object player = players[(tries - 1) % players.length];
        outputBuffer.append("Enter " + player + "'s guess: ");
        return input -> {
            int guess = Integer.parseInt(input);
            if(answer > guess) {
                outputBuffer.append(player + "'s guess is too low." + NEW_LINE);
            } else if (answer < guess) {
                outputBuffer.append(player + "'s guess is too high." + NEW_LINE);
            } else {
                outputBuffer.append("Correct! ");
                outputBuffer.append(player + " wins." + NEW_LINE);
                outputBuffer.append(SELECT_MODE_MESSAGE);
                return input1 -> processModeSelection(input1);
            }
            return getMultiPlayerGameProcessor(players, answer, tries + 1); // input : 어떤 입력값을 받아서 처리 결과로 멀티 프로세서를 만들도록 하는 구조
        };
    }

    private Processor getSinglePlayerGameProcessor(int answer, int tries) {
        return input -> {
            int guess = Integer.parseInt(input);
            if (guess < answer) {
                outputBuffer.append("Your guess is too low." + NEW_LINE + "Enter your guess: ");
                return getSinglePlayerGameProcessor(answer, tries + 1);
            } else if (guess > answer) {
                outputBuffer.append("Your guess is too high." + NEW_LINE + "Enter your guess: ");
                return getSinglePlayerGameProcessor(answer, tries + 1);
            } else {
                outputBuffer.append("Correct! " + tries + (tries == 1 ? " guess." : " guesses.") + NEW_LINE + SELECT_MODE_MESSAGE);
                return this::processModeSelection;
            }
        };
    }

}
