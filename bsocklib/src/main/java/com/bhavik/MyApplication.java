package com.bhavik;

import android.app.Application;

import com.bhavik.socket.server.Server;

public class MyApplication extends Application {

    public static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    private Server mServer;
    {
        mServer = new Server(this);
    }

    public static MyApplication getInstance() {return instance;}

    public Server getServer(){
        return mServer;
    }
}