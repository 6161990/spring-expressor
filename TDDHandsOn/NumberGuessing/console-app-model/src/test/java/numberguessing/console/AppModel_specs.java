package numberguessing.console;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
}
