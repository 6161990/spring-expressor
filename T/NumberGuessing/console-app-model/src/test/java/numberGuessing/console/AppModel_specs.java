package numberGuessing.console;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.powermock.reflect.Whitebox;

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
        sut.flushOutput();
        sut.processInput("1");

        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo(SINGLE_GAME_START_MESSAGE);
    }

    @DisplayName("싱글 플레이어 게임에서 입력한 정답이 answer 보다 작을 경우 해당 메세지가 출력된다")
    @ParameterizedTest
    @CsvSource({"50, 40", "30, 29", "89, 9"})
    void sut_correctly_prints_too_low_message_in_single_player_game(int answer, int guess) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.flushOutput();
        sut.processInput(String.valueOf(guess));

        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo("Your guess is too low." + NEW_LINE + "Enter your guess: ");
    }

    @DisplayName("싱글 플레이어 게임에서 입력한 정답이 answer 보다 클 경우 해당 메세지가 출력된다")
    @ParameterizedTest
    @CsvSource({"44, 50", "77, 88","9, 99"})
    void sut_correctly_prints_too_high_message_in_single_player_game(int answer, int guess) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.flushOutput();
        sut.processInput(String.valueOf(guess));

        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo("Your guess is too high." + NEW_LINE + "Enter your guess: ");
    }

    @DisplayName("싱글 플레이어 게임에서 입력한 정답이 answer 일 때, 해당 메세지가 출력된다")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 77, 99})
    void sut_correctly_prints_correct_message_in_single_player_game(int answer) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.flushOutput();
        sut.processInput(String.valueOf(answer));

        String actual = sut.flushOutput();

        assertThat(actual).startsWith("Correct! ");
    }

    @DisplayName("싱글 플레이어 게임에서 정답을 맞췄을 때, 총 실패횟수를 알려주는 메세지가 출력된다")
    @ParameterizedTest
    @ValueSource(ints = {19, 30, 77, 99})
    void sut_correctly_prints_guess_count_if_single_player_game_finished(int fails) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("1");

        for (int i = 0; i < fails - 1; i++) {
            sut.processInput("0");
            sut.flushOutput();
        }

        sut.processInput("50");
        String actual = sut.flushOutput();

        assertThat(actual).contains(fails + " guesses." + NEW_LINE);
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
        sut.flushOutput();
        sut.processInput("1");

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

    // -- START MULTI PLAY ROUND2 --

    @DisplayName("sut 에 다중 플레이어 모드 선택 후 게임 시작 메세지가 출력된다")
    @Test
    void sut_correctly_prints_multiplayer_game_setup_message(){
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.flushOutput();
        sut.processInput("2");

        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo("Multiplayer game" + NEW_LINE + "Enter player names separated with commas: ");
    }

    @DisplayName("다중 플레이어 모드 선택 시, 사용자를 입력하면 추측값 범위 메세지가 출력된다.")
    @Test
    void sut_correctly_prints_multiplayer_game_start_message() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub (50));
        sut.processInput("2");
        sut.flushOutput();

        sut.processInput("Jenny, Rose, Lisa");
        String actual = sut.flushOutput();

        assertThat(actual).contains("I'm thinking of a number between 1 and 100." + NEW_LINE);
    }

    @DisplayName("다중 플레이어 모드에서 첫번째 플레이어 순서에서 해당 플레이어 이름이 담긴 메세지가 출력된다.")
    @ParameterizedTest
    @CsvSource({"Jenny, Rose, Lisa", "Rose, Lisa, Jenny", "Lisa, Jenny, Rose"})
    void sut_correctly_prompts_first_player_name(String player1, String player2, String player3){
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("2");
        sut.flushOutput();

        sut.processInput(String.join(", ", player1, player2, player3));
        String actual = sut.flushOutput();

        assertThat(actual).endsWith("Enter " + player1 + "'s guess:");
    }

    @DisplayName("다중 플레이어 모드에서 두번째 플레이어 순서에서 해당 플레이어 이름이 담긴 메세지가 출력된다.")
    @ParameterizedTest
    @CsvSource({"Jenny, Rose, Lisa", "Rose, Lisa, Jenny", "Lisa, Jenny, Rose"})
    void sut_correctly_prompts_second_player_name(String player1, String player2, String player3){
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("2");
        sut.flushOutput();

        sut.processInput(String.join(", ", player1, player2, player3));
        sut.processInput("0");
        String actual = sut.flushOutput();

        assertThat(actual).endsWith("Enter " + player2 + "'s guess:");
    }

    @DisplayName("다중 플레이어 모드에서 세번째 플레이어 순서에서 해당 플레이어 이름이 담긴 메세지가 출력된다")
    @ParameterizedTest
    @CsvSource({"Jenny, Rose, Lisa", "Rose, Lisa, Jenny", "Lisa, Jenny, Rose"})
    void sut_correctly_prompts_third_player_name(String player1, String player2, String player3){
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("2");
        sut.flushOutput();

        sut.processInput(String.join(", ", player1, player2, player3));
        sut.processInput("0");
        sut.processInput("0");
        String actual = sut.flushOutput();

        assertThat(actual).endsWith("Enter " + player3 + "'s guess:");
    }

    @DisplayName("다중 플레이어 모드에서 모든 순서가 다 돌면 다시 첫번째 플레이어에게 넘어간다")
    @ParameterizedTest
    @CsvSource({"Jenny, Rose, Lisa", "Rose, Lisa, Jenny", "Lisa, Jenny, Rose"})
    void sut_correctly_rounds_players(String player1, String player2, String player3){
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("2");

        sut.processInput(String.join(", ", player1, player2, player3));
        sut.processInput("0");
        sut.processInput("0");
        sut.flushOutput();
        sut.processInput("0");

        String actual = sut.flushOutput();

        assertThat(actual).endsWith("Enter " + player1 + "'s guess:");

    }

    @DisplayName("다중 플레이어 게임에서 입력한 정답이 answer 보다 작을 경우 해당 메세지가 출력된다")
    @ParameterizedTest
    @CsvSource({"50, 49, 1, Jenny", "77, 7, 2, Rose"})
    void sut_correctly_prints_too_low_message_multiplayer_game(int answer, int guess, int fails, String lastPlayer) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("2");
        sut.processInput("Jenny, Rose, Me");

        for (int i = 0; i < fails-1; i++) {
            sut.processInput(String.valueOf(guess));
        }

        sut.flushOutput();
        sut.processInput(String.valueOf(guess));

        String actual = sut.flushOutput();

        assertThat(actual).startsWith(lastPlayer + " guess is too low." + NEW_LINE);
    }

    @DisplayName("다중 플레이어 게임에서 입력한 정답이 answer 보다 클 경우 해당 메세지가 출력된다")
    @ParameterizedTest
    @CsvSource({"50, 59, 1, Jenny", "77, 79, 2, Rose"})
    void sut_correctly_prints_too_high_message_multiplayer_game(int answer, int guess, int fails, String lastPlayer) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("2");
        sut.processInput("Jenny, Rose, Me");

        for (int i = 0; i < fails-1; i++) {
            sut.processInput(String.valueOf(guess));
        }

        sut.flushOutput();
        sut.processInput(String.valueOf(guess));

        String actual = sut.flushOutput();

        assertThat(actual).startsWith(lastPlayer + " guess is too high." + NEW_LINE);
    }

    @DisplayName("다중 플레이어 게임에서 입력한 정답을 맞힌 경우 해당 메세지가 출력된다")
    @ParameterizedTest
    @ValueSource(ints = {1,4,50,77})
    void sut_correctly_prints_message_in_multiplayer_game(int answer){
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("2");
        sut.processInput("Jenny, Rose, Me");

        sut.flushOutput();
        sut.processInput(String.valueOf(answer));

        String actual = sut.flushOutput();

        assertThat(actual).startsWith("Correct! ");
    }

    @DisplayName("멀티 플레이어 게임이 종료되었을 때 승자가 메세지에 출력된다")
    @ParameterizedTest
    @CsvSource({"0, Jenny", "1, Rose"})
    void sut_correctly_prints_winner_if_multiplayer_game_finished(int fails, String winner) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("2");
        sut.processInput("Jenny, Rose, Me");

        for (int i = 0; i < fails; i++) {
            sut.processInput("0");
        }

        sut.flushOutput();
        sut.processInput("50");

        String actual = sut.flushOutput();
        assertThat(actual).contains(winner + " wins!!!!!!!!!!" + NEW_LINE);
    }

    @DisplayName("멀티 플레이어 모드가 끝나면 셀렉트 모드 메세지가 출력된다")
    @Test
    void sut_prints_select_mode_message_if_multiplayer_game_finished() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("2");
        sut.processInput("Jenny, Rose, Me");
        sut.flushOutput();

        sut.processInput("50");

        String actual = sut.flushOutput();

        assertThat(actual).endsWith(GAME_MODE_SELECT_MESSAGE);
    }

    @DisplayName("멀티 플레이어 모드가 끝나고 셀렉트 모드에서 3을 입력하면 게임이 종료된다")
    @Test
    void sut_returns_to_mode_selection_if_multiplayer_game_finished() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("2");
        sut.processInput("Jenny, Rose, Me");
        sut.flushOutput();

        sut.processInput("50");
        sut.flushOutput();

        sut.processInput("3");

        boolean actual = sut.isCompleted();

        assertThat(actual).isTrue();
    }

    @DisplayName("private 메소드(print()) 테스트")
    @Test
    @Disabled
    void print_correctly_appends_string_to_output_buffer() throws Exception {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        var outputBuffer = (StringBuffer) Whitebox.getField(AppModel.class, "outputBuffer").get(sut);
        outputBuffer.setLength(0);
        Whitebox.invokeMethod(sut, "print", "foo");
        String actual = outputBuffer.toString();
        assertThat(actual).isEqualTo("foo");
    }

    @DisplayName("private 메소드(println()) 테스트")
    @Test
    @Disabled
    void println_correctly_appends_string_and_line_separator_to_output_buffer() throws Exception {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        var outputBuffer = (StringBuffer) Whitebox.getField(AppModel.class, "outputBuffer").get(sut); // private 필드 가져오기
        outputBuffer.setLength(0);
        Whitebox.invokeMethod(sut, "println", "foo"); //private 메소드 실행시키
        String actual = outputBuffer.toString();
        assertThat(actual).isEqualTo("foo"+ NEW_LINE);
    }

    @DisplayName("private 메소드(printLines()) 테스트")
    @Disabled
    @Test
    void printLines_correctly_appends_lines() throws Exception {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        var outputBuffer = (StringBuffer) Whitebox.getField(AppModel.class, "outputBuffer").get(sut);

        outputBuffer.setLength(0);
        Whitebox.invokeMethod(sut, "printLines", "Foo", "Bar", "Baz");

        String actual = outputBuffer.toString();
        assertThat(actual).isEqualTo("Foo" + NEW_LINE + "Bar" + NEW_LINE + "Baz");
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

 * ------------------- START MULTI PLAY ROUND2 ---------------------

 * [Step21. sut 에 다중 플레이어 모드 선택 후 게임 시작 메세지가 출력된다]
 *          "Multiplayer game" + NEW_LINE + "Enter player names separated with commas: "
 * [Step22. 다중 플레이어 모드 선택 시, 추측값 범위 메세지가 출력된다]
 *          "I'm thinking of a number between 1 and 100." + NEW_LINE
 * [Step23. 다중 플레이어 모드에서 첫번째 플레이어 순서에서 해당 플레이어 이름이 담긴 메세지가 출력된다]
 *          "Enter " + player1 + "'s guess:"
 * [Step24. 다중 플레이어 모드에서 두번째 플레이어 순서에서 해당 플레이어 이름이 담긴 메세지가 출력된다]
 *          "Enter " + player2 + "'s guess:"
 * [Step25. 다중 플레이어 모드에서 세번째 플레이어 순서에서 해당 플레이어 이름이 담긴 메세지가 출력된다]
 *           "Enter " + player3 + "'s guess:"
 * [Step26. Refactoring - 테스트 코드에서 적정시기에 FlushOutput 되도록한다]
 * [Step27. Refactoring - MultiPlayerProcessor 를 개선한다]
 * [Step28. 다중 플레이어 모드에서 모든 순서가 다 돌면 다시 첫번째 플레이어에게 넘어간다]
 * [Step29. 다중 플레이어 게임에서 입력한 정답이 answer 보다 작을 경우 해당 메세지가 출력된다]
 *  *     Message = player + " guess is too low." + NEW_LINE
 * [Step30. 다중 플레이어 게임에서 입력한 정답이 answer 보다 클 경우 해당 메세지가 출력된다]
 *  *     Message = player + " guess is too high." + NEW_LINE
 * [Step31. 다중 플레이어 게임에서 입력한 정답을 맞힌 경우 해당 메세지가 출력된다]
 *  *     Message = "Correct! "
 * [Step32. 멀티 플레이어 게임이 종료되었을 때 승자가 메세지에 출력된다]
 *  *     Message = winner + " wins!!!!!!!!!!" + NEW_LINE
 * [Step33. Refactoring]
 * [Step34. 멀티 플레이어 모드가 끝나면 셀렉트 모드 메세지가 출력된다]
 * [Step35. 멀티 플레이어 모드가 끝나고 셀렉트 모드에서 3을 입력하면 게임이 종료된다]
 * [Step36. Refactoring - Print 와 Println 메소드 추출]
 * [Step37. Refactoring - 과한 상수화 변경]
 * [Step38. ATDD(인수테스트) 변경 필요부분 수정]
 * [Step39. Should I test private(Print, Println)]
 * [Step40. Refactoring - printLines() & getProcessMultiModeGame() ]
 * [Step41. Should I test private(PrintLines)]
 * [Step42. Refactoring - TextOutput 클래스 생성을 통한 책임과 역할 분리 그리고 Private Test의 문제점]
 * */