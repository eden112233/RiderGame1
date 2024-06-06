package com.example.ridergame.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ridergame.R;
import com.example.ridergame.ScoreAdapter;
import com.example.ridergame.activities.BaseActivity;
import com.example.ridergame.model.UserScore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SeeScoresActivity extends BaseActivity {

    private RecyclerView scoresRecyclerView;
    private ScoreAdapter scoreAdapter;
    private List<UserScore> userScoreList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_see_scores);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = FirebaseFirestore.getInstance();

        initializeViews();
        setListeners();
    }

    @Override
    protected void initializeViews() {
        scoresRecyclerView = findViewById(R.id.scoresRecyclerView);
        scoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userScoreList = new ArrayList<>();
        scoreAdapter = new ScoreAdapter(userScoreList);
        scoresRecyclerView.setAdapter(scoreAdapter);
    }

    @Override
    protected void setListeners() {
        Button loadScoresButton = findViewById(R.id.loadScoresButton);
        loadScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadScores();
            }
        });
    }

    private void loadScores() {
        db.collection("scores")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        userScoreList.clear();
                        List<DocumentSnapshot> scoreDocuments = task.getResult().getDocuments();

                        db.collection("users")
                                .get()
                                .addOnCompleteListener(userTask -> {
                                    if (userTask.isSuccessful()) {
                                        for (DocumentSnapshot document : scoreDocuments) {
                                            String userId = document.getString("id");
                                            int score = document.getLong("score").intValue(); // Ensure correct type

                                            for (QueryDocumentSnapshot userDocument : userTask.getResult()) {
                                                if (userDocument.getString("id").equals(userId)) {
                                                    String email = userDocument.getString("email");
                                                    String firstName = userDocument.getString("firstName");
                                                    String lastName = userDocument.getString("lastName");

                                                    // Create a UserScore object and add it to the list
                                                    UserScore userScore = new UserScore(userId, email, firstName, lastName, score);
                                                    userScoreList.add(userScore);
                                                    break; // Break the loop once the user is found
                                                }
                                            }
                                        }
                                        // Notify dataset changed (if you are using an adapter)
                                        // scoreAdapter.notifyDataSetChanged();  // Uncomment if using an adapter
                                    } else {
                                        Toast.makeText(SeeScoresActivity.this, "Failed to load user data", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(SeeScoresActivity.this, "Failed to load scores", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
