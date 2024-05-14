package com.example.ridergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class LoadingActivity extends AppCompatActivity
{

    private CountDownTimer counter;
    TextView tv;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        tv = findViewById(R.id.tvLoading);


        new CountDownTimer(5000, 300)
        {

            public void onTick(long millisUntilFinished) {
                if(count %4 == 0)
                    tv.setText("loading.");


                if(count %4 == 1)
                    tv.setText("loading..");

                if(count %4 == 2)
                    tv.setText("loading...");

                count++;
            }



            public void onFinish()
            {
                Intent i = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(i);
            }
        }.start();

    }


}