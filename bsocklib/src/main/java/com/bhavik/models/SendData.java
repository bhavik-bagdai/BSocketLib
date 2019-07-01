package com.bhavik.models;

public class SendData {
    /**
     * The Sync code.
     */
    int syncCode;
    /**
     * The Object.
     */
    Object object;
    /**
     * The Ip address.
     */
    String ipAddress;
    /**
     * The Sync master.
     */
    SyncMaster syncMaster;

    /**
     * Instantiates a new Send data.
     *
     * @param syncCode   the sync code
     * @param ipAddress  the ip address
     * @param object     the object
     * @param syncMaster the sync master
     */
    public SendData(int syncCode, String ipAddress, Object object, SyncMaster syncMaster) {
        this.syncCode = syncCode;
        this.object = object;
        this.ipAddress = ipAddress;
        this.syncMaster = syncMaster;
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

    /**
     * Sets object.
     *
     * @param object the object
     */
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * Gets ip address.
     *
     * @return the ip address
     */
    public String getIpAddress() {
        return ipAddress;
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
