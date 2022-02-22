public class Main {
    public static void main(String args[]){
        System.out.println("hello JUnit");

        Calculator calculator = new Calculator(new KrwCalculator());

//        System.out.println(calculator.sum(10,10));

        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator1 = new Calculator(dollarCalculator);
        System.out.println(calculator1.sum(10,10));
    }
}
