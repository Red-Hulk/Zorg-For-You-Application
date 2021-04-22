package application.BackEndClasses;

import application.abstractClasses.ChatNetworkConnection;

import java.io.Serializable;
import java.util.function.Consumer;

public class ChatNetworkHost extends ChatNetworkConnection {

    private int port;

    public ChatNetworkHost(int port, Consumer<Serializable> onReceivedCallback){
        super(onReceivedCallback);
        this.port = port;
    }

    @Override
    protected boolean isServer() {
        return true;
    }

    @Override
    protected String getIP() {
        return null;
    }

    @Override
    protected int getPort() {
        return port;
    }

}