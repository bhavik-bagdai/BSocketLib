package com.bhavik.BsockOper;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.bhavik.roomDB.DBOperations;
import com.bhavik.roomDB.DeviceInfoEntity;
import com.bhavik.roomDB.DeviceInfoViewModel;

public class GeneralMethods {
    private Context context;
    DeviceInfoViewModel deviceInfoViewModel;
    public GeneralMethods(Context context){
        this.context = context;
        deviceInfoViewModel = ViewModelProviders.of((FragmentActivity) context).get(DeviceInfoViewModel.class);
    }
    public void InsertDevice(String Mac,String Ip,String sync, String StationName,String DeviceInfoStatus){
        DBOperations dbOperations = new DBOperations(context,deviceInfoViewModel);
        DeviceInfoEntity deviceInfoEntity = new DeviceInfoEntity(0,Mac,Ip,sync,StationName,DeviceInfoStatus);
        dbOperations.insertDevice(deviceInfoEntity);
    }
}
