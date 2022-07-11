package pattern.factoryMethod;

/**
 * Car 타입의 객체를 반환하는 팩토리 메소드를 선언한다.
 * 팩토리 메소드를 기본적으로 구현하는데,
 * 이 구현에서는 Car(concreate)를 반환한다.
 *
 * Car의 생성을 위해 팩토리 메소드를 호출한다.
 * */
public abstract class CarFactory {

    public abstract Car createCar(String name);
    public abstract Car returnMyCar(String name);

    private String numbering(){
        return "numbering ";
    }

    private String washCar(){
        return "washCar";
    }

    final public String sellCar(String name){
        String numbering = numbering();
        createCar(name);
        String washCar = washCar();
        return numbering + washCar;
    }
}
