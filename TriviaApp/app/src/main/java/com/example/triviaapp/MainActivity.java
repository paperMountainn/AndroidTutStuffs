package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.triviaapp.controller.AppController;
import com.example.triviaapp.data.AnswerListAsyncResponse;
import com.example.triviaapp.data.Repository;
import com.example.triviaapp.model.Question;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // not really need to put this into a variable
        List<Question> questionList = new Repository().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void ProcessFinished(ArrayList<Question> questionArrayList) {
                Log.d("QUESTIONLIST", String.valueOf(questionArrayList));

            }
        });





    }
}

//hello