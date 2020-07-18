package com.example.quizzapp;

public class QuizModel {
    private  int mQuestion;
    private boolean mAnswer;

//    public QuizModel(int question, boolean answer){ //CONSTRUCTOR
//        mQuestion = question;
//        mAnswer = answer;
//    }


    public QuizModel(int question, boolean answer) { //auto generate
        mQuestion = question;
        mAnswer = answer;
    }

    public int getQuestion() { //AUTO GENERATE GETTER AND SETTER
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
