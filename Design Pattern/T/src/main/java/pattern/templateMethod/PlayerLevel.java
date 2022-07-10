package pattern.templateMethod;

public abstract class PlayerLevel {
    public abstract void run();
    public abstract void jump();
    public abstract void turn();
    public abstract String showLevelMessage(int a);

    public void fly(){ } // 훅 메소드 (반드시 구현할 필요는 없을 때, 선택사항일 때)

    final public void go(int count){
        run();

        for(int i=0; i<count; i++){
            jump();
        }

        turn();
        fly();
        showLevelMessage(count);
    }

}
