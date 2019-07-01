package com.bhavik.roomDB.SyncMaster;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class SyncMasterViewModel extends AndroidViewModel {
    SyncMasterRepository syncMasterRepository;
    LiveData<List<SyncMasterEntity>> mAllData;

    public SyncMasterViewModel(@NonNull Application application) {
        super(application);
        syncMasterRepository = new SyncMasterRepository(application);
        mAllData = syncMasterRepository.getAllData();
    }
    public LiveData<List<SyncMasterEntity>> getAllData() {
        return mAllData;
    }

    public long addDevice(SyncMasterEntity syncMasterEntity) {
        return syncMasterRepository.DeviceAdd(syncMasterEntity);
    }
}