package com.bhavik.roomDB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DeviceInfoDAO {
    @Query("SELECT * FROM DeviceInfoEntity WHERE devid = :id LIMIT 1")
    DeviceInfoEntity findAdminById(long  id);

    @Query("SELECT * FROM DeviceInfoEntity")
    LiveData<List<DeviceInfoEntity>> getAll();

    /*@Query("SELECT imageName FROM UserEntity")
    LiveData<List<UserEntity>> getAllImg();*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(DeviceInfoEntity deviceInfoEntity);

    @Delete
    void delete(DeviceInfoEntity deviceInfoEntity);

    @Update
    void update(DeviceInfoEntity deviceInfoEntity);

}