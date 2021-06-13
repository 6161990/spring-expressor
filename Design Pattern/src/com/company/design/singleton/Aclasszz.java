package com.company.design.singleton;

public class Aclasszz {

    private SocketClient socketClient;

    public Aclasszz() {
        this.socketClient = SocketClient.getInstance();
    }

    public SocketClient getSocketClient() {
        return this.socketClient;
    }
}
