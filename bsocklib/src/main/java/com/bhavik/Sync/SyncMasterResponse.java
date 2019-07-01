package com.bhavik.Sync;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

/**
 * The type Sync master response.
 */
public class SyncMasterResponse {

    Context context;
    String ip;

    public SyncMasterResponse(Context context, String ip) {
        this.context = context;
        this.ip = ip;
    }
    public void sendSyncMaster() {

    /*    MyDatabaseManager myDatabaseManager = new MyDatabaseManager(context).open();
        List<SyncMaster> syncMasterList = myDatabaseManager.getSyncMasterByDeviceIp(ip);
        myDatabaseManager.close();
        for (SyncMaster syncMaster : syncMasterList) {
            toSync(syncMaster.getSyncCode(), syncMaster.getBatchCode());
        }*/
    }

    private void toSync(int syncCode, String batchCode) {
        /*MyDatabaseManager myDatabaseManager = new MyDatabaseManager(context).open();

        // New Order
        if (SyncCode.C_NEW_ORDER == syncCode) {

            List<OrdersMaster> list = myDatabaseManager.getOrderListByOrderId(batchCode);

            if (list.size() > 0) {
                Log.d("json", "sent1");
                SendData sendData = new SendData(SyncCode.C_NEW_ORDER, POS_Utils.getSelfIpAddress(context), list, null);
                String json = new Gson().toJson(sendData);
                SyncResponse.sendMessage(json, ip,context);
            }
        }
        // Pay Order

        else if (SyncCode.C_PAY_ORDER == syncCode) {

            List<OrdersMaster> list = myDatabaseManager.getOrderListByOrderId(batchCode);

            if (list.size() > 0) {
                SendData sendData = new SendData(SyncCode.C_PAY_ORDER, POS_Utils.getSelfIpAddress(context), list, null);
                String json = new Gson().toJson(sendData);
                SyncResponse.sendMessage(json, ip,context);
            }
        }
        // Cancel Order

        else if (SyncCode.C_CANCEL_ORDER == syncCode) {

            List<OrdersMaster> list = myDatabaseManager.getOrderListByOrderId(batchCode);

            if (list.size() > 0) {
                SendData sendData = new SendData(SyncCode.C_CANCEL_ORDER, POS_Utils.getSelfIpAddress(context), list, null);
                String json = new Gson().toJson(sendData);
                SyncResponse.sendMessage(json, ip,context);
            }
            // Hold Order

        } else if (SyncCode.C_HOLD_ORDER == syncCode) {

            List<OrdersMaster> list = myDatabaseManager.getOrderListByOrderId(batchCode);

            if (list.size() > 0) {
                SendData sendData = new SendData(SyncCode.C_HOLD_ORDER, POS_Utils.getSelfIpAddress(context), list, null);
                String json = new Gson().toJson(sendData);
                SyncResponse.sendMessage(json, ip,context);
            }
        }
        // Release Order

        else if (SyncCode.C_RELEASE_ORDER == syncCode) {

            List<OrdersMaster> list = myDatabaseManager.getOrderListByOrderId(batchCode);

            if (list.size() > 0) {
                SendData sendData = new SendData(SyncCode.C_RELEASE_ORDER, POS_Utils.getSelfIpAddress(context), list, null);
                String json = new Gson().toJson(sendData);
                SyncResponse.sendMessage(json, ip,context);
            }
            // Update Item Status

        } else if (SyncCode.C_UPDATE_ITEM_STATUS == syncCode) {

            List<OrderItemMaster> list = myDatabaseManager.getUpdateItemById(batchCode);

            if (list.size() > 0) {
                SendData sendData = new SendData(SyncCode.C_UPDATE_ITEM_STATUS, POS_Utils.getSelfIpAddress(context), list, null);
                String json = new Gson().toJson(sendData);
                SyncResponse.sendMessage(json, ip, context);
            }
            // Update Item Hold/Release Status

        } else if (SyncCode.C_UPDATE_ITEM_HOLD_RELEASE == syncCode) {

            List<OrderItemMaster> list = myDatabaseManager.getUpdateItemById(batchCode);

            if (list.size() > 0) {
                SendData sendData = new SendData(SyncCode.C_UPDATE_ITEM_HOLD_RELEASE, POS_Utils.getSelfIpAddress(context), list, null);
                String json = new Gson().toJson(sendData);
                SyncResponse.sendMessage(json, ip,context);
            }
            // Update Item Discount

        } else if (SyncCode.C_UPDATE_ITEM_DISCOUNT == syncCode) {

            List<OrderItemMaster> list = myDatabaseManager.getUpdateItemById(batchCode);

            if (list.size() > 0) {
                SendData sendData = new SendData(SyncCode.C_UPDATE_ITEM_DISCOUNT, POS_Utils.getSelfIpAddress(context), list, null);
                String json = new Gson().toJson(sendData);
                SyncResponse.sendMessage(json, ip,context);
            }
            // Update Item Complimentary or Spoil

        } else if (SyncCode.C_UPDATE_ITEM_COMP_SPOIL == syncCode) {

            List<OrderItemMaster> list = myDatabaseManager.getUpdateItemById(batchCode);

            if (list.size() > 0) {
                SendData sendData = new SendData(SyncCode.C_UPDATE_ITEM_COMP_SPOIL, POS_Utils.getSelfIpAddress(context), list, null);
                String json = new Gson().toJson(sendData);
                SyncResponse.sendMessage(json, ip,context);
            }
            // Table Transfer

        } else if (SyncCode.C_TABLE_TRANSFER == syncCode) {

            List<TableTransfer> list = myDatabaseManager.getTableTransferById(batchCode);

            if (list.size() > 0) {
                SendData sendData = new SendData(SyncCode.C_TABLE_TRANSFER, POS_Utils.getSelfIpAddress(context), list, null);
                String json = new Gson().toJson(sendData);
                SyncResponse.sendMessage(json, ip,context);
            }
            // Table Merge

        } else if (SyncCode.C_TABLE_EMPLOYEE_TRANSFER== syncCode) {

            List<EmployeeTableTransfer> list = myDatabaseManager.getEmployeeTableTransferById(batchCode);

            if (list.size() > 0) {
                SendData sendData = new SendData(SyncCode.C_TABLE_EMPLOYEE_TRANSFER, POS_Utils.getSelfIpAddress(context), list, null);
                String json = new Gson().toJson(sendData);
                SyncResponse.sendMessage(json, ip,context);
            }
            // Table Merge


        } else if (SyncCode.C_TABLE_MERGE == syncCode) {

            List<TableMerge> list = myDatabaseManager.getTableMergeById(batchCode);

            if (list.size() > 0) {
                SendData sendData = new SendData(SyncCode.C_TABLE_MERGE, POS_Utils.getSelfIpAddress(context), list, null);
                String json = new Gson().toJson(sendData);
                SyncResponse.sendMessage(json, ip,context);
            }
            // Table Release

        } else if (SyncCode.C_TABLE_RELEASE == syncCode) {

            List<TableRelease> list = myDatabaseManager.getTableReleaseById(batchCode);

            if (list.size() > 0) {
                SendData sendData = new SendData(SyncCode.C_TABLE_RELEASE, POS_Utils.getSelfIpAddress(context), list, null);
                String json = new Gson().toJson(sendData);
                SyncResponse.sendMessage(json, ip,context);
            }
            // Table Reservation

        }

        myDatabaseManager.close();*/
        deleteSyncMaster(syncCode, batchCode);
    }

    /**
     * Method for Delete Sync Master Data
     *
     * @param syncCode
     * @param batchCode
     */
    private void deleteSyncMaster(int syncCode, String batchCode) {
        /*Log.d("json", "delete");
        MyDatabaseManager myDatabaseManager = new MyDatabaseManager(context).open();
        myDatabaseManager.deleteSyncMasterByBatchCode(batchCode, ip, syncCode);
        myDatabaseManager.close();*/
    }
}
