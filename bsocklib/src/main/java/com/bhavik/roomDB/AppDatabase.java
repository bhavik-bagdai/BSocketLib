package com.bhavik.roomDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.bhavik.roomDB.DeviceInfo.DeviceInfoDao;
import com.bhavik.roomDB.DeviceInfo.DeviceInfoEntity;
import com.bhavik.roomDB.SyncMaster.SyncMasterDao;

@Database(entities = {DeviceInfoEntity.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    //define static instance
    private static AppDatabase mInstance;

    //method to get room database
    public static AppDatabase getDatabase(Context context) {

        if (mInstance == null)
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "BSock")
                    .allowMainThreadQueries()
                    .build();

        return mInstance;
    }
    //method to remove instance
    public static void closeDatabase() {
        mInstance = null;
    }

    public abstract DeviceInfoDao deviceInfoDAO();
    public abstract SyncMasterDao syncMasterDao();

}