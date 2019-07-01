package com.bhavik.Sync.manager;

import android.content.Context;
import android.os.CountDownTimer;


/**
 * Created by Mahesh Chakkarwar on 05-07-2016.
 */
public class ReSyncManager {
    private static ReSyncManager reSyncManager;
    private Context context;
    private final long IDLE_DURATION = 120000;
    private final long INTERVAL = 120000;
    private CountDownTimer countDownTimer;

    /**
     * Method for get Singleton Instance of ReSyncManager
     *
     * @param context the context
     * @return the instance
     */
    public static ReSyncManager getInstance(Context context) {
        if (reSyncManager == null)
            reSyncManager = new ReSyncManager(context);
        return reSyncManager;
    }

    /**
     * Constructor of SyncManager Class
     *
     * @param context
     */
    private ReSyncManager(final Context context) {
        this.context = context;
        /*countDownTimer = new CountDownTimer(IDLE_DURATION, INTERVAL) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                try {
//                    Toast.makeText(context,"Resync",Toast.LENGTH_SHORT).show();

                    // get Re Sync Order
                    MyDatabaseManager myDatabaseManager = new MyDatabaseManager(context).open();
                    List<OrdersMaster> orders = myDatabaseManager.getOrderDetailForReSync();
                    for(OrdersMaster order:orders) {
                        List<Items> itemsList = myDatabaseManager.getOrderItemsForReSync(order.getInvoiceNo());
                        order.setItemsList(itemsList);
                    }

                    // get Tables
                    ReSyncData reSyncData = new ReSyncData();
                    reSyncData.setObject(orders);
                    reSyncData.setTables(myDatabaseManager.getTablesForReSync());
                    myDatabaseManager.close();

                    SendData sendData = new SendData(SyncCode.C_RE_SYNC, POS_Utils.getSelfIpAddress(context), reSyncData, null);
                    String json = new Gson().toJson(sendData);
                    SyncResponse.sendMessageToAll(json);

                } catch (Exception e) {
                    e.printStackTrace();
                }

//                startTimer();

            }
        };*/
    }

    /**
     * Method for Start timer of Screen Saver.
     */
    public void startTimer() {
        countDownTimer.cancel();
        countDownTimer.start();
    }

    /**
     * Method for Stop timer of Screen Saver.
     */
    public void stopTimer() {
        countDownTimer.cancel();
    }
}
