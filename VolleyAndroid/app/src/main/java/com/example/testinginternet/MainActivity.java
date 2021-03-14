package com.example.testinginternet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    String url ="https://www.google.com";
    String apiUrl = "https://jsonplaceholder.typicode.com/todos";
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate request queue
//        RequestQueue queue = Volley.newRequestQueue(this);
        // one instance here! If instance is null, then create instance of queue
        queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();


        // request a string response from URL
        StringRequest stringRequest = getStringRequest();

        JsonArrayRequest jsonArrayRequest = getJsonArrayRequest();

        // add request to the queue, queue up the request that need to happen, there might be more than 1
        queue.add(stringRequest);

        // add jsonArrayRequest to the queue
        queue.add(jsonArrayRequest);
    }

    private JsonArrayRequest getJsonArrayRequest() {
        return new JsonArrayRequest(Request.Method.GET, apiUrl, null,

                    response -> {

                        // this will print out the whole JSONArray from the URL given
    //                    Log.d("JSON", response.toString());


                        // what to do after the JSON response
                        for (int i = 0 ;i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Log.d("JSON Field", jsonObject.getString("title"));
                            }
                            // not sure if it works, Android suggested surrounding it with try catch
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                },
                    error -> {
                        // what to do when there is error
                        Log.d("JSON", "onCreate: FAILED!");

                    }
            );
    }

    private StringRequest getStringRequest() {
        return new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // what do you want to do with the response that you get back
                            // display first 500 characters of substring

                            Log.d("RESPONSE", response.substring(0, 500));

                        }
                    },
                    // what happens when there's errors retrieving data?
                    // android handles it for you
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_LONG)
                                    .show();
                            Log.d("NOPE", "THAT DIDNT WORK!");

                        }
                    });
    }
}