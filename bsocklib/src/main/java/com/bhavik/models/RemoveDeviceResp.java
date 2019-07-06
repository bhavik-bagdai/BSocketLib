package com.bhavik.models;

import com.google.gson.annotations.SerializedName;

public class RemoveDeviceResp {
    @SerializedName("object")
    RemoveDevice object;

    public RemoveDevice getObject() {
        return object;
    }

    public void setObject(RemoveDevice object) {
        this.object = object;
    }

    public RemoveDeviceResp(RemoveDevice object) {


        this.object = object;
    }
}
