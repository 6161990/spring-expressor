package pattern.adepter;

public class SocketAdepter implements Electronic110v {

    private Electronic220v electronic220v;

    public SocketAdepter(Electronic220v electronic220v) {

        this.electronic220v = electronic220v;
    }

    @Override
    public void powerOn() {
        electronic220v.connect();
    }
}
