package com.bhavik.models;

public class pendinglist {
    String ip;
    int count;

    public pendinglist(String ip, int count) {
        this.ip = ip;
        this.count = count;
    }

    public pendinglist() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
