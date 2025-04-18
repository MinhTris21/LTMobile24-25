package com.example.quizapp;
//VIEW THU 2
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class QuizSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_selection);

        // Find the "Play Now" button
        Button playNowButton = findViewById(R.id.play_now_button);

        // Set click listener to navigate to CategorySelectionActivity
        playNowButton.setOnClickListener(v -> {
            Intent intent = new Intent(QuizSelectionActivity.this, CategorySelectionActivity.class);
            startActivity(intent);
        });
    }
}