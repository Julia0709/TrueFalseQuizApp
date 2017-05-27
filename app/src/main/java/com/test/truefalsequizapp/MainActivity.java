package com.test.truefalsequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
