package com.example.androidtutproject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private Button makeItRain;
    private TextView moneyValue;
    private Button showInfo;
    private int moneyCounter = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeItRain = findViewById(R.id.buttonMakeItRain);
        moneyValue = findViewById(R.id.moneyValue);
        showInfo = findViewById(R.id.buttonShowInfo);


    }

    public void showMoney(View view) {
        moneyCounter += 1;

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        //Log.d("MIR", "onClick" + moneyCounter);
        moneyValue.setText(numberFormat.format(moneyCounter));

//        if (moneyCounter == 20000){
//            moneyValue.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.purple_200));
//
//        }
        switch (moneyCounter){
            case 2:
                moneyValue.setTextColor(Color.MAGENTA);
                break;
            case 3:
                moneyValue.setTextColor(Color.BLUE);
                break;
            case 4:
                moneyValue.setTextColor(Color.RED);
                break;
            default:
                moneyValue.setTextColor(Color.WHITE);

        }


    }

    public void showInfo(View view) {
        Toast.makeText(MainActivity.this, R.string.app_info, Toast.LENGTH_SHORT)
                .show();

        Snackbar.make(moneyValue, R.string.app_info, Snackbar.LENGTH_SHORT)
                .setAction("More", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("MIR", "showInfo is clicked");
                    }
                })
                .show();

    }
}