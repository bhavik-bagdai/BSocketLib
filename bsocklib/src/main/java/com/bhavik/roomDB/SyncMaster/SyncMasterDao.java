package com.bhavik.roomDB.SyncMaster;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bhavik.roomDB.DeviceInfo.DeviceInfoEntity;

import java.util.List;

@Dao
public interface SyncMasterDao {
    @Query("SELECT * FROM SyncMasterEntity WHERE synid = :id LIMIT 1")
    SyncMasterEntity findAdminById(long id);

    @Query("SELECT * FROM SyncMasterEntity")
    LiveData<List<SyncMasterEntity>> getAll();

    /*@Query("SELECT imageName FROM UserEntity")
    LiveData<List<UserEntity>> getAllImg();*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(SyncMasterEntity syncMasterEntity);

    @Delete
    void delete(SyncMasterEntity syncMasterEntity);

    @Update
    void update(SyncMasterEntity syncMasterEntity);

}