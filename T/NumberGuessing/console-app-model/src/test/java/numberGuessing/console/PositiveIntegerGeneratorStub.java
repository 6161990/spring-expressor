package numberGuessing.console;

import numberGuessing.PositiveIntegerGenerator;

public class PositiveIntegerGeneratorStub implements PositiveIntegerGenerator {

    private int[] numbers;
    private int index;

    public PositiveIntegerGeneratorStub(int... numbers) {
        this.numbers = numbers;
        index = 0;
    }

    @Override
    public int generateLessThanEqualsToHundred() {
        int number = numbers[index];
        index = (index + 1) % numbers.length;
        return number;
    }
}
