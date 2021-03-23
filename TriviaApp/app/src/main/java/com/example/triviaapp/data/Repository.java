package com.example.triviaapp.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.triviaapp.controller.AppController;
import com.example.triviaapp.model.Question;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for Question retrieval, getting request from the internet too
 */
public class Repository {
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    private ArrayList<Question> questionArrayList = new ArrayList<>();

    public List<Question> getQuestions(final AnswerListAsyncResponse callBack){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        // print out response
//                        Log.d("RESPONSE", response.toString());

                        // loop through response array
                        for (int i = 0; i < response.length(); i++){
                            try {
                                String questionTitle = response.getJSONArray(i).get(0).toString();
                                boolean answer = response.getJSONArray(i).getBoolean(1);

                                // create new question object
                                Question question = new Question(questionTitle, answer);

                                // add question object to questionArrayList
                                questionArrayList.add(question);

                                // System.out.println(questionArrayList);

                            }

                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // using callBack here, check that ArrayList is filled before moving on, and retrieving in the mainActivity
                        if (null != callBack){
                            callBack.ProcessFinished(questionArrayList);
                        }
                    }
                },

                // add questions to the ArrayList of Questions that we have created
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("RESPONSE", "REQUEST FAILED");

                    }
                }
        );



        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return questionArrayList;
    }
}
