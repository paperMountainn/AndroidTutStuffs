package com.example.bioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bioapp.data.Bio;
import com.example.bioapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final Bio bio = new Bio();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // bind the object, at compile time
        binding.setBio(bio);

        // set value for bio class, at compile time
        bio.setName("Zoe");




        Button buttonDone = binding.buttonDone;
        buttonDone.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addHobbies(view);

                    }
                }
        );


//        enterHobbies = findViewById(R.id.editTextEnterHobbies);
//        hobbies = findViewById(R.id.textViewHobbiesText);
    }

    public void addHobbies(View view) {
//        String hobbyText = enterHobbies.getText().toString().trim();
//        hobbies.setText(String.format("Hobbies: %s", hobbyText));
//        hobbies.setVisibility(View.VISIBLE);

        // set reference to binded editText
        EditText editTextenterHobbies = binding.editTextEnterHobbies;

        // obtain String from the editText textfield
        String hobbyTextDisplay = editTextenterHobbies.getText().toString().trim();

        // set attribute hobbies of the bio object as the String obtained
        bio.setHobbies(hobbyTextDisplay);

        //set reference to binded textView
        TextView textViewHobbies = binding.textViewHobbiesText;
        // set String to the textView to display text
        // when you perform data-binding, these are no longer required
        // because when you set the attribute for hobbies, xml knows where to obtain this value and display it
        // display text in textView
//        textViewHobbies.setText(hobbyTextDisplay);

        binding.invalidateAll();
        textViewHobbies.setVisibility(View.VISIBLE);


        // hide keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // in case inputMethodManager is null
        assert inputMethodManager != null;
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}