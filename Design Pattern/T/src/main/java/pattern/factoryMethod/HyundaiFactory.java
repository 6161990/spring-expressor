package pattern.factoryMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Concreate의 인스턴스를 반환하기 위해 팩토리 메소드를 재정의한다.
 * */
public class HyundaiFactory extends CarFactory {

    Map<String, Car> carMap = new HashMap<>();

    @Override
    public Car createCar(String name) {
        Car car = null;
        if(name.equals("sonata")){
            car = new Sonata();
        }else if(name.equals("santafe")){
            car = new Santafe();
        }
        return car;
    }

    @Override
    public Car returnMyCar(String name) {
        Car car = carMap.get(name);
        if(car == null){
            if(name.equals("Tomas")){
                car = new Sonata();
            }else if(name.equals("Jein")){
                car = new Santafe();
            }
            carMap.put(name, car);
        }
        return car;
    }
}
