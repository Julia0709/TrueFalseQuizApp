package com.test.truefalsequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;

    private Button mHintBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // trueBtn
        mTrueButton = (Button) findViewById(R.id.trueBtn);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        // falseBtn
        mFalseButton = (Button) findViewById(R.id.falseBtn);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        // hintBtn: go to HintActivity
        mHintBtn = (Button) findViewById(R.id.hintBtn);
        mHintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHintActivity = new Intent(getApplication(), HintActivity.class);
                startActivity(goToHintActivity);
            }
        });
    }

    // toast: correct or incorrect
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = true;
        int messageResId = 0;
        if (answerIsTrue == userPressedTrue) {
            messageResId = R.string.correctToast;
        } else {
            messageResId = R.string.incorrectToast;
        }

        Toast.makeText(MainActivity.this, messageResId, Toast.LENGTH_SHORT).show();
    }

}
