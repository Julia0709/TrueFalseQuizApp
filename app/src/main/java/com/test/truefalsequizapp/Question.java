package com.test.truefalsequizapp;

public class Question {

    private int mQuestionText;
    private boolean mAnswerTrue;

    // method for question objects
    public Question(int questionText, boolean answerTrue) {
        mQuestionText = questionText;
        mAnswerTrue = answerTrue;
    }

    // Getter & Setter
    public int getQuestionText() {
        return mQuestionText;
    }

    public void setmQuestionText(int questionText) {
        mQuestionText = questionText;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
