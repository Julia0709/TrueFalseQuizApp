package com.test.truefalsequizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mQuestionNumber;
    private TextView mQuestionText;

    private Button mTrueButton;
    private Button mFalseButton;

    private Button mHintBtn;

    private ImageButton mNextBtn;

    // question object's array
    private Question[] mQuestionArray = new Question[]{
            new Question(R.string.USQuestion, true),
            new Question(R.string.CAQuestion, false),
            new Question(R.string.JPQuestion, true),
            new Question(R.string.KRQuestion, true),
            new Question(R.string.MXQuestion, true),
            new Question(R.string.UKQuestion, true)
    };

    private int mCurrentIndex = 0;

    private int totalPoint = 0;

    // variables for animation
    private RotateAnimation rotate;

    // activity lifecycle
    private static final String DEBUG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // questionText: connect Question TextView and model
        mQuestionText = (TextView) findViewById(R.id.questionText);
        updateQuestion();

        // clickable questionNumber and questionText
        mQuestionNumber.setEnabled(false);
        mQuestionNumber.setTextColor(Color.DKGRAY);
        mQuestionNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextQuesion();
            }
        });

        mQuestionText.setEnabled(false);
        mQuestionText.setTextColor(Color.DKGRAY);
        mQuestionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextQuesion();
            }
        });

        // trueBtn
        mTrueButton = (Button) findViewById(R.id.trueBtn);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                updateButtons(true);
            }
        });

        // falseBtn
        mFalseButton = (Button) findViewById(R.id.falseBtn);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateButtons(true);
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

        // nextBtn
        mNextBtn = (ImageButton) findViewById(R.id.nextBtn);
        mNextBtn.setEnabled(false);
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextQuesion();
            }
        });

    }

    // click to go next quesion
    private void goToNextQuesion() {
        // if it's not the last question
        if (mCurrentIndex != (mQuestionArray.length - 1)) {
            // text pressed
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionArray.length;
            updateQuestion();
            // enable true/false/hint button
            updateButtons(false);
        }
    }

    // update question text
    private void updateQuestion() {
        // question number
        mQuestionNumber = (TextView) findViewById(R.id.questionNumber);
        mQuestionNumber.setText("Question " + (mCurrentIndex + 1) + " / " + mQuestionArray.length);

        int question = mQuestionArray[mCurrentIndex].getQuestionText();
        mQuestionText.setText(question);
    }

    // control button
    private void updateButtons(boolean answered) {
        mFalseButton.setEnabled(!answered);
        mTrueButton.setEnabled(!answered);
        mHintBtn.setEnabled(!answered);

        // enable and animate nextBtn when not the last question
        if(mCurrentIndex < mQuestionArray.length) {
            startRotation();
            mNextBtn.setEnabled(answered);
            mQuestionNumber.setEnabled(answered);
            mQuestionText.setEnabled(answered);
        }
    }

    // toast: correct or incorrect
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionArray[mCurrentIndex].isAnswerTrue();
        int judgeAnswer = 0;
        if (answerIsTrue == userPressedTrue) {
            judgeAnswer = R.string.correctToast;
            totalPoint++;
        } else {
            judgeAnswer = R.string.incorrectToast;
        }

        Toast.makeText(MainActivity.this, judgeAnswer, Toast.LENGTH_SHORT).show();

        if (mCurrentIndex == (mQuestionArray.length-1)) {
            double percentage = ((double) totalPoint / mQuestionArray.length) * 100;
            Toast.makeText(MainActivity.this, "The score is " + (int)percentage + "%", Toast.LENGTH_SHORT).show();
            totalPoint = 0;
        }

    }

    private void startRotation() {
        // RotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
        rotate = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        // animation time
        rotate.setDuration(400);
        // animation repetition
        rotate.setRepeatCount(0);
        // continue showing after animation
        rotate.setFillAfter(true);

        // elements to animate
        mNextBtn.startAnimation(rotate);
    }

    // Activity Log
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(DEBUG, "onStart() called.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(DEBUG,"onRestart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(DEBUG, "onResume() called.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(DEBUG, "onPause() called.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(DEBUG, "onStop() called.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(DEBUG, "onDestroy called.");
    }

}
