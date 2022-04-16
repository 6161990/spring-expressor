package numberGuessing.console;

import numberGuessing.RandomGenerator;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        AppModel model = new AppModel(new RandomGenerator());
        Scanner scanner  = new Scanner(System.in);
        runLoop(model, scanner);
        scanner.close();;
    }

    private static void runLoop(AppModel model, Scanner scanner) {
        while (model.isCompleted() == false){
            System.out.println(model.flushOutput());
            model.processInput(scanner.nextLine());
        }
    }
}
