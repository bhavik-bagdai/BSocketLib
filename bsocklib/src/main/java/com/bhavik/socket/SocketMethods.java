package com.bhavik.socket;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.bhavik.BsockOper.GeneralMethodsSock;
import com.bhavik.Sync.SyncMasterResponse;
import com.bhavik.Sync.SyncResponse;
import com.bhavik.Sync.code.SyncCode;
import com.bhavik.models.SendData;
import com.bhavik.socket.client.ConnectionAsync;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class SocketMethods {
    private Context context;

    public SocketMethods(Context context) {
        this.context = context;
    }

    public void connect(final String ip) {
        if (context != null) {
            GeneralMethodsSock gm = new GeneralMethodsSock(context);
            if (ip != null) {

                if (gm.isIpAvail(ip)) {
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a", Locale.US);
                    String strDate = sdf.format(c.getTime());
                    gm.updateDevice(ip, "1", strDate);
                } else {
                    WifiManager wifiMgr = (WifiManager) context.getApplicationContext().getSystemService(context.WIFI_SERVICE);
                    WifiInfo wifiInfo = wifiMgr.getConnectionInfo();

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a", Locale.US);
                    String strDate = sdf.format(c.getTime());

                    gm.InsertDevice(wifiInfo.getMacAddress().toString(),ip,
                                         "1","POS",
                                         strDate, 1);
                    new ConnectionAsync().execute(context);
                    }


                }

                Log.d("jsonSending..", "sent");
                SyncMasterResponse syncMasterResponse = new SyncMasterResponse(context, ip);
                syncMasterResponse.sendSyncMaster();

                SendData sendData = new SendData(SyncCode.C_ADD_DEVICE_REQUEST, gm.getCurrentIP(context), new Object(), null);
                String json = new Gson().toJson(sendData);
                SyncResponse.sendMessage(json, ip,context);
        }
    }

    /**
     * Method for Disconnect Socket by Ip.
     *
     * @param ip the ip
     */
    public void disConnect(String ip) {
        if (context != null) {
            GeneralMethodsSock gm = new GeneralMethodsSock(context);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a", Locale.US);
            String strDate = sdf.format(c.getTime());

            gm.updateDevice(ip, "0", strDate);

            /*Log.d("jsonSending..", "sent");
            SyncMasterResponse syncMasterResponse = new SyncMasterResponse(context, ip);
            syncMasterResponse.sendSyncMaster();

            SendData sendData = new SendData(SyncCode.C_REMOVE_DEVICE, gm.getCurrentIP(context), new Object(), null);
            String json = new Gson().toJson(sendData);
            SyncResponse.sendMessageToAll(json, context);*/
        }
    }
}