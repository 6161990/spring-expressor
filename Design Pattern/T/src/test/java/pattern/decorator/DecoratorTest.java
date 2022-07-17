package pattern.decorator;

import org.junit.jupiter.api.Test;
import pattern.decorator.forObjectOriented.coffee.Coffee;
import pattern.decorator.forObjectOriented.coffee.EtiopiaCoffee;
import pattern.decorator.forObjectOriented.coffee.KenyaCoffee;
import pattern.decorator.forObjectOriented.decorator.Latte;
import pattern.decorator.forObjectOriented.decorator.Mocha;

import static org.assertj.core.api.Assertions.assertThat;

public class DecoratorTest {

    @Test
    void 케냐커피로_라떼를_만듭니다() {
        Coffee kenyaCoffee = new KenyaCoffee();
        kenyaCoffee.brewing();

        Coffee kenyaLatte = new Latte(kenyaCoffee);

        assertThat(kenyaLatte.brewing()).isEqualTo("KenyaCoffee is brewing.. Adding Milk ");
    }

    @Test
    void 케냐커피로_모카라떼를_만듭니다() {
        Coffee kenyaMocha = new Mocha(new Latte(new KenyaCoffee()));

        assertThat(kenyaMocha.brewing()).isEqualTo("KenyaCoffee is brewing.. Adding Milk And Adding Mocha Syrup ");
    }

    @Test
    void 에티오피아커피로_모카라떼를_만듭니다() {
        Coffee etiopiaMocha = new Mocha(new Latte(new EtiopiaCoffee()));
        // ------------------ decorator - decorator - realObject--------
        assertThat(etiopiaMocha.brewing()).isEqualTo("EtiopiaCoffee is brewing.. Adding Milk And Adding Mocha Syrup ");
    }
}
