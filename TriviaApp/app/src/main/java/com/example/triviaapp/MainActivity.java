package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

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
    private ActivityMainBinding binding;
    private int currentQuestionIndex;

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
        new Repository().getQuestions(new AnswerListAsyncResponse() {

            // ProcessFinished is overrode, this belongs to the interface AnswerListAsyncResponse
            // overriding the interface is compulsory
            @Override
            public void ProcessFinished(ArrayList<Question> questionArrayList) {
                Log.d("hellooo", String.valueOf(questionArrayList));

                // now we can use binding to retrieve that particular widget, and use their methods
                // casted to a CharSequence
                binding.textViewQuestion.setText((CharSequence) questionArrayList.get(currentQuestionIndex));

            }
        });






    }
}

//hello