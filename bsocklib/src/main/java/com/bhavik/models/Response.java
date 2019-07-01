package com.bhavik.models;

import com.google.gson.annotations.SerializedName;


/**
 * Created by android on 08/12/2016.
 */
public class Response {

    /**
     * The Sync code.
     */
    @SerializedName("syncCode")
    int syncCode;

    /**
     * The Object.
     */
    @SerializedName("object")
    Object object;

    /**
     * The Ip address.
     */
    @SerializedName("ipAddress")
    String ipAddress;

    /**
     * The Sync master.
     */
    @SerializedName("syncMaster")
    SyncMaster syncMaster;

    /**
     * Gets ip address.
     *
     * @return the ip address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Gets sync code.
     *
     * @return the sync code
     */
    public int getSyncCode() {
        return syncCode;
    }

    /**
     * Sets sync code.
     *
     * @param syncCode the sync code
     */
    public void setSyncCode(int syncCode) {
        this.syncCode = syncCode;
    }

    /**
     * Gets object.
     *
     * @return the object
     */
    public Object getObject() {
        return object;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Sets object.
     *
     * @param object the object
     */
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * Gets sync master.
     *
     * @return the sync master
     */
    public SyncMaster getSyncMaster() {
        return syncMaster;
    }
}
