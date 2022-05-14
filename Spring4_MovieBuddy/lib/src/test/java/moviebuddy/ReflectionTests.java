package moviebuddy;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class ReflectionTests {

	@Test
	void objectCreateAndMethodCall() throws Exception {
		//Without reflection
		Duck duck = new Duck();
		duck.quack();
		
		//With reflection
		Class<?> duckClass = Class.forName("moviebuddy.ReflectionTests$Duck");
		Object duckObjects = duckClass.getDeclaredConstructor().newInstance();
		Method quackMethod = duckObjects.getClass().getDeclaredMethod("quack", null);
		quackMethod.invoke(duckObjects);
		
	}
	
	static class Duck {
		
		void quack() {
			System.out.println("꽥꽥!");
		}
	}
}
