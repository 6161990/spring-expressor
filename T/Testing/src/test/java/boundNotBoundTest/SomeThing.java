package boundNotBoundTest;

public class SomeThing {

    Command b;

    public SomeThing(Command b) {
        this.b = b;
    }

    public SomeThing() {

    }

    public SomeThing(boolean b) {
    }

    public boolean someMethod(boolean b) {
        if (!b) {
            return true;
        }
        return false;
    }

    public boolean someMethod(Command b) {

        return false;
    }
}
