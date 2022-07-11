package pattern.factoryMethod;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FactoryMethodTest {

    @Test
    void factoryMethod패턴을_이용해_Car를_생성한다() {
        CarFactory factory = new HyundaiFactory();
        Car sonata = factory.createCar("sonata");
        Car santafe = factory.createCar("santafe");

        Assertions.assertEquals(sonata.toString(), "낙타가 나타났다. 소나타.");
        Assertions.assertEquals(santafe.toString(), "카페. 산타페.");
    }

    @Test
    void 차주인의_이름으로_차를_가져옵니다_없으면_생성합니다() {
        CarFactory factory = new HyundaiFactory();
        Car tomasCar = factory.returnMyCar("Tomas");
        Car tomasCarClone = factory.returnMyCar("Tomas");

        Assertions.assertEquals(tomasCar, tomasCarClone);
    }

    @Test
    void 첫_판매시_세차와_넘버링을_서비스합니다() {
        CarFactory factory = new HyundaiFactory();
        String output = factory.sellCar("sonata");

        Assertions.assertEquals(output, "numbering washCar");
    }
}
