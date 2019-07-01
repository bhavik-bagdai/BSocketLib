package com.bhavik.models;


import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("syncCode")
    int syncCode;

    @SerializedName("object")
    Object object;

    @SerializedName("ipAddress")
    String ipAddress;

    @SerializedName("syncMaster")
    SyncMaster syncMaster;

    public String getIpAddress() {
        return ipAddress;
    }

    public int getSyncCode() {
        return syncCode;
    }

    public void setSyncCode(int syncCode) {
        this.syncCode = syncCode;
    }

    public Object getObject() {
        return object;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public SyncMaster getSyncMaster() {
        return syncMaster;
    }
}