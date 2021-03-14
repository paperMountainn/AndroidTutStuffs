package com.example.triviaapp.model;

import androidx.annotation.NonNull;

/**
 * Question class models a single question
 * Later on, we can have a List of them
 */
public class Question {
    private String answer;
    private boolean answerTrue;

    // constructors
    public Question() {

    }

    public Question(String answer, boolean answerTrue) {
        this.answer = answer;
        this.answerTrue = answerTrue;
    }

    // getters, setters
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }


    // override toString here so that we print out one single Question
    @Override
    public String toString() {
        return "Question{" +
                "answer='" + answer + '\'' +
                ", answerTrue=" + answerTrue +
                '}';
    }
}
