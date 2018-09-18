package com.tianyisc.sockettest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tianyisc.sockettest.R;
import com.tianyisc.sockettest.fragment.FragmentTestActivity;

import java.io.IOException;

public class Main3Activity extends BaseActivity {
    private TextView text,text2;
    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        text = (TextView) findViewById(R.id.text);
        text2 = (TextView) findViewById(R.id.next_text);

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    type = 1;
                    application.sendInfo("send message 3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.next_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    type = 2;
//                    application.sendInfo("send message 32");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                Intent intent = new Intent(Main3Activity.this,FragmentTestActivity.class);
                startActivity(intent);
                finish();


            }
        });
    }

    @Override
    protected void getRecInfo(String recData) {
        if (type == 1){
            text.setText("3333333  "+recData);
        }else if (type == 2){
            text2.setText("333333322   "+recData);
        }else {
            text.setText("3333333  "+recData);
        }
    }
}
