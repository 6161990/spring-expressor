import numberguessing.console.AppModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AppModel_specs {

    @Test
    void sut_is_incompleted_when_it_is_initalized(){
        var sut = new AppModel(new PositiveIntegerGeneratorStub(50));
        boolean actual = sut.isCompleted();
        assertFalse(actual);
    }
}

/**
 * [Test1. sut가 초기화되고나면 완료된 상태가 아니다] PositiveIntegerGeneratorStub 생성
 * */