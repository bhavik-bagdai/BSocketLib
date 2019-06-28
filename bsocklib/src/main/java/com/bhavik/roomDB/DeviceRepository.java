package com.bhavik.roomDB;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DeviceRepository {
    DeviceInfoDAO branchDao;
    long lastinsertedid;
    private LiveData<List<DeviceInfoEntity>> mAllData;

    public DeviceRepository(@NonNull Application application) {
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        //init  Dao
        branchDao = appDatabase.deviceInfoDAO();
        mAllData = branchDao.getAll();
    }

    public LiveData<List<DeviceInfoEntity>> getAllData() {
        return mAllData;
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
}