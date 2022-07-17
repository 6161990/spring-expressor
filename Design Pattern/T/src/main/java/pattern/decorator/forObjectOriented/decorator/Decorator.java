package pattern.decorator.forObjectOriented.decorator;

import pattern.decorator.forObjectOriented.coffee.Coffee;

/**
 * Decorator : Component의 참조자를 관리하면서 Component에 정의된 인터페이스를 만족하도록 정의
 * */
public abstract class Decorator extends Coffee {

    Coffee coffee;

    public Decorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String brewing() {
        return coffee.brewing();
    }
}
