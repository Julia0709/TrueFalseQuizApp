package com.test.truefalsequizapp;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends Activity {

    private TextView mtextApiVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        // check and show sdk version
        if(Build.VERSION.SDK_INT >= 21){
            mtextApiVer = (TextView) findViewById(R.id.textApiVer);
            mtextApiVer.setText("api version is " + Build.VERSION.SDK_INT);
        }else{
            mtextApiVer.setText("api version is under 21.");
        }

        // backBtn: go back to MainActivity
        Button returnButton = (Button) findViewById(R.id.backBtn);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}