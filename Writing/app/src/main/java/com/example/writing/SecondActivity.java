//CAC THU VIEN DUOC SU DUNG TRONG VIEW THU 2
package com.example.writing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText editTextCorrected;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editTextCorrected = findViewById(R.id.editTextCorrected);
        btnBack = findViewById(R.id.btnBack);

        String correctedText = getIntent().getStringExtra("CORRECTED_TEXT");
        editTextCorrected.setText(correctedText);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TRA VE DOAN VAN DA DUOC GIAO VIEN CHINH SUA
                Intent resultIntent = new Intent();
                resultIntent.putExtra("RETURNED_TEXT", editTextCorrected.getText().toString());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
