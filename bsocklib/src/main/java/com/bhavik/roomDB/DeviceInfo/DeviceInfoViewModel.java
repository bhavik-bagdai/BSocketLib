package com.bhavik.roomDB.DeviceInfo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class DeviceInfoViewModel extends AndroidViewModel {
    DeviceRepository deviceRepository;
    LiveData<List<DeviceInfoEntity>> mAllDataL;
    List<DeviceInfoEntity> mAllData;

    public DeviceInfoViewModel(@NonNull Application application) {
        super(application);
        deviceRepository = new DeviceRepository(application);
        mAllDataL = deviceRepository.getAllDataL();
        mAllData = deviceRepository.getAllData();
    }
    public List<DeviceInfoEntity> getAllData() {
        return mAllData;
    }

    public LiveData<List<DeviceInfoEntity>> getAllDataL() {
        return mAllDataL;
    }

    public long addDevice(DeviceInfoEntity deviceInfoEntity) {
        return deviceRepository.DeviceAdd(deviceInfoEntity);
    }


    public void updateDevice(String ip, String status, String strDate) {
        deviceRepository.DeviceUpdate(ip,status,strDate);
    }

    public List<DeviceInfoEntity> getDevicesPOSWITHKDS() {
        return deviceRepository.getDevicesPOSWITHKDS();
    }

    public boolean isIpAvail(String ip) {
        return deviceRepository.isIpAvail(ip);
    }
}