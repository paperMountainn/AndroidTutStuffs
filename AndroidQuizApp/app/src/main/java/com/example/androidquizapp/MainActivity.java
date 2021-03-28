package com.example.androidquizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidquizapp.databinding.ActivityMainBinding;
import com.example.androidquizapp.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int questionIndex;


    private final Question[] questionBank = new Question[]{
            new Question(R.string.Singapore_language, false),
            new Question(R.string.Singapore_anthem, true),
            new Question(R.string.Singapore_flower, true),
            new Question(R.string.Singapore_size, false)

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        // set reference to textView
        TextView textViewQuestion = binding.textViewQuestion;
        // set the first question as content for the textView
        textViewQuestion.setText(questionBank[0].getAnswerId());

        // set reference to next button
        Button buttonNext = binding.buttonNext;
        buttonNext.setOnClickListener(
                v -> {
//                    Log.d("BUTTONNEXT", String.valueOf(questionIndex++));
                    // increment question index, go to the next
//                    questionIndex ++;
                    // to solve the index out of bounds problem, we use this to increment instead
                    questionIndex = (questionIndex + 1) % questionBank.length;
                    // update question, obtain the objects in array questionBank accordingly
                    updateQuestion();
                }
            );

        // set reference to prev button
        Button buttonPrevious = binding.buttonPrevious;
        buttonPrevious.setOnClickListener(
                v -> {
                    if (questionIndex > 0){
                        // decrement
                        questionIndex = (questionIndex - 1) % questionBank.length;
                        // update
                        updateQuestion();
                    }

                }
        );

        // when click the button, check the answer
        Button buttonTrue = binding.buttonTrue;
        buttonTrue.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswer(true);
                    }
                }
        );
        Button buttonFalse = binding.buttonFalse;
        buttonFalse.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswer(false);
                    }
                }
        );
    }

    // external functions

    private void updateQuestion(){
        // set reference to textView
        TextView textViewQuestion = binding.textViewQuestion;
        // set the first question as content for the textView
        textViewQuestion.setText(questionBank[questionIndex].getAnswerId());

    }

    // check if answer is true
    private void checkAnswer(boolean userChoice){
        boolean correctAnswer = questionBank[questionIndex].isAnswerTrue();
        int displayMessage;

        if (userChoice == correctAnswer){
            displayMessage = R.string.correct_answer;
        }
        else{
            displayMessage = R.string.wrong_answer;
        }

        Snackbar.make(binding.imageView, displayMessage, Snackbar.LENGTH_SHORT).show();

    }
}