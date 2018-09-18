package com.tianyisc.sockettest;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

/**
 * Created by fj on 2018/2/23.
 * 此种方式适合0或1个按钮实例一次Handler（3个参数）,多次实例,多次发送不同请求使用（2个参数）
 */

public class RecHandler extends Handler {
    private Activity context;
    private SocketThread socketThread;
    private String sendInfo;
    private OnRecListener onRecListener;
    private SocketApplication application;


    public RecHandler(Activity context, String sendInfo, OnRecListener onRecListener){
        this.context = context;
        this.sendInfo = sendInfo;
        this.onRecListener = onRecListener;
    }

    public void setSocketApplication(SocketApplication application){
        this.application = application;
    }

    public void setSendInfo(String sendInfo){
        this.sendInfo = sendInfo;
    }



    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        RecData data = (RecData) msg.obj;
        switch (data.getRecType()) {
            case Constant.GETSUCCESS:
                LogsUtil.e(context,"获取成功  "+data.getRecData());
                onRecListener.onRecData(data.getRecData());
                break;
            case Constant.CONNECT_OVERTIME:
                LogsUtil.e(context,"连接超时");
                reConnect();
                break;
            case Constant.CONNECT_SUCCESS:
                LogsUtil.e(context,"连接服务器断开");
                delayTime();
                break;
            case Constant.CONNECT_FAILED:
                LogsUtil.e(context,"连接失败");
                delayTime();
                break;
            default:
                LogsUtil.e(context,"io异常");
                delayTime();
                break;
        }
    }

    private void reConnect(){
        if (socketThread != null){
            socketThread.interrupt();
            socketThread =null;
        }
        application.setSocket(null);
        socketThread = new SocketThread(application);
        socketThread.setOnHandler(this);
        socketThread.start();
    }

    private void delayTime(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reConnect();
            }
        }).start();
    }


}
