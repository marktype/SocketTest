package com.tianyisc.sockettest;

import android.text.TextUtils;
import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 接收数据代理类
 */
public class SocketReciver {
    private static volatile SocketReciver singleton;

    private SocketReciver() {
    }

    public static SocketReciver getInstance() {
        if (singleton == null) {
            synchronized (SocketReciver.class) {
                if (singleton == null) {
                    singleton = new SocketReciver();
                }
            }
        }
        return singleton;
    }

    private DataInputStream inputStream = null;
    private byte[] buf;
    public RecData sendMessage(RecData recData,SocketApplication application) {
        try {

            inputStream = application.getInputStream();

            StringBuilder stringBuilder = new StringBuilder();
            if (buf == null) {
                buf = new byte[4 * 1024];
            }
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                String receData = new String(buf, 0, len, Charset.forName("UTF-8"));
                Log.e("SocketSendWhileRecevier", "str-接受---" + receData);
                Log.e("SocketSendWhileRecevier", "str-接受---" + Thread.currentThread());
                stringBuilder.append(receData);
                if (receData.contains("exit")) {
                    recData.setRecData(stringBuilder.toString());
                    recData.setRecType(Constant.GETSUCCESS);
                    break;
                }
            }
            if (TextUtils.isEmpty(stringBuilder.toString())){
                recData.setRecData(stringBuilder.toString());
                recData.setRecType(Constant.CONNECT_SUCCESS);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recData;
    }
}
