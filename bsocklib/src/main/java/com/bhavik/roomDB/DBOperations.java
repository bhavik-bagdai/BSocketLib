package com.bhavik.roomDB;

import android.content.Context;

import com.bhavik.roomDB.DeviceInfo.DeviceInfoEntity;
import com.bhavik.roomDB.DeviceInfo.DeviceInfoViewModel;

import java.util.List;

public class DBOperations {
    DeviceInfoViewModel deviceInfoViewModel;
    Context context;
    public DBOperations(){
    }

    public DBOperations(Context context, DeviceInfoViewModel deviceInfoViewModel) {
        this.context = context;
        this.deviceInfoViewModel = deviceInfoViewModel;
    }

    public void insertDevice(DeviceInfoEntity deviceInfoEntity) {
        deviceInfoViewModel.addDevice(deviceInfoEntity);
    }

    public List<DeviceInfoEntity> getDevices() {
        return deviceInfoViewModel.getAllRData();
    }

    public void updateDevice(String ip, String status, String strDate) {
        deviceInfoViewModel.updateDevice(ip,status,strDate);
    }

    public List<DeviceInfoEntity> getDevicesPOSWITHKDS() {
        return deviceInfoViewModel.getDevicesPOSWITHKDS();
    }

    public boolean isIpAvail(String ip) {
        return deviceInfoViewModel.isIpAvail(ip);
    }
}
