package numberGuessing.console;

import numberGuessing.PositiveIntegerGeneratorStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppModel_specs {

    private static final String NEW_LINE = System.lineSeparator();

    @DisplayName("sut 가 처음 초기화되면 isCompleted 가 false 다.")
    @Test
    void sut_is_incompleted_when_it_is_initialized(){
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        boolean actual = sut.isCompleted();
        assertThat(actual).isFalse();
    }

    @DisplayName("sut 의 첫 flushOutput 은 게임모드선택 옵션 메세지다.")
    @Test
    void sut_correctly_prints_select_mode_message() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));

        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo("1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE +
                "3: Exit" + NEW_LINE + "Enter selection: ");
    }


    @DisplayName("sut 진행중 3을 입력값으로 넣으면 sut 는 종료된다")
    @Test
    void sut_correctly_exist() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));

        sut.processInput("3");

        // assertThat(sut.isCompleted()).isTrue();
        assertTrue(sut.isCompleted());
    }

    @DisplayName("sut 에 싱글게임모드선택 후 게임 시작 메세지가 출력된다")
    @Test
    void sut_correctly_prints_single_player_game_start_message() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("1");

        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo("Single player game Start!" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                + NEW_LINE + "Enter your guess: ");
    }

    void sut_correctly_prints_too_low_message_in_single_player_game(int answer, int guess) {
    }

    void sut_correctly_prints_too_high_message_in_single_player_game(int answer, int guess) {
    }


    void sut_correctly_prints_correct_message_in_single_player_game(int answer) {
    }

    void sut_correctly_prints_guess_count_if_single_player_game_finished(int fails){
    }

    void sut_correctly_prints_one_guess_if_single_player_game_finished() {
    }

    void sut_prints_select_mode_message_if_single_player_game_finished(){
    }

    void sut_returns_to_mode_selection_if_single_player_game_finished() {
    }

    void sut_generates_answer_for_each_game(String source) {
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
 * [Step6. 싱글 플레이어 게임에서 입력한 정답이 answer 보다 클 경우 해당 메세지가 출력된다(Test)]
 * [Step7. 싱글 플레이어 게임에서 입력한 정답이 answer 일 때, 해당 메세지가 출력된다(Test)]
 *          입력값이 게임 모드를 선택하는지, 정답을 입력하는지 알 수 있게 상태 변화를 감지할 수 있는 방법이 필요하다.
 * [Step8. Refactoring]
 * [Step9. 싱글 플레이어 게임에서 정답을 맞췄을 때, 총 실패횟수를 알려주는 메세지가 출력된다(Test)]
 * [Step10. 싱글 플레이어 게임에서 정답을 한번에 맞췄을 때, 'guesses' 가 아니라 'guess' 로 출력된다(Test)]
 * [Step10. Refactoring]
 * [Step11. 싱글 플레이어 모드가 끝나면 다시 select mode 가 보여진다(Test)]
 * [Step11. Refactoring]
 * [Step12. 싱글 플레이어 모드가 끝나고 돌아간 select mode 에서 exit 를 선택했을 때 sut 는 잘 종료된다(Test)]
 * [Step13. 싱글 플레이어 모드는 반복하여 게임을 실행해도 잘 돌아간다(Test)]
 * [Step14. Refactoring 1 - Processor 를 도입하여 sut 루프 를 교체해주는 방식으로 변경한다]
 * [Step15. Refactoring 2 - ModeSelection 에서 1이 입력되었을 때 SinglePlayer 의 processor 를 생성하도록 변경한다]
 * [Step16. Refactoring 3 - tries 필드를 없앤다]
 * [Step17. Refactoring 4 - answer 필드를 없앤다]
 * [Step18. Refactoring 5 - 해당 Refactoring 진행 후, 최종적으로 필요없어진 부분을 제거한다]
 * */