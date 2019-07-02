package com.bhavik.socket.client;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.bhavik.socket.Binterface.I_WS_Connections;

import java.io.StringReader;
import java.net.Socket;
import java.net.SocketException;

public class ConnectOtherPOS {

    private Context context;
    private Client mClient;
    public static I_WS_Connections i_ws_connections;

    public ConnectOtherPOS(Context context, String ip) {
        this.context = context;
        if (!ip.equals("")) {
            //we create a Client object and
            new connectTask().execute(ip);
        }
    }

    public String getOtherServer(String gateway, int ip) {
        gateway = gateway.replace(".", "-");
        String[] oldGateway = gateway.split("-");
        String grubbrrDSN = oldGateway[0] + "." + oldGateway[1] + "." + oldGateway[2] + "." + ip;
        return grubbrrDSN;
    }

    public String intToIp(int i) {
        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                ((i >> 24) & 0xFF);
    }

    /**
     * Plays device's default notification sound
     */
    public void playBeep() {
        try {
            Uri notification = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(context,
                    notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class connectTask extends AsyncTask<String, String, Client> {

        @Override
        protected Client doInBackground(final String... message) {
            //we create a Client object and
            mClient = new Client(new I_WS_Connections() {
                @Override
                public void onConnect(Socket socket) {
                    try {
                        Thread.sleep(500);
                        /*MyDatabaseManager myDatabaseManager = new MyDatabaseManager(context).open();
                        String Crdate = LocalDate.now().toString("MM-dd-YYYY") + " " + DateUtils.formatDateTime(context, DateTime.now(), DateUtils.FORMAT_SHOW_TIME);
                        myDatabaseManager.updateDevice(socket.getInetAddress().getHostAddress(),"1", Crdate);
                        myDatabaseManager.close();
                        // Send SyncMaster


                        SyncMasterResponse syncMasterResponse = new SyncMasterResponse(context, socket.getInetAddress().getHostAddress());
                        syncMasterResponse.sendSyncMaster();*/
                        socket.setKeepAlive(true);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }
                    if (i_ws_connections != null) {
                        i_ws_connections.onConnect(socket);
                    }
                }

                @Override
                public void onDisconnectSync(String ip) {
                    //MyDatabaseManager myDatabaseManager = null;
                    try {
                        //new SocketMethods(context).disConnect(ip);
                        Thread.sleep(10000);

                        /*myDatabaseManager = new MyDatabaseManager(context).open();
                        if (myDatabaseManager.isIpAvail(ip)) {*/
                            new connectTask().execute(ip);
                        /*}
                        myDatabaseManager.close();*/
                        if (i_ws_connections != null) {
                            i_ws_connections.onDisconnect(ip);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        //myDatabaseManager.close();
                    }
                }

                @Override
                public void onDisconnect(String ip) {
                    // playBeep();

                    if (i_ws_connections != null) {
                        i_ws_connections.onDisconnect(ip);
                    }
                }

                @Override
                public void onMessageReceive(String messageType, String message) {
                    if (isDeviceReq(message))
                        playBeep();

                    if (i_ws_connections != null) {
                        i_ws_connections.onMessageReceive("", message);

                    }
                }
            }, message[0]);
            mClient.start();
            return null;
        }
    }

    private boolean isDeviceReq(String message) {
        try {
            JsonReader reader = new JsonReader(new StringReader(message.replace(" ", "")));
            reader.setLenient(true);
             /*BranchLoginResponse response = new Gson().fromJson(reader, BranchLoginResponse.class);
            if (response.getSyncCode() == SyncCode.C_ADD_DEVICE_REQUEST || response.getSyncCode() == SyncCode.C_ADD_DEVICE_RESPONSE)
                return false;*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}