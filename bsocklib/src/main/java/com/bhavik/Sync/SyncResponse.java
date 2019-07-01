package com.bhavik.Sync;

import android.content.Context;
import android.util.Log;

import com.bhavik.BsockOper.GeneralMethods;
import com.bhavik.models.Response;
import com.bhavik.models.SendData;
import com.bhavik.models.SyncMaster;
import com.bhavik.socket.utils.Config;
import com.bhavik.socket.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.Socket;


/**
 * The type Sync response.
 */
public class SyncResponse {

    private static String[] params;
    private Context context;

    /**
     * Sets params.
     *
     * @param params the params
     */
    public void setParams(String[] params) {
        this.params = params;
    }

    /**
     * To json.
     *
     * @param context  the context
     * @param syncCode the sync code
     */
    public static void toJson(final Context context, int syncCode, String OrderInv, String Date, JsonObject jsonObject) {

        GeneralMethods generalMethods = new GeneralMethods(context);
                //Sync Master
                SyncMaster syncMaster = new SyncMaster(syncCode,null, OrderInv, Date);
                generalMethods.insertSMasterForPOS(syncMaster);

                SendData sendData = new SendData(syncCode, generalMethods.getCurrentIP(context), jsonObject, syncMaster);
                String json = new Gson().toJson(sendData);
                SyncResponse.sendMessageToAll(json,context);
    }

    public static boolean sendMessage(String message, String ip, Context context) {
        Log.d("json", "sendMessage1");
        try {

            /*MyDatabaseManager myDatabaseManager = new MyDatabaseManager(context).open();
            Cursor cursordevices = myDatabaseManager.getDevices();*/
            //new senddata(message,ip).execute();
            try {
                if (Utils.ARRAY_CONNECTED_SOCKET != null) {
                    for (int s = 0; s < Utils.ARRAY_CONNECTED_SOCKET.size(); s++) {
                        Log.d("json", "sendMessage2");
                        if(!Utils.ARRAY_CONNECTED_SOCKET.get(s).isConnected())
                        {
                            try{
                                InetAddress serverAddr = Utils.ARRAY_CONNECTED_SOCKET.get(s).getInetAddress();

                                // create a socket to make the connection with the server
                                final Socket socket = new Socket(serverAddr, Config.PORT);
                                Utils.ARRAY_CONNECTED_SOCKET.remove(s);
                                Utils.ARRAY_CONNECTED_SOCKET.add(s,socket);
                            }
                            catch (Exception e){

                            }
                        }
                        Socket socket = Utils.ARRAY_CONNECTED_SOCKET.get(s);
                        if (socket != null && socket.getInetAddress() != null) {
                            if (ip.equals(socket.getInetAddress().getHostAddress())) {
                                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(Utils.ARRAY_CONNECTED_SOCKET.get(s).getOutputStream())), true);
                                if (out != null) {
                                    out.println(message);
                                    Log.d("json", message);
                                    out.flush();
                                }
                                break;
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        //myDatabaseManager.close();
        //return true;
    }


    public static boolean sendMessageToAll(String message, Context context) {
        Log.d("json", "sendMessage1");
        try {
          try {
                if (Utils.ARRAY_CONNECTED_SOCKET != null) {
                    for (int s = 0; s <= Utils.ARRAY_CONNECTED_SOCKET.size(); s++) {
                        Log.d("json", "sendMessage2");
                        if(!Utils.ARRAY_CONNECTED_SOCKET.get(s).isConnected())
                        {
                            try{
                                InetAddress serverAddr = Utils.ARRAY_CONNECTED_SOCKET.get(s).getInetAddress();

                                // create a socket to make the connection with the server
                                final Socket socket = new Socket(serverAddr, Config.PORT);
                                Utils.ARRAY_CONNECTED_SOCKET.remove(s);
                                Utils.ARRAY_CONNECTED_SOCKET.add(s,socket);

                            }
                            catch (Exception e){

                            }
                        }
                        Socket socket = Utils.ARRAY_CONNECTED_SOCKET.get(s);
                        if (socket != null && socket.getInetAddress() != null) {

                            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(Utils.ARRAY_CONNECTED_SOCKET.get(s).getOutputStream())), true);
                            if (out != null) {
                                out.println(message);

                                /*MyDatabaseManager myDatabaseManager = new MyDatabaseManager(context).open();
                                AckOrder ackOrder = new AckOrder();
                                String ip = String.valueOf(GUtils.ARRAY_CONNECTED_SOCKET.get(s).getInetAddress());
                                if(ip.contains("/")){
                                    ip = ip.replace("/","");
                                }
                                ackOrder.setDeviceip(ip);
                                ackOrder.setInvnumber(POS_Utils.getCurrentInvoiceNo(context));
                                ackOrder.setStatus(0);
                                long val = myDatabaseManager.ackorder(ackOrder);

                                Log.d("json", message);
                                out.flush();*/
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public JsonElement fromJson(Context context, String message) {

        Response response = null;
        try {
            JsonReader reader = new JsonReader(new StringReader(message));
            reader.setLenient(true);
            response = new Gson().fromJson(reader, Response.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement json = null;
        if (response == null) {
            return json;
        }
         else {
            // Convert JSON to JsonElement, and later to String
            json = gson.fromJson(message, JsonElement.class);
            return json;
        }
    }
}