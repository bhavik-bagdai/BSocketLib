package com.bhavik.socket.client;

import android.content.Context;
import android.os.AsyncTask;
import com.bhavik.BsockOper.GeneralMethodsSock;
import com.bhavik.roomDB.DeviceInfo.DeviceInfoEntity;
import java.util.List;

public class ConnectionAsync extends AsyncTask<Context, Void, Void> {
    @Override
    protected Void doInBackground(Context... params) {
        GeneralMethodsSock gm = new GeneralMethodsSock(params[0]);
        List<DeviceInfoEntity> deviceInfoEntityList = gm.getDevices();
        for (DeviceInfoEntity deviceinfoEntity : deviceInfoEntityList) {
            new ConnectOtherPOS(params[0],deviceinfoEntity.getIp());
        }
        return null;
    }
}