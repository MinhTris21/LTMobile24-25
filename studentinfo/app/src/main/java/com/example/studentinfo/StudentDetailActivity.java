package com.example.studentinfo;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        ImageView avatar = findViewById(R.id.detail_avatar);
        TextView fullName = findViewById(R.id.detail_full_name);
        TextView studentId = findViewById(R.id.detail_student_id);
        TextView phone = findViewById(R.id.detail_phone);
        TextView email = findViewById(R.id.detail_email);
        TextView gender = findViewById(R.id.detail_gender);
        TextView year = findViewById(R.id.detail_year);

        long studentIdExtra = getIntent().getLongExtra("STUDENT_ID", -1);
        StudentDbHelper dbHelper = new StudentDbHelper(this);
        Cursor cursor = dbHelper.getStudentById(studentIdExtra);

        if (cursor.moveToFirst()) {
            String avatarName = cursor.getString(cursor.getColumnIndex("avatar"));
            int resId = getResources().getIdentifier(avatarName, "drawable", getPackageName());
            if (resId != 0) {
                avatar.setImageResource(resId);
            } else {
                avatar.setImageResource(android.R.drawable.ic_menu_info_details);
            }
            fullName.setText(cursor.getString(cursor.getColumnIndex("full_name")));
            studentId.setText(cursor.getString(cursor.getColumnIndex("student_id")));
            phone.setText(cursor.getString(cursor.getColumnIndex("phone")));
            email.setText(cursor.getString(cursor.getColumnIndex("email")));
            gender.setText(cursor.getString(cursor.getColumnIndex("gender")));
            year.setText(cursor.getString(cursor.getColumnIndex("year")));
        }
        cursor.close();
    }
}