package pattern.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pattern.singleton.forObjectOriented.ConnectionPool;

import java.util.Calendar;

class SingletonTest {

   @Test
   void 싱글톤_패턴을_이용해_하나의_객체를_공유한다() {
        ConnectionPool instance1 = ConnectionPool.getInstance();
        ConnectionPool instance2 = ConnectionPool.getInstance();

       Assertions.assertEquals(instance1, instance2);
   }

    @Test
    void 자바의_Calendar_객체도_싱글턴객체이다() {
       // 날짜에 대한 정보, 서울 동경(타임존)에 맞는 정보를 가져온다. 여러번 설정할 필요가 없기 때문에 싱글톤으로 구현되어 있다.
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        Assertions.assertEquals(calendar1, calendar2);
    }
}
