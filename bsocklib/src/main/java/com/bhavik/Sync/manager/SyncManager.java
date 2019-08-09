package com.bhavik.Sync.manager;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import com.bhavik.BsockOper.GeneralMethodsSock;
import com.bhavik.BsockOper.GeneralMethodsSock;
import com.bhavik.roomDB.DeviceInfo.DeviceInfoEntity;
import com.bhavik.socket.SocketMethods;
import com.bhavik.socket.utils.Utils;

import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SyncManager {
    private static SyncManager screenSaverManager;
    private Context context;
    private final long IDLE_DURATION = 3000;
    private final long INTERVAL = 3000;
    private CountDownTimer countDownTimer;

    /**
     * Method for get Singleton Instance of SyncManager
     *
     * @param context the context
     * @return the instance
     */
    public static SyncManager getInstance(Context context) {
        if (screenSaverManager == null)
            screenSaverManager = new SyncManager(context);
        return screenSaverManager;
    }

    /**
     * Constructor of SyncManager Class
     *
     * @param context
     */
    private SyncManager(final Context context) {
        this.context = context;
        countDownTimer = new CountDownTimer(IDLE_DURATION, INTERVAL) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                Log.v("MyCountDownTimer", "Counter finished");
                try {
                    GeneralMethodsSock gm = new GeneralMethodsSock(context);
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a", Locale.US);
                    String strDate = sdf.format(c.getTime());
                    List<DeviceInfoEntity> deviceInfoList = gm.getDevicesPOSWITHKDS();

                    for (DeviceInfoEntity deviceInfo : deviceInfoList) {
                        final String ip = deviceInfo.getIp();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                            boolean isConnected = false;
                            try {
                                /*  Process p1 = java.lang.Runtime.getRuntime().exec("ping -n 1 " + "192.168.0.16");
                                int returnVal = p1.waitFor();
                                boolean reachable = (returnVal == 0);
                                isConnected = reachable;*/
                                isConnected = InetAddress.getByName(ip).isReachable(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if(!isConnected)
                            {
                                Process p1 = null;
                                try {
                                    p1 = Runtime.getRuntime().exec("ping -n 1 " + ip);
                                    int returnVal = p1.waitFor();
                                    boolean reachable = (returnVal == 0);
                                    isConnected = reachable;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                            SocketMethods socketMethods = new SocketMethods(context);
                            if (!isConnected) {
                                socketMethods.disConnect(ip);
                                Log.d("Status", ip + " is Disconnected");
                            } else {
                                if (Utils.ARRAY_CONNECTED_SOCKET != null) {
                                    for (int i = 0; i < Utils.ARRAY_CONNECTED_SOCKET.size(); i++) {
                                        try {
                                            if (Utils.ARRAY_CONNECTED_SOCKET.get(i).getInetAddress().getHostAddress().contains(ip)) {
                                                GeneralMethodsSock gm = new GeneralMethodsSock(context);
                                                Calendar c = Calendar.getInstance();
                                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a", Locale.US);
                                                String strDate = sdf.format(c.getTime());
                                                gm.updateDevice(ip, "1", strDate);
                                                break;
                                            }
                                        } catch (NullPointerException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                            }
                        }).start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startTimer();
            }
        };
    }

    /**
     * Method for Start timer of Screen Saver.
     */
    public void startTimer() {
        Log.v("ScreenSaverManager", "Timer Started");
        countDownTimer.cancel();
        countDownTimer.start();
    }

    /**
     * Method for Stop timer of Screen Saver.
     */
    public void stopTimer() {
        Log.v("ScreenSaverManager", "Timer stopped");
        countDownTimer.cancel();
    }
}
