package com.bhavik.roomDB.SyncMaster;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class SyncMasterEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    long synid;
    @ColumnInfo(name = "IP")
    String ip;
    @ColumnInfo(name = "SyncCode")
    String syncCode;
    @ColumnInfo(name = "batchCode")
    String batchCode;
    @ColumnInfo(name = "SyncDate")
    String syncDate;


    public SyncMasterEntity(long synid, String ip, String syncCode, String batchCode, String syncDate) {
        this.synid = synid;
        this.ip = ip;
        this.syncCode = syncCode;
        this.batchCode = batchCode;
        this.syncDate = syncDate;
    }

    public long getSynid() {
        return synid;
    }

    public void setSynid(long synid) {
        this.synid = synid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSyncCode() {
        return syncCode;
    }

    public void setSyncCode(String syncCode) {
        this.syncCode = syncCode;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(String syncDate) {
        this.syncDate = syncDate;
    }
}