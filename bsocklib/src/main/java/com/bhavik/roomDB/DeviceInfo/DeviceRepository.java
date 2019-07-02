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

    public List<DeviceInfoEntity> getAllData() {
        return mAllData;
    }

    public LiveData<List<DeviceInfoEntity>> getAllDataL() {
        return mAllDataL;
    }

    //method to add note
    public long DeviceAdd(DeviceInfoEntity deviceInfoEntity) {
        try {
            lastinsertedid = new DeviceAddEx().execute(deviceInfoEntity).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return lastinsertedid;
    }

    public void DeviceUpdate(String ip, String status, String strDate) {
        new DeviceUp().execute(ip,status,strDate);
    }

    public List<DeviceInfoEntity> getDevicesPOSWITHKDS() {
        List<DeviceInfoEntity> getalldevice= branchDao.getDevicesPOSWITHKDS();
        return getalldevice;
    }

    public boolean isIpAvail(String ip) {
        return branchDao.isIpAvail(ip);
    }

    //Async task to add note
    public class DeviceAddEx extends AsyncTask<DeviceInfoEntity, Long, Long> {

        @Override
        protected Long doInBackground(DeviceInfoEntity... deviceInfoEntities) {
            lastinsertedid = branchDao.insert(deviceInfoEntities[0]);
            return lastinsertedid;
        }

        @Override
        protected void onPostExecute(Long aVoid) {
            super.onPostExecute(aVoid);
            Log.e("BranchLogin----", "Done");
        }
    }

    //Async task to add note
    public class DeviceUp extends AsyncTask<String, Long, Long> {

        @Override
        protected Long doInBackground(String... strings) {
            lastinsertedid = branchDao.update(strings[0],strings[1],strings[2]);
            return lastinsertedid;
        }

        @Override
        protected void onPostExecute(Long aVoid) {
            super.onPostExecute(aVoid);
            Log.e("BranchLogin----", "Done");
        }
    }

}