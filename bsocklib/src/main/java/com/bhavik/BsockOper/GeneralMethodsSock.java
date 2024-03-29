package com.bhavik.BsockOper;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;

import com.bhavik.models.SyncMaster;
import com.bhavik.roomDB.DBOperations;
import com.bhavik.roomDB.DeviceInfo.DeviceInfoEntity;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class GeneralMethodsSock {
    public Context context;
    public static Context ctx;
    public GeneralMethodsSock(Context context){
        this.context = context;
    }

    public long InsertDevice(String Mac, String Ip, String sync, String StationName, String DeviceInfoStatus, int deviceType){
        DBOperations dbOperations = new DBOperations(context);
        DeviceInfoEntity deviceInfoEntity = new DeviceInfoEntity(0,Mac,Ip,sync,StationName,DeviceInfoStatus,deviceType);
        return dbOperations.DeviceAdd(deviceInfoEntity);
    }

    public List<DeviceInfoEntity> getDevices(){
        DBOperations dbOperations = new DBOperations(context);
        return dbOperations.getAllData();
    }

    public LiveData<List<DeviceInfoEntity>> getDevicesL(){
        DBOperations dbOperations = new DBOperations(context);
        return dbOperations.getAllDataL();
    }

    public void updateDevice(String ip, String status, String strDate) {
        DBOperations dbOperations = new DBOperations(context);
        dbOperations.DeviceUpdate(ip,status,strDate);
    }

    public List<DeviceInfoEntity> getDevicesPOSWITHKDS() {
        DBOperations dbOperations = new DBOperations(context);
        return dbOperations.getDevicesPOSWITHKDS();
    }

    public boolean isIpAvail(String ip) {
        DBOperations dbOperations = new DBOperations(context);
        return dbOperations.isIpAvail(ip);
    }

    public String getCurrentIP(Context context){
        boolean WIFI = false;
        boolean MOBILE = false;
        boolean BLUETOOTH = false;
        boolean Ethernet = false;

        ConnectivityManager CM = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = CM.getAllNetworkInfo();

        for (NetworkInfo netInfo : networkInfo) {
            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (netInfo.isConnected())
                    WIFI = true;

            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                if (netInfo.isConnected())
                    MOBILE = true;

            if (netInfo.getTypeName().equalsIgnoreCase("Ethernet"))
                if (netInfo.isConnected())
                    Ethernet = true;

        }

        if(WIFI == true) {
            return GetDeviceipWiFiData(context);
        }
        if(MOBILE == true) {
            return GetDeviceipMobileData();
        }
        if(Ethernet == true) {
            InetAddress i[] =  GetDeviceipEtData(context);
            return i[1].getHostAddress();
        }

        return "";
    }

    public String GetDeviceipMobileData(){
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements();) {
                NetworkInterface networkinterface = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = networkinterface.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("Current IP", ex.toString());
        }
        return null;
    }

    public String GetDeviceipWiFiData(Context context)
    {
        WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        @SuppressWarnings("deprecation")

        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return ip;
    }

    public InetAddress[] GetDeviceipEtData(Context context)
    {
        ArrayList<InetAddress> addresses = new ArrayList<InetAddress>();
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while( en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        addresses.add(inetAddress);
                    }
                }
            }
        } catch (SocketException ex) {
            String LOG_TAG = null;
            System.out.println(LOG_TAG + " : " + ex.toString());
        }

        return addresses.toArray(new InetAddress[1]);
    }

    public void insertSMasterForPOS(SyncMaster syncMaster) {
    }
}