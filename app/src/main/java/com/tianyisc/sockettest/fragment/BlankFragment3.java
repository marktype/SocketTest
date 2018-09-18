package com.tianyisc.sockettest.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tianyisc.sockettest.R;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment3 extends BaseFragment {
    private TextView text;
    private View mView;

    public BlankFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_blank, container, false);

        text = (TextView) mView.findViewById(R.id.text);

        mView.findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    application.sendInfo("send message 2");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return mView;
    }

    @Override
    protected void getRecInfo(String recData) {
        text.setText("33333333   "+recData);
    }

}
