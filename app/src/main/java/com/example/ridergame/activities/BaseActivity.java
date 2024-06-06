package com.example.ridergame.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ridergame.model.User;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initializeViews();
//        setListeners();
    }

    protected abstract void initializeViews();
    protected abstract void setListeners();
    public static User currentUser;
}