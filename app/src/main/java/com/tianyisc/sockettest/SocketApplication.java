package com.tianyisc.sockettest;

import android.app.Application;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


/**
 * socket只连接一次方式
 */
public class SocketApplication extends Application {
    private Socket socket;
    private DataOutputStream out = null;
    private DataInputStream inputStream = null;
    private byte[] buf;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void init() throws IOException {
        socket = new Socket();
        SocketAddress socAddress = new InetSocketAddress("192.168.199.229", 30006);
        Log.d("init", "init: socket " + socket);
        socket.connect(socAddress, 5000);
//        socket.setSoTimeout(70*1000);  //长连接不加此属性
        this.out = new DataOutputStream(socket.getOutputStream());
        this.inputStream = new DataInputStream(socket.getInputStream());
        Log.d("init", "连接成功了");
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public DataInputStream getInputStream(){
        return inputStream;
    }

    public void sendInfo(String info) throws IOException {
        if (out != null){
            out.write(info.getBytes());
            out.flush();
        }else {
            Log.e("init","服务器连接失败");
        }

    }

}
