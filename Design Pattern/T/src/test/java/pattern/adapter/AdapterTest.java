package pattern.adapter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pattern.adapter.forObjectOriented.Print;
import pattern.adapter.forObjectOriented.PrintBanner;

/** 클라이언트 : Target 인터페이스를 사용하는 객체*/
class AdapterTest {

    @Test
    void 어뎁터_패턴을_이용하여_프린트를_구현() {
        Print sut = new PrintBanner("할 수 있다");
        String week = sut.printWeek();
        String strong = sut.printStrong();

        Assertions.assertEquals(week, "(할 수 있다)");
        Assertions.assertEquals(strong, "**할 수 있다**");
    }
}
