package numberGuessing.console;

import numberGuessing.RandomGenerator;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        AppModel appModel = new AppModel(new RandomGenerator());
        Scanner scanner = new Scanner(System.in);
        runLoop(appModel, scanner);
        scanner.close();
    }

    private static void runLoop(AppModel appModel, Scanner scanner) {
        while(appModel.isCompleted() == false){
            System.out.println(appModel.flushOutput());
            appModel.processInput(scanner.nextLine());
        }
    }
}
