package com.bhavik.models;

/**
 * Created by Websmithp6 on 12/29/2016.
 */
public class SyncMaster {
    /**
     * The Sync code.
     */
    int SyncCode;
    /**
     * The Id.
     */
    String id, /**
     * The Batch code.
     */
    batchCode, /**
     * The Date.
     */
    date;

    /**
     * Instantiates a new Sync master.
     *
     * @param syncCode  the sync code
     * @param id        the id
     * @param batchCode the batch code
     * @param date      the date
     */
    public SyncMaster(int syncCode, String id, String batchCode, String date) {
        SyncCode = syncCode;
        this.batchCode = batchCode;
        this.date = date;
        this.id = id;
    }

    /**
     * Gets sync code.
     *
     * @return the sync code
     */
    public int getSyncCode() {
        return SyncCode;
    }

    /**
     * Gets batch code.
     *
     * @return the batch code
     */
    public String getBatchCode() {
        return batchCode;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }
}
