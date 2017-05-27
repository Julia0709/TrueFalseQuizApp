package com.test.truefalsequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

    private ImageButton mPrevButton;
    private ImageButton mNextButton;

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
        mQuestionNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if it's not the last question
                if (mCurrentIndex != (mQuestionArray.length - 1)) {
                    // text pressed
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionArray.length;
                    updateQuestion();
                }
            }
        });

        mQuestionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if it's not the last question
                if (mCurrentIndex != (mQuestionArray.length - 1)) {
                    // text pressed
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionArray.length;
                    updateQuestion();
                }
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

        // prevBtn
        mPrevButton = (ImageButton) findViewById(R.id.prevBtn);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if it's not the 1st question
                if (mCurrentIndex != 0) {
                    // prev button pressed
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionArray.length;
                    updateQuestion();
                }
            }
        });

        // nextBtn
        mNextButton = (ImageButton) findViewById(R.id.nextBtn);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if it's not the last question
                if (mCurrentIndex != (mQuestionArray.length - 1)) {
                    // next button pressed
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionArray.length;
                    updateQuestion();
                    // enable true/false/hint button
                    updateButtons(false);
                }
            }
        });

    }

    // update question text
    private void updateQuestion() {
        // question number
        mQuestionNumber = (TextView) findViewById(R.id.questionNumber);
        mQuestionNumber.setText("Question" + (mCurrentIndex + 1));

        int question = mQuestionArray[mCurrentIndex].getQuestionText();
        mQuestionText.setText(question);
    }

    // control button
    private void updateButtons(boolean answered) {
        mFalseButton.setEnabled(!answered);
        mTrueButton.setEnabled(!answered);
        mHintBtn.setEnabled(!answered);
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

    // Activity Loh
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
