package com.example.activitylifecycletestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ShowGuess extends AppCompatActivity {

    TextView textViewShowGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);

        textViewShowGuess = findViewById(R.id.textViewShowGuess);

        Bundle extra = getIntent().getExtras();

        if (extra != null){
            // obtain intent and do something with it
            String guess = extra.getString("guess");
            textViewShowGuess.setText(guess);
            Log.d("somevalue", String.valueOf(extra.getInt("somevalue")));
        }

        textViewShowGuess.setOnClickListener(
                v -> {
                    Intent intent = getIntent();
                    intent.putExtra("throwBackKey", "hello there!!");
                    setResult(RESULT_OK, intent);
                    finish();
                }
        );




    }
}