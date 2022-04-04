package numberGuessing.console;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class AppModel_specs {

    public static final String NEW_LINE = System.lineSeparator();
    public static final String GAME_MODE_SELECT_MESSAGE = "1: Single player game" + NEW_LINE +
           "2: Multiplayer game" + NEW_LINE + "3: Exit" + NEW_LINE + "Enter selection: ";
    public static final String SINGLE_GAME_START_MESSAGE = "Single player game" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                            + NEW_LINE + "Enter your guess: ";

    @DisplayName("sut 가 처음 초기화되면 isCompleted 가 false 다.")
    @Test
    void sut_is_incompleted_when_it_is_initialized() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));

        boolean actual = sut.isCompleted();

        assertThat(actual).isFalse();
    }

    @DisplayName("sut 의 첫 flushOutput 은 게임모드선택 옵션 메세지다.")
    @Test
    void sut_correctly_prints_select_mode_message() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));

        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo(GAME_MODE_SELECT_MESSAGE);
    }


    @DisplayName("sut 진행중 3을 입력값으로 넣으면 sut 는 종료된다")
    @Test
    void sut_correctly_exist() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));

        sut.processInput("3");

        boolean actual = sut.isCompleted();

        assertThat(actual).isTrue();
    }

    @DisplayName("sut 에 싱글게임모드선택 후 게임 시작 메세지가 출력된다")
    @Test
    void sut_correctly_prints_single_player_game_start_message() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("1");

        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo(SINGLE_GAME_START_MESSAGE);
    }

    @DisplayName("싱글 플레이어 게임에서 입력한 정답이 answer 보다 작을 경우 해당 메세지가 출력된다")
    @ParameterizedTest
    @CsvSource({"8, 5", "99, 9", "77,7"})
    void sut_correctly_prints_too_low_message_in_single_player_game(int answer, int guess) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");

        sut.processInput(String.valueOf(guess));
        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo("Your guess is too low." + NEW_LINE + "Enter your guess: ");
    }

    @DisplayName("싱글 플레이어 게임에서 입력한 정답이 answer 보다 클 경우 해당 메세지가 출력된다")
    @ParameterizedTest
    @CsvSource({"1,9", "7, 77", "30, 88"})
    void sut_correctly_prints_too_high_message_in_single_player_game(int answer, int guess) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");

        sut.processInput(String.valueOf(guess));
        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo("Your guess is too high." + NEW_LINE + "Enter your guess: ");
    }

    @DisplayName("싱글 플레이어 게임에서 입력한 정답이 answer 일 때, 해당 메세지가 출력된다")
    @ParameterizedTest
    @ValueSource(ints = {100, 10, 1})
    void sut_correctly_prints_correct_message_in_single_player_game(int answer) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");

        int guess = answer;
        sut.processInput(String.valueOf(guess));
        String actual = sut.flushOutput();

        assertThat(actual).startsWith("Correct! ");
    }

    @DisplayName("싱글 플레이어 게임에서 정답을 맞췄을 때, 총 실패횟수를 알려주는 메세지가 출력된다")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    void sut_correctly_prints_guess_count_if_single_player_game_finished(int fails) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("1");

        for (int i = 0; i < fails; i++) {
            sut.processInput("7");
            sut.flushOutput();
        }

        sut.processInput("50");
        String actual = sut.flushOutput();

        assertThat(actual).contains((fails + 1) + " guesses." + NEW_LINE);
    }

    @DisplayName("싱글 플레이어 게임에서 정답을 한번에 맞췄을 때, 'guesses' 가 아니라 'guess' 로 출력된다")
    @Test
    void sut_correctly_prints_one_guess_if_single_player_game_finished() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("1");
        sut.flushOutput();
        sut.processInput("50");

        String actual = sut.flushOutput();

        assertThat(actual).contains("1 guess." + NEW_LINE);
    }

    @DisplayName("싱글 플레이어 모드가 끝나면 다시 select mode 가 보여진다")
    @Test
    void sut_prints_select_mode_message_if_single_player_game_finished() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("1");
        sut.flushOutput();
        sut.processInput("50");
        String actual = sut.flushOutput();

        assertThat(actual).endsWith(GAME_MODE_SELECT_MESSAGE);
    }

    @DisplayName("싱글 플레이어 모드가 끝나고 돌아간 select mode 에서 exit 를 선택했을 때 sut 는 잘 종료된다")
    @Test
    void sut_returns_to_mode_selection_if_single_player_game_finished() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("1");
        sut.processInput("50");
        sut.flushOutput();

        sut.processInput("3");

        boolean actual = sut.isCompleted();
        assertThat(actual).isTrue();
    }

    @DisplayName("싱글 플레이어 모드는 반복하여 게임을 실행해도 잘 돌아간다")
    @ParameterizedTest
    @ValueSource(strings = {"1, 10, 100"})
    void sut_generates_answer_for_each_game(String source) {
        int[] answers = Stream.of(source.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answers));

        for (int answer: answers) {
            sut.processInput("1");
            sut.flushOutput();
            sut.processInput(String.valueOf(answer));
        }

        String actual = sut.flushOutput();
        assertThat(actual).startsWith("Correct! ");
    }

    @DisplayName("sut 에 다중 플레이어 모드 선택 후 게임 시작 메세지가 출력된다")
    @Test
    void sut_correctly_prints_multiplayer_game_setup_message(){
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("2");

        String actual = sut.flushOutput();
        assertThat(actual).isEqualTo("Multiplayer game" + NEW_LINE + "Enter player names separated with commas: ");
    }

}

/**
 * [Step1. sut 가 처음 초기화되면 isCompleted 가 false 다.(Test)]
 *          PositiveIntegerGeneratorStub 생성한다.
 * [Step2. sut 의 첫 flushOutput 게임모드선택 옵션 메세지다(Test)]
 *   GameModeSelection Message = "1: Single player game" + NEW_LINE +
 *          "2: Multiplayer game" + NEW_LINE + "3: Exit" + NEW_LINE + "Enter selection: "
 * [Step3. sut 진행중 3을 입력값으로 넣으면 sut 는 종료된다(Test)]
 * [Step4. sut 에 싱글게임모드선택 후 게임 시작 메세지가 출력된다(Test)]
 *   SinglePlayerStart Message = "Single player game" + NEW_LINE + "I'm thinking of a number between 1 and 100."
 *                 + NEW_LINE + "Enter your guess: "
 * [Step5. 싱글 플레이어 게임에서 입력한 정답이 answer 보다 작을 경우 해당 메세지가 출력된다(Test)]
 *     TooLowMessage = "Your guess is too low." + NEW_LINE + "Enter your guess: "
 * [Step6. 싱글 플레이어 게임에서 입력한 정답이 answer 보다 클 경우 해당 메세지가 출력된다(Test)]
 *     TooHighMessage = "Your guess is too high." + NEW_LINE + "Enter your guess: "
 * [Step7. 싱글 플레이어 게임에서 입력한 정답이 answer 일 때, 해당 메세지가 출력된다(Test)]
 *          입력값이 게임 모드를 선택하는지, 정답을 입력하는지 알 수 있게 상태 변화를 감지할 수 있는 방법이 필요하다.
 * [Step8. Refactoring]
 * [Step9. 싱글 플레이어 게임에서 정답을 맞췄을 때, 총 실패횟수를 알려주는 메세지가 출력된다(Test)]
 *      FailsCountMessage = "(fails + 1) + " guesses." + NEW_LINE"
 * [Step10. 싱글 플레이어 게임에서 정답을 한번에 맞췄을 때, 'guesses' 가 아니라 'guess' 로 출력된다(Test)]
 * [Step11. Refactoring]
 * [Step12. 싱글 플레이어 모드가 끝나면 다시 select mode 가 보여진다(Test)]
 * [Step13. Refactoring]
 * [Step14. 싱글 플레이어 모드가 끝나고 돌아간 select mode 에서 exit 를 선택했을 때 sut 는 잘 종료된다(Test)]
 * [Step15. 싱글 플레이어 모드는 반복하여 게임을 실행해도 잘 돌아간다(Test)]
 * [Step16. Refactoring 1 - Processor 를 도입하여 sut 루프 를 교체해주는 방식으로 변경한다]
 * [Step17. Refactoring 2 - ModeSelection 에서 1이 입력되었을 때 SinglePlayer 의 processor 를 생성하도록 변경한다]
 * [Step18. Refactoring 3 - tries 필드를 없앤다]
 * [Step19. Refactoring 4 - answer 필드를 없앤다]
 * [Step20. Refactoring 5 - 해당 Refactoring 진행 후, 최종적으로 필요없어진 부분을 제거한다]

 -------------- 다중 플레이어 모드 ----------------
 * [Step21. sut 에 다중 플레이어 모드 선택 후 게임 시작 메세지가 출력된다]
 * */