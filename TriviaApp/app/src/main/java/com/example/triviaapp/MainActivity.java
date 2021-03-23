package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.triviaapp.controller.AppController;
import com.example.triviaapp.data.AnswerListAsyncResponse;
import com.example.triviaapp.data.Repository;
import com.example.triviaapp.databinding.ActivityMainBinding;
import com.example.triviaapp.model.Question;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // these are instance variables that you can use everywhere in the class!
    private ActivityMainBinding binding;
    private int currentQuestionIndex;
    List<Question> questionList;

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
        List<Question> questionList = new Repository().getQuestions(new AnswerListAsyncResponse() {

            // ProcessFinished is overrode, this belongs to the interface AnswerListAsyncResponse
            // overriding the interface is compulsory
            @Override
            public void ProcessFinished(ArrayList<Question> questionArrayList) {

                // print out all the answers to check if we are getting the questions from the url
                Log.d("hellooo", String.valueOf(questionArrayList));

                // now we can use binding to retrieve that particular widget, and use their methods
                String questionText = questionArrayList.get(currentQuestionIndex).getQuestion();
                binding.textViewQuestion.setText(questionText);


            }
        }
        );

        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestionIndex = (currentQuestionIndex + 1);


            }
        });

        binding.buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

