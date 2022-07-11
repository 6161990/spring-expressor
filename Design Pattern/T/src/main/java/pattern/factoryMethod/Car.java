package pattern.factoryMethod;

/**
 * 팩토리 메소드가 생성하는 객체의 인터페이스를 정의한다.
 * */
public abstract class Car {
    String carType;

    @Override
    public String toString() {
        return carType;
    }
}
