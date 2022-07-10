package pattern.templateMethod;

public class AdvancedLevel extends PlayerLevel{
    @Override
    public void run() {
        System.out.println("running faster");
    }

    @Override
    public void jump() {
        System.out.println("jumping!");
    }

    @Override
    public void turn() {
        System.out.println("turning!");
    }

    @Override
    public String showLevelMessage(int count) {
        return "***** AdvancedLevel Level *****" + count + "round jumping";
    }
}