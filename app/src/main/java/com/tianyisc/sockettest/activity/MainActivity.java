package com.tianyisc.sockettest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tianyisc.sockettest.R;

import java.io.IOException;

public class MainActivity extends BaseActivity {
    private TextView text;
//    private RecHandler recHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
//        socketThread.setOnHandler(recHandler);
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    application.sendInfo("send message 1");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    protected void getRecInfo(String recData) {
        text.setText(recData);
    }
}
