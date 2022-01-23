package numberguessing.console;

import numberguessing.PositiveIntegerGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AppModel_specs {

    @Test //해당 메소드가 test라는 것을 알려주는 어노테이션
    void sut_is_incompleted_when_it_is_initialized() { // sut = System Under Test : 테스트 대상이 되는 시스템 여기서는 AppModel instance
        var sut  = new AppModel(new PositiveIntegerGeneratorStub(50)); //테스트에서 사용되는 인터페이스의 구현체: 테스트를 위해 입력을 미리 준비해놓는 역할을 함
        boolean actual = sut.isCompleted();
        assertFalse(actual); // 테스트 결과를 검증해주는 메소드
    }
}
