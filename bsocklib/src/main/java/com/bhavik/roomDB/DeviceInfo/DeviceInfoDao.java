package com.bhavik.roomDB.DeviceInfo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DeviceInfoDao {
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

    @Query("SELECT * FROM DeviceInfoEntity")
    List<DeviceInfoEntity> getAllR();

    @Query("Update DeviceInfoEntity set DeviceInfoStatus = :status, lastSyncDate = :dat  where IP = :ip")
    long update(String ip, String status, String dat);

    @Query("SELECT * FROM DeviceInfoEntity WHERE deviceType=1 or deviceType=2")
    List<DeviceInfoEntity> getDevicesPOSWITHKDS();

    @Query("SELECT * FROM DeviceInfoEntity WHERE IP like :ip")
    boolean isIpAvail(String ip);
}