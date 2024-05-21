package com.example.ridergame.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ridergame.R;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, View.OnClickListener{

    ImageButton btnPlay;
    Button btnInst;
    Button btnSet;
    Button btnShop;
    private static final String TAG="swipe position";
    private float x1,x2,y1,y2;
    private static int MIN_DISTANCE =150;
    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);


        //initialize gesturedetector
        this.gestureDetector=new GestureDetector(MainActivity.this,this);

        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);
        btnInst = findViewById(R.id.btnInst);
        btnInst.setOnClickListener(this);
        btnSet = findViewById(R.id.btnSet);
        btnSet.setOnClickListener(this);
        btnShop = findViewById(R.id.btnShop);
        btnShop.setOnClickListener(this);
    }


    //override on touch event
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()){
            //starting to swipe time gesture
            case MotionEvent.ACTION_DOWN:
                x1=event.getX();
                y1=event.getY();
                break;


            //ending time swipe gesture
            case MotionEvent.ACTION_UP:
                x2=event.getX();
                y2=event.getY();

                //getting value for horizontal swipe
                float ValueX= x2-x1;
                //getting value for vertical swipe
                float ValueY= y2-y1;

                if(Math.abs(ValueX)>MIN_DISTANCE){
                    //detect left to right swipe
                    if (x2>x1)
                    {
                        Toast.makeText(this,"right is swiped",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"Right Swipe");
                    }

                    else {
                        //detect right to left swipe
                        Toast.makeText(this,"left is swiped - setting",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"Left Swipe");
                        Intent intent = new Intent(this, SettingActivity.class);
                        startActivity(intent);


                    }
                } else if (Math.abs(ValueY)> MIN_DISTANCE)
                {
                    //detect top to bottom swipe
                    if (y2>y1)
                    {
                        Toast.makeText(this,"bottom is swiped",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"Bottom Swipe");
                    }
                    else {
                        //detect bottom to top swipe
                        Toast.makeText(this,"top is swiped",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"Top Swipe");
                    }
                }


        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        if (view == btnPlay) {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        }
        if (view == btnSet) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        }
        if (view == btnShop) {
            Intent intent = new Intent(this, ShopActivity.class);
            startActivity(intent);
        }
        if (view == btnInst) {
            Intent intent = new Intent(this, InstructionsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //הפונקציה מראה את התפריט
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) //מקבלים הפנייה לאייטם
    {
        int id = item.getItemId();//מקבלים את הid של האייטם
        if (id == R.id.settings) {

        }
        return false;
    }




    @Override
    public boolean onDown(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
        return false;
    }




}























