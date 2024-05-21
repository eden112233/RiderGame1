package com.example.ridergame.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ridergame.R;

public class AddScoreActivity extends AppCompatActivity {

    private int score;
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_score);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the score from the intent
        score = getIntent().getIntExtra("score", 0);

        // Set the score in the TextView
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + score);

        nameEditText = findViewById(R.id.nameEditText);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveScore();
            }
        });

        Button seeAllScoresButton = findViewById(R.id.seeAllScoresButton);
        seeAllScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the action to see all scores
                Toast.makeText(AddScoreActivity.this, "See All Scores button clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveScore() {
        String name = nameEditText.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }
        // Save the score and name here (e.g., to a database or shared preferences)
        Toast.makeText(this, "Score saved", Toast.LENGTH_SHORT).show();
    }
}
