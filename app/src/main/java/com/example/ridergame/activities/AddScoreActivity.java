package com.example.ridergame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.ridergame.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;

public class AddScoreActivity extends BaseActivity {

    private int score;
    private TextView scoreTextView;
    private View saveButton;
    private View seeAllScoresButton;
    private FirebaseFirestore db;

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
        // Initialize Firebase Database reference
        db = FirebaseFirestore.getInstance();

        initializeViews();
        setListeners();
    }

    @Override
    protected void initializeViews() {
        // Set the score in the TextView
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + score);
        saveButton = findViewById(R.id.saveButton);
        seeAllScoresButton = findViewById(R.id.seeAllScoresButton);
    }

    @Override
    protected void setListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveScore();
            }
        });


        seeAllScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the action to see all scores
                Toast.makeText(AddScoreActivity.this, "See All Scores button clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddScoreActivity.this, SeeScoresActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveScore() {
        String id = currentUser.getId();
        if (id.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a HashMap to store the score
        HashMap<String, Object> scoreMap = new HashMap<>();
        scoreMap.put("id", id);
        scoreMap.put("score", score);

        // Save the score to Firestore
        db.collection("scores").document(id).set(scoreMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddScoreActivity.this, "Score saved", Toast.LENGTH_SHORT).show();
                        saveButton.setEnabled(false);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddScoreActivity.this, "Failed to save score", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
