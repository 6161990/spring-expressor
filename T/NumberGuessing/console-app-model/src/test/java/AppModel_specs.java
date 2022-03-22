import numberguessing.console.AppModel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
}

/**
 * [Test1. sut가 초기화되고나면 완료된 상태가 아니다] PositiveIntegerGeneratorStub 생성
 * [Test2. sut의 첫 flushOutput 게임모드선택 옵션 메세지다]
 *
 * */