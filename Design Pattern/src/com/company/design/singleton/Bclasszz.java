package com.company.design.singleton;

public class Bclasszz {

    private SocketClient socketClient;

    public Bclasszz(){
        this.socketClient = SocketClient.getInstance();
    }
    public SocketClient getSocketClient(){
        return this.socketClient;
    }
}
