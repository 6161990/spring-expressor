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

    private final TextOutput output;
    private final PositiveIntegerGenerator generator;
    boolean completed;
    private Processor processor;

    public AppModel(PositiveIntegerGenerator generator) { // 생성자를 통한 입력 : 공개된 인터페이스를 통한 입력 - 직접입력
        output = new TextOutput(SELECT_MODE_MESSAGE);
        this.generator = generator;
        completed = false;
        processor = this::processModeSelection;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String flushOutput() {
        return output.flushOutput();
    }

    public void processInput(String input) { // 프로세서를 실행하고 결과값으로 프로세스를 다시 교체하는 역할로 재구현
        processor = processor.run(input);
    }

    private void printLines(String... lines) {
        output.printLines(lines);
    }

    private Processor processModeSelection(String input) {
        if(input.equals("1")) {
            int answer = generator.generateLessThanOrEqualToHundred(); // 직접입력으로 받은 generator의 인터페이스를 출력하고, answer는 간접 입력을 받고있다.
            printLines("Single player game", "I'm thinking of a number between 1 and 100.", "Enter your guess: ");
            return getSinglePlayerGameProcessor(answer, 1);
        } else if(input.equals("2")) {
            printLines("Multiplayer game", "Enter player names separated with commas: ");
            return startMultiPlayerGame();
        } else {
            completed = true;
            return null;
        }
    }


    private Processor startMultiPlayerGame() {
        return input -> {
            Object[] players = Stream.of(input.split(",")).map(String::trim).toArray(); // 플레이어 이름 옆에 공백이 하나 더 들어가서 처리(CsvSource 에 들어간 배열값을 자세히보라), toArray의 반환타입이 Object이기 때문에 반환타입 수정.
            int answer = generator.generateLessThanOrEqualToHundred();
            printLines("I'm thinking of a number between 1 and 100.", "Enter " + players[0] + "'s guess: ");
            return getMultiPlayerGameProcessor(players, answer, 1);
        };
    }

    private Processor getMultiPlayerGameProcessor(Object[] players, int answer, int tries) {
        return input -> {
            int guess = Integer.parseInt(input);

            Object currentPlayer = players[(tries - 1) % players.length];
            Object nextPlayer = players[tries % players.length];

            if(answer > guess) {
                printLines(currentPlayer + "'s guess is too low.", "Enter " + nextPlayer + "'s guess: ");
                return getMultiPlayerGameProcessor(players, answer, tries + 1); // input : 어떤 입력값을 받아서 처리 결과로 멀티 프로세서를 만들도록 하는 구조
            } else if (answer < guess) {
                printLines(currentPlayer + "'s guess is too high.", "Enter " + nextPlayer + "'s guess: ");
                return getMultiPlayerGameProcessor(players, answer, tries + 1);
            } else {
                printLines("Correct! "+ currentPlayer + " wins.", SELECT_MODE_MESSAGE);
                return this::processModeSelection;
            }

        };
    }

    private Processor getSinglePlayerGameProcessor(int answer, int tries) {
        return input -> {
            int guess = Integer.parseInt(input);
            if (guess < answer) {
                printLines("Your guess is too low.", "Enter your guess: ");
                return getSinglePlayerGameProcessor(answer, tries + 1);
            } else if (guess > answer) {
                printLines("Your guess is too high.", "Enter your guess: ");
                return getSinglePlayerGameProcessor(answer, tries + 1);
            } else {
                printLines("Correct! " + tries + (tries == 1 ? " guess." : " guesses."), SELECT_MODE_MESSAGE);
                return this::processModeSelection;
            }
        };
    }

}
