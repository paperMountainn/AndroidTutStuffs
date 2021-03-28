package com.example.androidtutproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button showButton;
    private TextView greetings;
    private EditText nameEntered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showButton = findViewById(R.id.button);
        greetings = findViewById(R.id.textView);
        nameEntered = findViewById(R.id.editTextTextPersonName);

        // run button when clicked
        showButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = nameEntered.getText().toString();

                        if (name.isEmpty()){
                            greetings.setText("Please Enter your name!");
                        }
                        greetings.setText("Hello there, " + name);

                    }
                }
        );


    }
}