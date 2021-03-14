package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.triviaapp.controller.AppController;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // do something upon receiving the jsonArray
                Log.d("TAG", response.toString());

            }
        },
                // if error, do this
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}