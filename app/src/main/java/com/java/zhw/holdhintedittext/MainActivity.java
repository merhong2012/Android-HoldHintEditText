package com.java.zhw.holdhintedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HoldHintEditText editText = (HoldHintEditText) findViewById(R.id.editText);
        String hint = "想说点什么呢:";
        assert editText != null;
        editText.setHoldHintText(hint);
    }
}
