package com.test.truefalsequizapp;

public class Question {

    private int mTextQuestion;
    private boolean mAnswerTrue;

    // method for question objects
    public Question(int textQuestion, boolean answerTrue) {
        mTextQuestion = textQuestion;
        mAnswerTrue = answerTrue;
    }

    // Getter & Setter
    public int getTextQuestion() {
        return mTextQuestion;
    }

    public void setTextQuestion(int textQuestion) {
        mTextQuestion = textQuestion;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
