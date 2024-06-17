package com.example.ridergame.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.os.Bundle;
import android.widget.SeekBar;

import com.example.ridergame.R;
import android.widget.SeekBar;

public class SettingActivity extends AppCompatActivity {
    SeekBar seekBar;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume= audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        seekBar = findViewById(R.id.volume);
        seekBar.setMax(maxVolume);
        seekBar.setProgress(currentVolume);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }




}