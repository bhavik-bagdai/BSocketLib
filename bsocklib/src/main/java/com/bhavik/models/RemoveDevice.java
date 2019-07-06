package com.bhavik.models;

import com.google.gson.annotations.SerializedName;

public class RemoveDevice {
    @SerializedName("id")
    String id;
    @SerializedName("ip")
    String ip;

    public RemoveDevice(String id, String ip) {
        this.id = id;
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
