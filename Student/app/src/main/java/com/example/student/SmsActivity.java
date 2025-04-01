package com.example.student;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SmsActivity extends AppCompatActivity {
    EditText editTextMessage;
    Button buttonSendSMS;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSendSMS = findViewById(R.id.buttonSendSMS);

        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");

        buttonSendSMS.setOnClickListener(v -> {
            String message = editTextMessage.getText().toString();
            if (!message.isEmpty() && phone != null) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone, null, message, null, null);
                Toast.makeText(SmsActivity.this, "Tin nhắn đã gửi!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SmsActivity.this, "Vui lòng nhập nội dung tin nhắn!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
