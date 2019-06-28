package com.bhavik.roomDB;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class DBOperations {
    DeviceInfoViewModel deviceInfoViewModel;
    Context context;
    public DBOperations(){
    }

    public DBOperations(Context context,DeviceInfoViewModel deviceInfoViewModel) {
        this.context = context;
        this.deviceInfoViewModel = deviceInfoViewModel;
    }

    public void insertDevice(DeviceInfoEntity deviceInfoEntity) {
        deviceInfoViewModel.addDevice(deviceInfoEntity);
    }
}
