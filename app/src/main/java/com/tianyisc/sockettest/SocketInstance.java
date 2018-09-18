package com.tianyisc.sockettest;

/**
 * socket代理类，保证单一性
 */
public class SocketInstance {

    private static volatile SocketInstance singleton;
    private static volatile SocketThread socketThread;

    private SocketInstance() {
    }

    public static SocketInstance getInstance() {
        if (singleton == null) {
            synchronized (SocketInstance.class) {
                if (singleton == null) {
                    singleton = new SocketInstance();

                }
            }
        }
        return singleton;
    }

    public SocketThread getSocketInstance(SocketApplication application,RecHandler recHandler){
        if (socketThread == null){
            socketThread = new SocketThread(application);
            socketThread.setOnHandler(recHandler);
            socketThread.start();
            return socketThread;
        }else {
            socketThread.setOnHandler(recHandler);
            return socketThread;
        }
    }
}
