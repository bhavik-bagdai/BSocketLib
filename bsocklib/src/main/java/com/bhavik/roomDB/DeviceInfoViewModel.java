package com.bhavik.roomDB;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class DeviceInfoViewModel extends AndroidViewModel {
    DeviceRepository deviceRepository;
    LiveData<List<DeviceInfoEntity>> mAllData;

    public DeviceInfoViewModel(@NonNull Application application) {
        super(application);
        deviceRepository = new DeviceRepository(application);
        mAllData = deviceRepository.getAllData();
    }
    public LiveData<List<DeviceInfoEntity>> getAllData() {
        return mAllData;
    }

    public long addDevice(DeviceInfoEntity deviceInfoEntity) {
        return deviceRepository.DeviceAdd(deviceInfoEntity);
    }
}
