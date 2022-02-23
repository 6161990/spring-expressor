public class Calculator {

    private ICalculator iCalculator;

    public Calculator(ICalculator iCalculator){
        this.iCalculator=iCalculator;
    }
    public int sum(int x, int y) {
        return iCalculator.sum(x, y);
    }

    public int minus(int x, int y) {
        return iCalculator.minus(x, y);
    }
}
