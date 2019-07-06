package com.bhavik.roomDB;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.bhavik.BsockOper.GeneralMethodsSock;
import com.bhavik.roomDB.DeviceInfo.DeviceInfoDao;
import com.bhavik.roomDB.DeviceInfo.DeviceInfoEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DBOperations {
    LiveData<List<DeviceInfoEntity>> mAllDataL;
    List<DeviceInfoEntity> mAllData;
    DeviceInfoDao branchDao = null;
    Context context;
    private Long lastinsertedid;

    public DBOperations(){
    }

    public DBOperations(Context context) {
        this.context = context;
        Log.d("Scontext:",GeneralMethodsSock.ctx.toString());
        AppDatabase appDatabase = AppDatabase.getDatabase(GeneralMethodsSock.ctx);
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

    //method to add note
    public void DeviceDel(String IP) {
        branchDao.delete(IP);
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
