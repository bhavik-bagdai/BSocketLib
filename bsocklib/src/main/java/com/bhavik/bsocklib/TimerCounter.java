package com.bhavik.bsocklib;

import android.content.Context;
import android.util.Log;

import com.bhavik.BsockOper.GeneralMethods;
import com.bhavik.roomDB.DeviceInfo.DeviceInfoEntity;
import com.bhavik.socket.SocketMethods;
import com.bhavik.socket.utils.Utils;

import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TimerCounter {
    int counter;
    Context context;
    public TimerCounter(Context ctx) {
        context = ctx;
    }

    public void Timer() {
    }

    private Timer timer;
    private TimerTask timerTask;

    public void startTimer(int counter) {
        this.counter = counter;

        // set a new timer
        timer = new Timer();

        // initialize the timer task's job
        initializeTimerTask();

        // schedule the timer, to wake up every 1 second
        timer.schedule(timerTask, 1000, 1000);
    }

    // it sets the timer to print the counter every x seconds
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.i("in timer", "in timer ++++ " + (counter++));
                try {
                    GeneralMethods gm = new GeneralMethods(context);
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
                                                if (Utils.ARRAY_CONNECTED_SOCKET.get(i).getInetAddress().getHostAddress().equals(ip)) {
                                                    GeneralMethods gm = new GeneralMethods(context);
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

                    /*
                        if (socket != null && socket.getInetAddress() != null) {

                                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(WsUtils.ARRAY_CONNECTED_SOCKET.get(s).getOutputStream())), true);
                                if (out != null) {
                                    out.flush();
                                }else{
                                    out.flush();
                                }

                        }
                    */
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
    }

    public void stopTimerTask() {
        // stop the timer, if it's not already null
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
