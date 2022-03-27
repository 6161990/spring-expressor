import numberguessing.console.AppModel;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppModel_specs {

    private static final String NEW_LINE = System.lineSeparator();

    @Test
    void sut_is_incompleted_when_it_is_initalized(){
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        boolean actual = sut.isCompleted();
        assertFalse(actual);
    }

    @Test
    void sut_correctly_prints_select_mode_message() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo("1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE +
                "3: Exit" + NEW_LINE + "Enter selection: ");

    }

    @Test
    void sut_correctly_exist() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("3");

        boolean actual = sut.isCompleted();
        assertTrue(actual);

    }

    @Test
    void sut_correctly_prints_single_player_game_start_message() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.flushOutput();
        sut.processInput("1");

        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo("Single player game Start!" + NEW_LINE + "I'm thinking of a number between 1 and 100." + NEW_LINE + "Enter your guess: ");
    }

    @ParameterizedTest
    @CsvSource({"50, 34","60, 20", "89, 24"})
    void sut_correctly_prints_too_low_message_in_single_player_game(int answer, int guess) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");

        sut.flushOutput();
        sut.processInput(Integer.toString(guess));

        String actual = sut.flushOutput();
        assertThat(actual).isEqualTo("Your guess is too low." + NEW_LINE + "Enter your guess: ");
    }

    @ParameterizedTest
    @CsvSource({"20,45" , "56, 99", "12, 35"})
    void sut_correctly_prints_too_high_message_in_single_player_game(int answer, int guess) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");

        sut.flushOutput();
        sut.processInput(Integer.toString(guess));

        String actual = sut.flushOutput();
        assertThat(actual).isEqualTo("Your guess is too high." + NEW_LINE + "Enter your guess: ");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 10, 100})
    void sut_correctly_prints_correct_message_in_single_player_game(int answer) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");

        sut.flushOutput();
        int guess = answer;
        sut.processInput(Integer.toString(guess));

        String actual = sut.flushOutput();
        assertThat(actual).isEqualTo("Correct! ");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    void sut_correctly_prints_guess_count_if_single_player_game_finished(int fails){
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("1");
        for (int i = 0; i < fails; i++) {
            sut.processInput("30");
        }
        sut.flushOutput();
        sut.processInput("50");

        String actual = sut.flushOutput();
        assertThat(actual).contains((fails + 1) + " guesses." + NEW_LINE);
    }

    @Test
    void sut_correctly_prints_one_guess_if_single_player_game_finished() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("1");
        sut.flushOutput();
        sut.processInput("50");

        String actual = sut.flushOutput();
        assertThat(actual).contains("1 guess.");
    }
}

/**
 * [Test1. sut가 초기화되고나면 완료된 상태가 아니다] PositiveIntegerGeneratorStub 생성
 * [Test2. sut의 첫 flushOutput 게임모드선택 옵션 메세지다]
 * [Test3. sut에 게임모드선택메세지출력 후 3을 입력값으로 넣으면 sut는 종료된다]
 * [Test4. sut에 싱글게임모드선택 후 게임 시작 메세지가 출력된다]
 * [Test5. 싱글 플레이어 게임에서 입력한 정답이 answer보다 작을 경우 해당 메세지가 출력된다]
 * [Test6. 싱글 플레이어 게임에서 입력한 정답이 answer보다 클 경우 해당 메세지가 출력된다]
 * [Test7. 싱글 플레이어 게임에서 입력한 정답이 answer일 때, 해당 메세지가 출력된다]
 *          입력값이 게임 모드를 선택하는지, 정답을 입력하는지 알 수 있게 상태 변화를 감지할 수 있는 방법이 필요하다.
 * [Test8. Refactoring]
 * [Test9. 싱글 플레이어 게임에서 정답을 맞췄을 때, 총 실패횟수를 알려주는 메세지가 출력된다]
 * [Test10. 싱글 플레이어 게임에서 정답을 한번에 맞췄을 때, 'guesses'가 아니라 'guess' 로 출력된다]
 * */