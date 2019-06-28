package com.bhavik.socket.server;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.JsonReader;

import com.bhavik.socket.Ginterface.I_WS_Connections;
import com.bhavik.socket.client.ConnectOtherPOS;
import com.bhavik.socket.utils.GConfig;

import java.io.IOException;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

public class Server {
    Context context;
    //ServerSocket serverSocket;
    ServerThread client;
    I_WS_Connections i_ws_connections;

    public Server(Context context) {
        this.context = context;
    }

    public void start() {
        Thread socketServerThread = new Thread(new SocketServerThread());
        socketServerThread.start();
    }

    public int getPort() {
        return GConfig.PORT;
    }

  /*  public void onDestroy() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    public void setListner(I_WS_Connections i_ws_connections) {
        this.i_ws_connections = i_ws_connections;
        ConnectOtherPOS.i_ws_connections = i_ws_connections;
    }

    private class SocketServerThread extends Thread {

        int count = 0;

        @Override
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(GConfig.PORT);
                while (true) {
                    final Socket socket = serverSocket.accept();
                    count++;
                    if (i_ws_connections != null) {
                        //playBeep();
                        i_ws_connections.onConnect(socket);
                    }
                    addThread(socket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void addThread(Socket socket) {

        client = new ServerThread(new I_WS_Connections() {
            @Override
            public void onMessageReceive(String messageTypefinal, final String message) {

                //{"ipAddress":"192.168.31.57","object":{},"syncCode":604}
                if (isDeviceReq(message))
                    playBeep();

                if (i_ws_connections != null) {
                    i_ws_connections.onMessageReceive("", message);
                }
                    //TODO BHAVIK SYNC RESPONSE WORK
                    /*Response response = null;
                    try {
                        com.google.gson.stream.JsonReader reader = new com.google.gson.stream.JsonReader(new StringReader(message));
                        reader.setLenient(true);
                        response = new Gson().fromJson(reader, Response.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/

                    //if (response.getSyncCode() == SyncCode.C_NEW_ORDER) {
                      /*  MyDatabaseManager myDatabaseManager = new MyDatabaseManager(context).open();
                        NewOrder newOrder = new Gson().fromJson(message, NewOrder.class);
                        List<OrdersMaster> ordersList = newOrder.getObject();

                        SendData sendData = new SendData(SyncCode.C_ORDER_CONFORMATION, POS_Utils.getSelfIpAddress(context), ordersList, null);
                        String json = new Gson().toJson(sendData);
                        SyncResponse.sendMessage(json, response.getIpAddress(), context);*/
                    //}
            }

            @Override
            public void onDisconnect(String ip) {

                //playBeep();

                if (i_ws_connections != null) {
                    i_ws_connections.onDisconnect(ip);
                }


            }

            @Override
            public void onConnect(Socket socket) {

            }

            @Override
            public void onDisconnectSync(String ip) {

            }
        }, socket);

        try {
            client.open();
            client.start();
        } catch (IOException ioe) {
            System.out.println("Error opening thread: " + ioe);
        }
    }

    public String getIpAddress() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (enumNetworkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = enumNetworkInterfaces.nextElement();
                Enumeration<InetAddress> enumInetAddress = networkInterface.getInetAddresses();
                while (enumInetAddress.hasMoreElements()) {
                    InetAddress inetAddress = enumInetAddress.nextElement();
                    if (inetAddress.isSiteLocalAddress()) {
                        ip = inetAddress.getHostAddress();
                    }
                }
            }
            ip += ":" + getPort();

        } catch (SocketException e) {
            e.printStackTrace();
            ip += "Something Wrong! " + e.toString() + "\n";
        }
        return ip;
    }

    /**
     * Plays device's default notification sound
     */
    public void playBeep() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(context,notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isDeviceReq(String message) {
        JsonReader reader = new JsonReader(new StringReader(message.replace(" ", "")));
        reader.setLenient(true);

        /*try {
            BranchLoginResponse response = new Gson().fromJson(reader, BranchLoginResponse.class);
            if (response.getSyncCode() == SyncCode.C_ADD_DEVICE_REQUEST || response.getSyncCode() == SyncCode.C_ADD_DEVICE_RESPONSE)
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return true;
    }
}