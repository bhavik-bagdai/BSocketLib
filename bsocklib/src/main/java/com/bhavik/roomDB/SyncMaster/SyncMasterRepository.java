package com.bhavik.roomDB.SyncMaster;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bhavik.roomDB.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SyncMasterRepository {
    SyncMasterDao syncMasterDao;
    long lastinsertedid;
    private LiveData<List<SyncMasterEntity>> mAllData;

    public SyncMasterRepository(@NonNull Application application) {
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        //init  Dao
        syncMasterDao = appDatabase.syncMasterDao();
        mAllData = syncMasterDao.getAll();
    }

    public LiveData<List<SyncMasterEntity>> getAllData() {
        return mAllData;
    }

    //method to add note
    public long DeviceAdd(SyncMasterEntity syncMasterEntity) {
        try {
            lastinsertedid = new DeviceAddEx().execute(syncMasterEntity).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return lastinsertedid;
    }

    public List<SyncMasterEntity> getAllRData() {
        List<SyncMasterEntity> getalldevice= syncMasterDao.getAllR();
        return getalldevice;
    }

    //Async task to add note
    public class DeviceAddEx extends AsyncTask<SyncMasterEntity, Long, Long> {

        @Override
        protected Long doInBackground(SyncMasterEntity... syncMasterEntities) {
            lastinsertedid = syncMasterDao.insert(syncMasterEntities[0]);
            return lastinsertedid;
        }

        @Override
        protected void onPostExecute(Long aVoid) {
            super.onPostExecute(aVoid);
            Log.e("BranchLogin----", "Done");
        }
    }
}