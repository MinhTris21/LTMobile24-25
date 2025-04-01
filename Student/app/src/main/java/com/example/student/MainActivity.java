//BÀI TẬP VỀ NHÀ TUẦN 4, BỔ SUNG CÁC CHỨC NĂNG SMS, GỌI ĐIỆN THOẠI, CHỤP ẢNH CAMERA
package com.example.student;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
//CÁC THƯ VIỆN VÀ PACKAGE SỬ DỤNG TRONG BÀI LÀM
public class MainActivity extends AppCompatActivity {
    EditText editTextPurpose;
    Button buttonSendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPurpose = findViewById(R.id.editTextPurpose);
        buttonSendData = findViewById(R.id.buttonSendData);

        buttonSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TẠO TEXTBOX ĐỂ SINH VIÊN NHẬP MỤC TIÊU PHÁT TRIỂN BẢN THÂN
                String purpose = editTextPurpose.getText().toString();
                Intent intent = new Intent(MainActivity.this, StudentInfoActivity.class);
                intent.putExtra("purpose", purpose);
                startActivity(intent);
            }
        });
    }
}