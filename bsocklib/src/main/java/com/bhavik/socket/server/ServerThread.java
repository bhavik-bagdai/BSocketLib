package com.bhavik.socket.server;

import com.bhavik.socket.Binterface.I_WS_Connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread {
    private I_WS_Connections i_ws_connections = null;
    private Socket _socket = null;
    private BufferedReader _streamIn = null;
    private boolean _isConnected = true;

    public ServerThread(I_WS_Connections i_ws_connections, Socket _socket) {
        this.i_ws_connections = i_ws_connections;
        this._socket = _socket;
    }

    public void run() {
        try {
            while (_isConnected) {
                String msg = _streamIn.readLine();
                if (msg != null) {
                    i_ws_connections.onMessageReceive("", msg);
                } else {
                    _isConnected = false;
                    i_ws_connections.onDisconnect(_socket.getInetAddress().getHostAddress());
                    close();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void open() throws IOException {
        _streamIn = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
    }

    public void close() throws IOException {
        if (_socket != null)
            _socket.close();

        if (_streamIn != null)
            _streamIn.close();
    }
}