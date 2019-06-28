package com.bhavik.socket.client;

import android.util.Log;

import com.bhavik.socket.Ginterface.I_WS_Connections;
import com.bhavik.socket.utils.GConfig;
import com.bhavik.socket.utils.GUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;


public class Client extends Thread {

    private String serverMessage = "";
    private I_WS_Connections mMessageListener = null;
    private boolean mRun = false;
    private String ip = "";
    PrintWriter out;
    BufferedReader in;

    /**
     * Constructor of the class. OnMessagedReceived listens for the messages
     * received from server
     */
    public Client(I_WS_Connections i_ws_connections, String ip) {
        mMessageListener = i_ws_connections;
        this.ip = ip;
    }

    /**
     * Sends the message entered by client to the server
     *
     * @param message text entered by client
     */
    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
            out.flush();
        }
    }

    public void stopClient() {
        mRun = false;
    }

    @Override
    public void run() {

        mRun = true;

        try {

            if (!ip.equals("")) {
                // here you must put your computer's IP address.
                InetAddress serverAddr = InetAddress.getByName(ip);

                // create a socket to make the connection with the server
                final Socket socket = new Socket(serverAddr, GConfig.PORT);

                socket.setKeepAlive(true);
                try {

                    if (GUtils.ARRAY_CONNECTED_SOCKET == null)
                        GUtils.ARRAY_CONNECTED_SOCKET = new ArrayList<Socket>();

                    for (int c = 0; c < GUtils.ARRAY_CONNECTED_SOCKET.size(); c++) {
                        if (GUtils.ARRAY_CONNECTED_SOCKET.get(c).getInetAddress().getHostAddress().equals(ip)) {
                            GUtils.ARRAY_CONNECTED_SOCKET.remove(GUtils.ARRAY_CONNECTED_SOCKET.get(c));
                        }
                    }


                    GUtils.ARRAY_CONNECTED_SOCKET.add(socket);


                    // send the message to the server
                    out = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())), true);


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            connected(socket);
                        }
                    }).start();

                    // receive the message which the server sends back
                    in = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));

                    // in this while the client listens for the messages sent by the
                    // server
                    while (mRun) {
                        final String serverMessage = in.readLine();

                        if (serverMessage != null && mMessageListener != null) {
                            // call the method messageReceived from MyActivity class
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    displayMessage(serverMessage);
                                }
                            }).start();

                        } else {
                            mRun = false;
                            disConnected(socket.getInetAddress().getHostAddress());
                            disConnectedSync(socket.getInetAddress().getHostAddress());
                        }


                    }

                    Log.e("RESPONSE FROM SERVER", "S: Received Message: '"
                            + serverMessage + "'");

                } catch (Exception e) {

                    mRun = false;
                    disConnectedSync(ip);
                } finally {
                    // the socket must be closed. It is not possible to reconnect to
                    // this socket
                    // after it is closed, which means a new socket instance has to
                    // be created.
                    socket.close();
                }

            }
        } catch (Exception e) {

            mRun = false;
            disConnectedSync(ip);
        }
    }

    private void displayMessage(String serverMessage) {
        mMessageListener.onMessageReceive("", serverMessage);
    }

    private void connected(Socket socket) {
        mMessageListener.onConnect(socket);
    }

    private void disConnected(String ip) {
        mMessageListener.onDisconnect(ip);

        if (GUtils.ARRAY_CONNECTED_SOCKET != null) {
            for (int c = 0; c < GUtils.ARRAY_CONNECTED_SOCKET.size(); c++) {
                if (GUtils.ARRAY_CONNECTED_SOCKET.get(c).getInetAddress().getHostAddress().equals(ip)) {
                    GUtils.ARRAY_CONNECTED_SOCKET.remove(c);
                    break;
                }
            }
        }

    }

    private void disConnectedSync(String ip) {
        mMessageListener.onDisconnectSync(ip);
    }

}