package com.tianyisc.sockettest;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by fj on 2018/2/22.
 */

public class SocketThread extends Thread {
    private Handler handler;
    private SocketApplication application;
    private Activity context;

    public SocketThread(SocketApplication application) {
        this.application = application;
    }

    public void setOnHandler(Handler handler) {
        this.handler = handler;
    }

    public void setContext(Activity context){
        this.context = context;
    }

    @Override
    public void run() {
        super.run();
        RecData recData = new RecData();
        Socket socket = application.getSocket();
        if (socket == null) {
            try {
                Thread.sleep(1000);
                application.init();
                recData.setRecType(Constant.CONNECT_SUCCESS);
            } catch (SocketTimeoutException e) {
                Log.d("init", "init: SocketTimeoutException ");
                if (TextUtils.isEmpty(e.getMessage())) {
                    recData.setRecType(Constant.CONNECT_OVERTIME);
                } else {
                    recData.setRecType(Constant.CONNECT_FAILED);
                }
                e.printStackTrace();
            } catch (ConnectException e) {
                Log.d("init", "init: ConnectException ");
                recData.setRecType(Constant.CONNECT_FAILED);
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("init", "init: IOException ");
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            socket = application.getSocket();
        }
        Log.d("init", "init: context "+context);
        while (socket != null &&!socket.isClosed() &&socket.isConnected()){
            SocketReciver.getInstance().sendMessage(recData,application);
            if (!TextUtils.isEmpty(recData.getRecData())){
                Log.d("init", "init: currentThread "+Thread.currentThread());
                Message msg = Message.obtain();
                msg.obj = recData;
                handler.sendMessage(msg);
            }else {
                break;
            }
        }
        Log.e("init", "finally: recData " + recData);
        Message msg = Message.obtain();
        msg.obj = recData;
        handler.sendMessage(msg);
    }


}
