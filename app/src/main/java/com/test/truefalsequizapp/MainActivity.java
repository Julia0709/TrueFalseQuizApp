package com.test.truefalsequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextQuestion;

    private Button mTrueButton;
    private Button mFalseButton;

    private Button mHintBtn;

    // question object's array
    private Question[] mQuestionArray = new Question[]{
            new Question(R.string.USQuestion, true),
            new Question(R.string.UKQuestion, false),
            new Question(R.string.UKQuestion, true),
            new Question(R.string.UKQuestion, true),
            new Question(R.string.UKQuestion, true),
            new Question(R.string.UKQuestion, true)
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // textQuestion: connect Question TextView and model
        mTextQuestion = (TextView) findViewById(R.id.textQuestion);
        mTextQuestion.setText(R.string.USQuestion);

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
        boolean answerIsTrue = mQuestionArray[mCurrentIndex].isAnswerTrue();
        int judgeAnswer = 0;
        if (answerIsTrue == userPressedTrue) {
            judgeAnswer = R.string.correctToast;
        } else {
            judgeAnswer = R.string.incorrectToast;
        }

        Toast.makeText(MainActivity.this, judgeAnswer, Toast.LENGTH_SHORT).show();
    }

}
