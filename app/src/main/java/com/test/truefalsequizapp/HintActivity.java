package com.test.truefalsequizapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends Activity {

    private TextView hShowAnswerText;
    private TextView hAnswerText;
    private boolean hAnswerTrue = true; // need to implement

    // buttons
    private Button hGoBackBtn;
    private Button hShowAnswerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        // showAnswerText: set text color
        hShowAnswerText = (TextView) findViewById(R.id.showAnswerText);
        hShowAnswerText.setTextColor(Color.DKGRAY);

        // answerText: display answer
        hAnswerText = (TextView) findViewById(R.id.answerText);

        // showAnswerBtn
        hShowAnswerBtn = (Button) findViewById(R.id.showAnswerBtn);
        hShowAnswerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAnswer();
            }
        });

        // goBackBtn: go back to MainActivity
        hGoBackBtn = (Button) findViewById(R.id.goBackBtn);
        hGoBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // update answer
    private void updateAnswer() {
        if (hAnswerTrue) {
            hAnswerText.setText(R.string.trueBtn);
        } else {
            hAnswerText.setText(R.string.falseBtn);
        }
    }

}