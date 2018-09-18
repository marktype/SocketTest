package com.tianyisc.sockettest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.tianyisc.sockettest.OnRecListener;
import com.tianyisc.sockettest.RecHandler;
import com.tianyisc.sockettest.SocketApplication;
import com.tianyisc.sockettest.SocketInstance;
import com.tianyisc.sockettest.SocketThread;

public abstract class BaseActivity extends Activity {
    protected SocketThread socketThread;
    protected SocketApplication application;
    protected RecHandler recHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (SocketApplication) getApplication();
        initListener();
        socketThread = SocketInstance.getInstance().getSocketInstance(application,recHandler);
    }

    protected abstract void getRecInfo(String recData);


    public void initListener(){
        recHandler = new RecHandler(this,"重新发送消息", new OnRecListener() {
            @Override
            public void onRecData(String recData) {
                getRecInfo(recData);
            }
        });
        recHandler.setSocketApplication(application);
    }

    @Override
    protected void onResume() {
        super.onResume();
        socketThread.setContext(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onDestroy", "str----" +socketThread+"  application getSocket "+application.getSocket());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}
