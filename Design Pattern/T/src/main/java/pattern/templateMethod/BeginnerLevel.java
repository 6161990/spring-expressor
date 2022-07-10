package pattern.templateMethod;

public class BeginnerLevel extends PlayerLevel{
    @Override
    public void run() {
        System.out.println("running slowly");
    }

    @Override
    public void jump() {
        System.out.println("can't jump");
    }

    @Override
    public void turn() {
        System.out.println("can't turn");
    }

    @Override
    public String showLevelMessage(int count) {
        return "***** Beginner Level *****" + count + "round jumping";
    }
}
