package com.example.activitylifecycletestapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonGuess;
    EditText editTextEnterGuess;
    public static final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(MainActivity.this,"onCreate!!", Toast.LENGTH_SHORT).show();

        buttonGuess = findViewById(R.id.buttonGuess);
        editTextEnterGuess = findViewById(R.id.editTextGuessField);


        buttonGuess.setOnClickListener(
                v -> {
                    String guess = editTextEnterGuess.getText().toString().trim();
                    if (guess.isEmpty()){
                      Toast.makeText(MainActivity.this, "Please Enter Text!", Toast.LENGTH_LONG)
                              .show();
                    }
                    else{
                        Intent intent = new Intent(MainActivity.this, ShowGuess.class);
                        intent.putExtra("guess", guess);
                        intent.putExtra("somevalue", 12);
                        startActivityForResult(intent, REQUEST_CODE);


                    }

                }
        );



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try{
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
                assert data != null;
                String requiredValue = data.getStringExtra("throwBackKey");
                Toast.makeText(MainActivity.this, requiredValue, Toast.LENGTH_LONG)
                        .show();
            }
        }
        catch (Exception e){
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG)
                    .show();

        }
    }
}