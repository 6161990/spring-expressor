package pattern.singleton;

import pattern.singleton.forObjectOriented.SocketClient;

public class Aclasszz {

    private SocketClient socketClient;

    public Aclasszz() {
        this.socketClient = SocketClient.getInstance();
    }

    public SocketClient getSocketClient() {
        return this.socketClient;
    }
}
