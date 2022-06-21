package moviebuddy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class ReflectionTests {
    @Test
    void objectCreateAndMethod() throws Exception {
        //Without reflection
        Duck duck = new Duck();
        String quack = duck.quack();

        //Without reflection
        Class<?> duckClass = Class.forName("moviebuddy.ReflectionTests$Duck");
        Object duckObject = duckClass.getDeclaredConstructor().newInstance();
        Method quackMethod = duckObject.getClass().getDeclaredMethod("quack");
        Object invokeQuack = quackMethod.invoke(duckObject);

        Assertions.assertEquals(quack, invokeQuack);
    }

    static class Duck {
        private String quackquack;

        public String quack(){
            quackquack = "꽥꽥@@";
            return quackquack;
        }
    }
}
