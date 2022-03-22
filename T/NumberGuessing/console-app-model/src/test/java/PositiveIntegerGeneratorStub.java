import numberguessing.PositiveIntegerGenerator;

public class PositiveIntegerGeneratorStub implements PositiveIntegerGenerator {

    private final int[] numbers;
    private int index;

    public PositiveIntegerGeneratorStub(int... numbers) {
        this.numbers = numbers;
        this.index = 0;
    }

    @Override
    public int generateLessThanOrEqualToHundread() {
        int number = numbers[index];
        index = (number + 1) % numbers.length;
        return number;
    }
}
