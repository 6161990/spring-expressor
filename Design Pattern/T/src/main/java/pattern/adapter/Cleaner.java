package pattern.adapter;

public class Cleaner implements Electronic220v {

    @Override
    public void connect() {
        System.out.println("청소기 220v on");
    }
}
