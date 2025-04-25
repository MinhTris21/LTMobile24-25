package com.example.gptb2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextA, editTextB, editTextC;
    private Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextA = findViewById(R.id.editTextA);
        editTextB = findViewById(R.id.editTextB);
        editTextC = findViewById(R.id.editTextC);
        btnResult = findViewById(R.id.btnResult);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ensure inputs are not empty
                if (editTextA.getText().toString().trim().isEmpty() ||
                        editTextB.getText().toString().trim().isEmpty() ||
                        editTextC.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter values for a, b, and c!", Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    double a = Double.parseDouble(editTextA.getText().toString().trim());
                    double b = Double.parseDouble(editTextB.getText().toString().trim());
                    double c = Double.parseDouble(editTextC.getText().toString().trim());

                    // Ensure 'a' is not zero (quadratic equation requirement)
                    if (a == 0) {
                        Toast.makeText(MainActivity.this, "Value of 'a' cannot be zero!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    // Pass values to ResultActivity
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("a", a);
                    intent.putExtra("b", b);
                    intent.putExtra("c", c);
                    startActivity(intent);

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid input! Enter valid numbers.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
