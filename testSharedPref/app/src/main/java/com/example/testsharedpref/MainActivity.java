package com.example.testsharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textViewShowMessage;
    private Button buttonSave;
    private EditText editTextEnterMessage;

    // we won't really change this
    private static final String MESSAGE_ID = "message_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSave = findViewById(R.id.buttonSave);
        textViewShowMessage = findViewById(R.id.textViewShowMessage);
        editTextEnterMessage = findViewById(R.id.editTextEnterMessage);


        // when button clicked, save the message user typed in shared Pref
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // save the stuffs user typed into editText
                String message = editTextEnterMessage.getText().toString().trim();

                // parse in globalID (usually in caps to denote)
                // sharedpref is essentially saved as an xml file, which needs an ID
                // MODE_PRIVATE: only this app can access
                SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);

                // invoke sharedpref editor, so that we can write stuffs into it
                // the editor points to where sharedPref is, and add data that we want
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // sharedPref is like a hashmap
                // put key-value pairs, put key
                editor.putString("message", message);

                // you need to save the changes to device / disk
                editor.apply();

            }
        });

        // get data back from sharedPref
        SharedPreferences getSharedData = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
        // get key-value
        String value = getSharedData.getString("message", "Nothing Yet");

        // show value and put onto textView
        textViewShowMessage.setText(value);



    }
}