package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.triviaapp.controller.AppController;
import com.example.triviaapp.data.AnswerListAsyncResponse;
import com.example.triviaapp.data.Repository;
import com.example.triviaapp.databinding.ActivityMainBinding;
import com.example.triviaapp.model.Question;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // these are instance variables that you can use everywhere in the class!
    private ActivityMainBinding binding;
    private int currentQuestionIndex;
    List<Question> questionList;
    private static final String SCORE_ID = "score_prefs";
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate binding object
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);



        // added async interface to obtain stuffs from the url asynchronously
//        List<Question> questionList = new Repository().getQuestions(new AnswerListAsyncResponse() {
//
//            // ProcessFinished is overrode, this belongs to the interface AnswerListAsyncResponse
//            // overriding the interface is compulsory
//            @Override
//            public void ProcessFinished(ArrayList<Question> questionArrayList) {
//                Log.d("hellooo", String.valueOf(questionArrayList));
//
//            }
//        });

        questionList = new Repository().getQuestions(new AnswerListAsyncResponse() {

            // ProcessFinished is overrode, this belongs to the interface AnswerListAsyncResponse
            // overriding the interface is compulsory
            // after getting questions: do this
            @Override
            public void ProcessFinished(ArrayList<Question> questionArrayList) {

                // print out all the answers to check if we are getting the questions from the url
                Log.d("hellooo", String.valueOf(questionArrayList));

                // now we can use binding to retrieve that particular widget, and use their methods
                String questionText = questionArrayList.get(currentQuestionIndex).getQuestion();
                binding.textViewQuestion.setText(questionText);

                // set the 0/913 of the question
                updateCounter(questionArrayList);




            }
        }
        );

        // buttonNext when clicked, updates currentQuestionIndex
        // then update the Question
        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // to avoid overflow, have this line of code
                currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
                updateQuestion();


            }
        });

        binding.buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                updateQuestion();

            }
        });

        binding.buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateQuestion();


            }
        });


    }



    private void checkAnswer(boolean userChoice) {
        String snackMessage;
        boolean correctAnswer = questionList.get(currentQuestionIndex).isAnswerTrue();
        if (userChoice == correctAnswer){
            snackMessage = "You are correct!";
            score = score + 10;
            fadeAnimation();
        }
        else {
            snackMessage = "You are wrong!";
            shakeAnimation();
        }
        Snackbar.make(binding.cardViewQuestion, snackMessage, Snackbar.LENGTH_LONG).show();

    }

    private void updateCounter(ArrayList<Question> questionArrayList) {
        binding.textViewQuestionNum.setText(String.format(getString(R.string.question_out_of_formatted), currentQuestionIndex, questionArrayList.size()));
    }




    // method to update question
    private void updateQuestion(){
        String question = questionList.get(currentQuestionIndex).getQuestion();
        binding.textViewQuestion.setText(question);
        // update question, also update the counter based on currentQuestionIndex
        updateCounter((ArrayList<Question>) questionList);
    }

    private void shakeAnimation(){
        // get reference to animation res
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake_animation);

        // attach to cardView
        binding.cardViewQuestion.setAnimation(shake);

        // upon shaking, also make the textview background red to indicate wrong answer
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.textViewQuestion.setTextColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.textViewQuestion.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void fadeAnimation(){
        // setting up
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);

        // set duration etc
        alphaAnimation.setDuration(300);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        binding.cardViewQuestion.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.textViewQuestion.setTextColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.textViewQuestion.setTextColor(Color.WHITE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}

    // you are obligated to override onClick if you implement onClickListener
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            // this would be deprecated once gradle5 is around
//            case R.id.buttonNext:
//
//                break;
//            case R.id.buttonTrue:
//                break;
//            case R.id.buttonFalse:
//                break;
//        }
//
//    }
//}

