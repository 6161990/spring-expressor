package numberGuessing;

import java.util.Random;

public class RandomGenerator implements PositiveIntegerGenerator{

    private final Random random = new Random();

    @Override
    public int generateLessThanOrEqualToHundred() {
        return random.nextInt(100) + 1;
    }
}
