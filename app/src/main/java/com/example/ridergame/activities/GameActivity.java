package com.example.ridergame.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ridergame.views.BoardGame;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game);

        BoardGame boardgame = new BoardGame(this);
        setContentView(boardgame);
    }


}