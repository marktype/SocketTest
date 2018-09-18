package com.tianyisc.sockettest.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianyisc.sockettest.OnRecListener;
import com.tianyisc.sockettest.R;
import com.tianyisc.sockettest.RecHandler;
import com.tianyisc.sockettest.SocketApplication;
import com.tianyisc.sockettest.SocketInstance;
import com.tianyisc.sockettest.SocketThread;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    protected SocketThread socketThread;
    protected SocketApplication application;
    protected RecHandler recHandler;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (SocketApplication) getActivity().getApplication();
        initListener();
        socketThread = SocketInstance.getInstance().getSocketInstance(application,recHandler);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("fragment","11111111111");
        return inflater.inflate(R.layout.fragment_base, container, false);
    }


    protected abstract void getRecInfo(String recData);


    public void initListener(){
        recHandler = new RecHandler(getActivity(),"重新发送消息", new OnRecListener() {
            @Override
            public void onRecData(String recData) {
                getRecInfo(recData);
            }
        });
        recHandler.setSocketApplication(application);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            socketThread = SocketInstance.getInstance().getSocketInstance(application,recHandler);
        }
        Log.e("fragment","hide  "+getActivity()+"   "+hidden);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("fragment","222222222222");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("onDestroy", "str----" +socketThread+"  application getSocket "+application.getSocket());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }

}
