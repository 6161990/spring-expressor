package numberguessing.console;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppModel_specs {

    private static final String NEW_LINE = System.lineSeparator();

    @Test //해당 메소드가 test라는 것을 알려주는 어노테이션
    void sut_is_incompleted_when_it_is_initialized() { // sut = System Under Test : 테스트 대상이 되는 시스템 여기서는 AppModel instance
        var sut  = new AppModel(new PositiveIntegerGeneratorStub(50)); //테스트에서 사용되는 인터페이스의 구현체: 테스트를 위해 입력을 미리 준비해놓는 역할을 함
        boolean actual = sut.isCompleted();
        assertFalse(actual); // 테스트 결과를 검증해주는 메소드
    }

    @Test
    void sut_correctly_prints_select_mode_message() { // 게임 앱이 시작될 때 사용자에게 선택지를 주는 message가 잘 출력되는지 확인하는 test
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50)); //실제 게임을 진행하고 있지 않기 때문에 아무 숫자나 넣음
        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo("1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE +
                "3: Exit" + NEW_LINE + "Enter selection: "); //assertThat : 테스트 검증 로직을 간단하게 작성하도록 도와주는 도구 (assertj 제공)
    }

    @Test
    void sut_correctly_exits() { // sut가 올바로 종료된다.
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));

        sut.processInput("3");

        boolean actual = sut.isCompleted();
        assertTrue(actual);
    }

    @Test
    void sut_correctly_prints_single_player_game_start_message() { // 단일 플레이어 모드가 시작되었음을 알리는 메세지가 잘 출력되는지 확인
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.flushOutput();
        sut.processInput("1");

        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo("Single player game" + NEW_LINE + "I'm thinking of a number between 1 and 100."
                + NEW_LINE + "Enter your guess: ");
    }

    @ParameterizedTest // JUnit 5
    @CsvSource({"50, 40", "30, 29", "89, 9"})  //ParameterizedTest의 파라미터를 제공해주는 여러 방법 중 하나
    // CsvSource 는 , 를 사용해 값을 분리해 테스트 메소드에 각각 순서대로 넣어줌
    void sut_correctly_prints_too_low_message_in_single_player_game(int answer, int guess) { // answer는 게임 프로그램이 설정한 값 , guess는 사용자가 압력한 값
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.flushOutput();
        sut.processInput(Integer.toString(guess)); // answer = 50 , guess = 40

        String actual = sut.flushOutput();

        assertThat(actual).isEqualTo("Your guess is too low." + NEW_LINE + "Enter your guess: ");
    }

    @ParameterizedTest
    @CsvSource({"50,60", "80,81"})
    void sut_correctly_prints_too_high_message_in_single_player_game(int answer, int guess) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.flushOutput();
        sut.processInput(Integer.toString(guess));

        String actual = sut.flushOutput();
        assertThat(actual).isEqualTo("Your guess is too high." + NEW_LINE + "Enter your guess: ");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 10, 100}) // 파라미터 값이 하나일 때 @ValueSource가 더 유용함.
    void sut_correctly_prints_correct_message_in_single_player_game(int answer) {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.flushOutput();
        int guess = answer;
        sut.processInput(Integer.toString(guess));

        String actual = sut.flushOutput();

        assertThat(actual).startsWith("Correct! ");
    }

    //싱글 플레이어 게임이 종료되었을 때 주요 요구사항 1 - 사용자가 몇 번 추측을 했는지 출력하는 기능
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    void sut_correctly_prints_guess_count_if_single_player_game_finished(int fails) { // 실패횟수
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50)); // 정답 50
        sut.processInput("1"); //싱글 플레이어 모드
        for(int i=0; i < fails; i++){
            sut.processInput("30"); // 실패횟수 만큼 오답
        }

        sut.flushOutput();
        sut.processInput("50"); // 정답

        String actual = sut.flushOutput();

        assertThat(actual).contains((fails + 1) + " guesses." + NEW_LINE); // 실패횟수+ 성공횟수

    }

    // 싱글 플레이어 게임이 종료 되었을 때, 사용자가 한 번에 맞췄다면 output 문장에 guesses(복수) 형태가 아니라 guess(단수) 형태여야한다.
    @Test
    void sut_correctly_prints_one_guess_if_single_player_game_finished() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50)); // 정답 50
        sut.processInput("1"); //싱글 플레이어 모드
        sut.flushOutput();
        sut.processInput("50");

        String actual = sut.flushOutput();

        assertThat(actual).contains("1 guess.");
    }

    // 한번의 싱글 플레이어 게임이 끝났을 때, MODE 선택지가 잘 출력되는지 확인하는 테스트
    @Test
    void sut_prints_select_mode_message_if_single_player_game_finished() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        sut.processInput("1");
        sut.flushOutput();
        sut.processInput("50");

        String actual = sut.flushOutput();

        assertThat(actual).endsWith("1: Single player game" + NEW_LINE + "2: Multiplayer game" + NEW_LINE + "3: Exit"
                + NEW_LINE + "Enter selection: ");
    }

    // MODE 선택지가 나오고 플레이어의 선택에 따라 MODE가 잘 돌아가는지 확인하는 테스트
    @Test
    void sut_returns_to_mode_selection_if_single_player_game_finished() {
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));

        sut.processInput("1");
        sut.processInput("50");
        sut.processInput("3");

        boolean actual = sut.isCompleted();
        assertTrue(actual);
    }

    // 반복 플레이어를 해도 답이 잘 출력이 되는지 확인하는 테스트
    @ParameterizedTest
    @ValueSource(strings = "1, 10, 100")
    void sut_generates_answer_for_each_game(String source) {
        int[] answers = Stream.of(source.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        var sut = new AppModel(new PositiveIntegerGeneratorStub(answers));
        for(int answer : answers) {
            sut.processInput("1"); // 모드 게임을 선택하는 "1"
            sut.flushOutput(); //  "Single player game I'm thinking of ....."
            sut.processInput(Integer.toString(answer)); // 답 입력
        }

        String actual = sut.flushOutput();
        assertThat(actual).startsWith("Correct! ");
    }
    /**
     * 해당 테스트에서 겪었던 문제 두 가지
     * 1. Red Green Refactoring 순환 Test 가 안됨. 테스트 코드 작성 후 테스트가 바로 성공해버림 (1,10,100) 순으로 인자 값을 주었기 때문. (100,10,1)순으로 주면 정상적으로 테스트 실패 (Red 단계이므로 실패가 정상)
     *  -> 이유 :  input 10일 때, 두 번째 아웃풋 발생 (output = "Your guess is too high." + NEW_LINE + "Enter your guess: ";)
     *            싱글 플레이어 모드가 true인 채로, 다시 답을 입력하는 데 싱글 플레이어를 선택하는 "1"이  processSinglePlayerGame(input)으로 들어감. => 테스트 성공
     *            그 다음 테스트에서는 answer[3](=100)이 processModeSelection() input 값으로 들어가서 else 문을 타버려서 게임 종료.
     *            바로 직전 output 값인 Correct! 로 assert 검증들어가기 때문에 테스트 결국 성공.
     *
     * 2. 1에서 겪은 궁극적 원인은 answer 에 있었음. 반복하여 테스트 할 때 생성되는 answer값이 계속해서 새로 생성되지 않는 로직이었음. 그래서 답은 계속 "1"
     *    로직에서 answer가 생성되는 시점을 게임 모드 선택 이후로 변경해야함. => 그러면 1,10,100 으로 테스트를 해도 성공함.
     * */
}
