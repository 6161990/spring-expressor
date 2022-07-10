package pattern.templateMethod;

public class SuperLevel extends PlayerLevel{
    private boolean flying;

    @Override
    public void run() {
        System.out.println("running super fast!");
    }

    @Override
    public void jump() {
        System.out.println("jumping higher!");
    }

    @Override
    public void turn() {
        System.out.println("turning faster!");
    }

    @Override
    public void fly() {
        System.out.println("fly fly fly~");
        flying = true;
    }

    @Override
    public String showLevelMessage(int count) {
        String message = "***** SuperLevel Level *****" + count + "round jumping";
        if(flying){
            message += " and flying!!!!!!";
        }
        return message;
    }
}
