package numberguessing.console;

import numberguessing.PositiveIntegerGenerator;

public class PositiveIntegerGeneratorStub implements PositiveIntegerGenerator {

    private final int[] numbers;
    private int index;

    public PositiveIntegerGeneratorStub(int... numbers) {
        this.numbers = numbers;
        index = 0;
    }

    @Override
    public int generateLessThanOrEqualToHundred() {
        int number = numbers[index];
        index = (index + 1) % numbers.length; // 나머지 연산자를 이용해 number의 길이가 0 이상이 될 때마다 index를 setting 해주도록
        return number;
    }
}
