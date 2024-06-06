package com.example.ridergame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ridergame.model.UserScore;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private List<UserScore> userScoreList;

    public ScoreAdapter(List<UserScore> userScoreList) {
        this.userScoreList = userScoreList;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        UserScore userScore = userScoreList.get(position);
        holder.scoreTextView.setText("Score: " + userScore.getScore());
        holder.nameTextView.setText("Name: " + userScore.getFirstName() + " " + userScore.getLastName());
    }

    @Override
    public int getItemCount() {
        return userScoreList.size();
    }

    public static class ScoreViewHolder extends RecyclerView.ViewHolder {
        TextView scoreTextView, nameTextView;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            scoreTextView = itemView.findViewById(R.id.scoreTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }
    }
}
