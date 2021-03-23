package com.example.triviaapp.model;

import androidx.annotation.NonNull;

/**
 * Question class models a single question
 * Later on, we can have a List of them
 */
public class Question {
    private String question;
    private boolean answerTrue;

    // constructors
    public Question() {

    }

    public Question(String question, boolean answerTrue) {
        this.question = question;
        this.answerTrue = answerTrue;
    }

    // getters, setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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
                "question='" + question + '\'' +
                ", answerTrue=" + answerTrue +
                '}';
    }
}
