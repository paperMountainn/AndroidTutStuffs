package com.example.triviaapp.controller;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * AppController contains all methods and propertiest that governs the entire app
 * you need to add it to AndroidManifest.xml so that it can be accessed app wide
 */
public class AppController extends Application {
    private static AppController instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private AppController(Context context) {
        ctx = context;

        // get queue so that we can add to it
        requestQueue = getRequestQueue();

    }

    /**
     *
     * @return Singleton
     */
    public static synchronized AppController getInstance() {

        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }


    // can be any type of req, <T> is a generics type
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    // you can do this because you inherit Application
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
