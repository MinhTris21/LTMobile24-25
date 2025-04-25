//FIRST VIEW, TRANG HOC SINH LOP 1
package com.example.writing;
//CAC THU VIEN DUOC SU DUNG TRONG BAI LAM
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1; // Unique request code

    private EditText editTextStudent, editTextTeacher;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextStudent = findViewById(R.id.editTextStudent);
        editTextTeacher = findViewById(R.id.editTextTeacher);
        btnNext = findViewById(R.id.btnNext);
        // SET TEXTBOX CUA GIAO VIEN KHONG THE DUOC CHINH SUA TRONG VIEW NAY
        editTextTeacher.setFocusable(false);
        editTextTeacher.setClickable(false);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CLICK BUTTON VA CHUYEN SANG VIEW THU HAI
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("CORRECTED_TEXT", editTextTeacher.getText().toString());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            String correctedText = data.getStringExtra("RETURNED_TEXT");
            editTextTeacher.setText(correctedText);
        }
    }
}
