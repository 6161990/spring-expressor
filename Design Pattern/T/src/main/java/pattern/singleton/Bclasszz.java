package pattern.singleton;

import pattern.singleton.forObjectOriented.SocketClient;

public class Bclasszz {

    private SocketClient socketClient;

    public Bclasszz() {
        this.socketClient = SocketClient.getInstance();
    }

    public SocketClient getSocketClient() {
        return this.socketClient;
    }
}
