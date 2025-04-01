package com.example.student;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
//VIEW HIỂN THỊ CÁC THÔNG TIN SINH VIÊN ĐÃ NHẬP, B SUNG 3 CHỨC NĂNG THEO YÊU CẦU CỦA ĐỀ BÀI
public class SummaryActivity extends AppCompatActivity {
    TextView textViewSummary;
    Button buttonBack, buttonSMS, buttonCall, buttonCamera;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        textViewSummary = findViewById(R.id.textViewSummary);
        buttonBack = findViewById(R.id.buttonBack);
        buttonSMS = findViewById(R.id.buttonSMS);
        buttonCall = findViewById(R.id.buttonCall);
        buttonCamera = findViewById(R.id.buttonCamera);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String studentID = intent.getStringExtra("studentID");
        String className = intent.getStringExtra("class");
        phone = intent.getStringExtra("phone");
        String purpose = intent.getStringExtra("purpose");
        String year = intent.getStringExtra("year");
        String major = intent.getStringExtra("major");

        String summary = "Mục tiêu: " + purpose + "\n" +
                "Họ tên: " + name + "\n" +
                "MSSV: " + studentID + "\n" +
                "Lớp: " + className + "\n" +
                "SĐT: " + phone + "\n" +
                "Năm: " + year + "\n" +
                "Chuyên ngành: " + major;

        textViewSummary.setText(summary);

        buttonBack.setOnClickListener(v -> {
            Intent backIntent = new Intent(SummaryActivity.this, MainActivity.class);
            backIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(backIntent);
            finish();
        });

        //NÚT NHẤN ĐỂ SMS, VỚI NỘI DUNG LÀ THÔNG TIN ĐÃ NHAP CUA SINH VIEN
        buttonSMS.setOnClickListener(v -> {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("smsto:" + phone));
            smsIntent.putExtra("sms_body", "THÔNG TIN ĐÃ ĐĂNG KÝ"+ "\n-------------------\n" + summary);
            startActivity(smsIntent);
        });

        //CHƯUCS NĂNG GỌI ĐIỆN THOẠI
        buttonCall.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phone));
            startActivity(callIntent);
        });

        //NÚT NHÂN ĐỂ MỞ CAMERA CHỤP ANH
        buttonCamera.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(cameraIntent);
        });
    }
}
