package com.bhavik.socket.Binterface;

import java.io.Serializable;
import java.net.Socket;

public interface I_WS_Connections extends Serializable {

    void onConnect(Socket socket);

    void onDisconnectSync(String ip);

    void onDisconnect(String ip);

    void onMessageReceive(String messageType, String message);

}
