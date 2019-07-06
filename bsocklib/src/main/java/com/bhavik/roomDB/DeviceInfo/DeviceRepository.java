package com.bhavik.roomDB.DeviceInfo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bhavik.roomDB.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DeviceRepository {
    DeviceInfoDao branchDao;
    long lastinsertedid;
    private LiveData<List<DeviceInfoEntity>> mAllDataL;
    private List<DeviceInfoEntity> mAllData;

    public DeviceRepository(@NonNull Application application) {
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        //init  Dao
        branchDao = appDatabase.deviceInfoDAO();
        mAllData = branchDao.getAll();
        mAllDataL = branchDao.getAllL();
    }



}