package com.tianyisc.sockettest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tianyisc.sockettest.R;

import java.io.IOException;

public class Main2Activity extends BaseActivity {
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        text = (TextView) findViewById(R.id.text);

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    application.sendInfo("send message 2");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void getRecInfo(String recData) {
        text.setText("2222222"+recData);
    }

}
