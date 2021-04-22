package application.BackEndClasses;

import application.abstractClasses.ChatNetworkConnection;
import java.io.Serializable;
import java.util.function.Consumer;

public class ChatNetworkClient extends ChatNetworkConnection {

    private String ip;
    private int port;

    public ChatNetworkClient(String ip, int port, Consumer<Serializable> onReceivedCallback) {
        super(onReceivedCallback);
        this.ip = ip;
        this.port = port;
    }

    @Override
    protected boolean isServer() {
        return false;
    }

    @Override
    protected String getIP() {
        return ip;
    }

    @Override
    protected int getPort() {
        return port;
    }

}
