package com.tianyisc.sockettest;

import android.text.TextUtils;

/**
 * Created by fj on 2018/2/12.
 */

public class RecData {
    private String ip;
    private int port;
    private int recType;
    private String recData;
    private String sendData;

    public String getSendData() {
        return sendData;
    }

    public void setSendData(String sendData) {
        this.sendData = sendData;
    }

    public String getIp() {
        if (TextUtils.isEmpty(ip)){
            return Constant.HOST;
        }
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        if (port == 0){
            return Constant.PORT;
        }
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getRecType() {
        return recType;
    }

    public void setRecType(int recType) {
        this.recType = recType;
    }

    public String getRecData() {
        return recData;
    }

    public void setRecData(String recData) {
        this.recData = recData;
    }
}
