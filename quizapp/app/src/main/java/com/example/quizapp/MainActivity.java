package com.example.quizapp;
//CAC THU VIEN SU DUNG TRONG CHUONG TRINH, TRONG VIEW THU NHAT
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the "Take Quiz" button
        Button takeQuizButton = findViewById(R.id.take_quiz_button);

        // Set click listener to navigate to QuizSelectionActivity
        takeQuizButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizSelectionActivity.class);
            startActivity(intent);
        });
    }
}