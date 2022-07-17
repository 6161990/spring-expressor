package pattern.decorator.forObjectOriented.decorator;

import pattern.decorator.forObjectOriented.coffee.Coffee;
/**
 * ConcreteDecorator : 새롭게 추가되는 서비스를 실제 구현한 클래스로 addBehavior()를 구현한다.
 * */
public class Latte extends Decorator{
    public Latte(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String brewing() {
        return super.brewing() + " Adding Milk ";
    }
}
