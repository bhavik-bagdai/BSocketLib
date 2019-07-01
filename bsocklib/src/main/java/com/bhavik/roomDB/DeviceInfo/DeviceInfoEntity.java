package com.bhavik.roomDB.DeviceInfo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class DeviceInfoEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    long devid;
    @ColumnInfo(name = "Mac")
    String mac;
    @ColumnInfo(name = "IP")
    String ip;
    @ColumnInfo(name = "Sync")
    String sync;
    @ColumnInfo(name = "StationName")
    String stationName;
    @ColumnInfo(name = "DeviceInfoStatus")
    String deviceinfoStatus;
    @ColumnInfo(name = "LastSyncDate")
    String lastSyncDate;
    @ColumnInfo(name = "deviceType")
    int deviceType;


    public DeviceInfoEntity(long devid, String mac, String ip, String sync, String stationName, String deviceinfoStatus,int deviceType) {
        this.devid = devid;
        this.mac = mac;
        this.ip = ip;
        this.sync = sync;
        this.stationName = stationName;
        this.deviceinfoStatus = deviceinfoStatus;
        this.deviceType = deviceType;
    }

    public String getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(String lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public long getDevid() {
        return devid;
    }

    public void setDevid(long devid) {
        this.devid = devid;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getDeviceinfoStatus() {
        return deviceinfoStatus;
    }

    public void setDeviceinfoStatus(String deviceinfoStatus) {
        this.deviceinfoStatus = deviceinfoStatus;
    }
}