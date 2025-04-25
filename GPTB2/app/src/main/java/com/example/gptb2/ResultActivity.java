package com.example.gptb2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView resultText;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.resultText);
        btnBack = findViewById(R.id.btnBack);

        // Get the passed data
        Intent intent = getIntent();
        double a = intent.getDoubleExtra("a", 0);
        double b = intent.getDoubleExtra("b", 0);
        double c = intent.getDoubleExtra("c", 0);

        // Compute the quadratic formula
        double discriminant = (b * b) - (4 * a * c);
        String result;

        if (discriminant > 0) {
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            result = "2 nghiem cua phuong trinh:\nX1 = " + x1 + "\nX2 = " + x2;
        } else if (discriminant == 0) {
            double x = -b / (2 * a);
            result = "Gia tri nghiem:\nX = " + x;
        } else {
            result = "PT vo nghiem";
        }

        resultText.setText(result);

        // Back Button Click Event
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Closes ResultActivity and returns to MainActivity
            }
        });
    }
}
