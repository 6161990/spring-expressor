package pattern.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pattern.builder.pizza.ClassPizza;
import pattern.builder.pizza.Pizza;
import pattern.builder.pizza.YoonPizza;

import static pattern.builder.pizza.Pizza.Topping.*;
import static pattern.builder.pizza.YoonPizza.Size.큰거;

public class BuilderTest {

    @Test
    void 빌더패턴을_이용해_다양한_피자_인스턴스를_생성합니다() {
        Pizza yoonPizza = new YoonPizza.Builder(큰거).addTopping(머시룸).build();

        Assertions.assertEquals(yoonPizza.toString(), "toppings=[머시룸], size=큰거");
    }

    @Test
    void 빌더패턴을_이용해_다양한_피자_인스턴스를_생성합니다2() {
        Pizza classicPizza = new ClassPizza.Builder().addTopping(햄).addTopping(양파).addTopping(머시룸).sauceInside().build();

        Assertions.assertEquals(classicPizza.toString(), "topping= [햄, 머시룸, 양파], sauceInside= true");
    }


}
