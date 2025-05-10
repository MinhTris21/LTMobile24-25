package com.example.studentinfo;
//CAC THU VIEN SU DUNG TRONG BAI LAM
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView listView;
    private StudentDbHelper dbHelper;
    private List<Student> studentList;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        dbHelper = new StudentDbHelper(this);
        studentList = new ArrayList<>();
        adapter = new StudentAdapter(this, studentList);
        listView.setAdapter(adapter);

        // Clear existing database to avoid duplicates
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM students");
        db.close();

        //CAC MAU DATA DUOC IMPORT VAO SQLITE
        dbHelper.insertStudent("Nguyen Van B", "S101", "0912345678", "abc@example.com", "img_1", "Male", "Freshman");
        dbHelper.insertStudent("Nguyen Mai B", "S102", "0987654321", "Bichtranca@example.com", "img", "Female", "Sophomore");
        dbHelper.insertStudent("Pham Thanh T", "S103", "0901234567", "Thau.pham@example.com", "img", "Female", "Junior");
        dbHelper.insertStudent("Le Quoc H", "S104", "0932145678", "Hoang.le@example.com", "img_1", "Male", "Senior");
        dbHelper.insertStudent("Cao Thi C", "S105", "0978765432", "Chi.caothi@example.com", "img", "Female", "Freshman");

        // Insert new student data
        dbHelper.insertStudent("Tran Van K", "S106", "0945678901", "khoi.tran@example.com", "img", "Male", "Sophomore");
        dbHelper.insertStudent("Vo Thi L", "S107", "0923456789", "lan.vo@example.com", "img_1", "Female", "Junior");
        dbHelper.insertStudent("Bui Minh N", "S108", "0971234567", "nam.bui@example.com", "img", "Male", "Freshman");
        dbHelper.insertStudent("Dang Thi H", "S109", "0908765432", "hoa.dang@example.com", "img_1", "Female", "Senior");
        dbHelper.insertStudent("Ngo Van P", "S110", "0935678901", "phong.ngo@example.com", "img", "Male", "Junior");

        loadStudents();

        // Single-tap listener
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Log.d(TAG, "Item clicked: " + studentList.get(position).getFullName());
            Student student = studentList.get(position);
            Intent intent = new Intent(MainActivity.this, StudentDetailActivity.class);
            intent.putExtra("STUDENT_ID", student.getId());
            startActivity(intent);
        });
    }
        //HAM LOADING THONG TIN CUA MOI CA NHAN
    private void loadStudents() {
        studentList.clear();
        Cursor cursor = dbHelper.getAllStudents();
        Log.d(TAG, "Number of students: " + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                // Check column indices
                int idIndex = cursor.getColumnIndex("_id");
                int fullNameIndex = cursor.getColumnIndex("full_name");
                int studentIdIndex = cursor.getColumnIndex("student_id");
                int phoneIndex = cursor.getColumnIndex("phone");
                int emailIndex = cursor.getColumnIndex("email");
                int avatarIndex = cursor.getColumnIndex("avatar");
                int genderIndex = cursor.getColumnIndex("gender");
                int yearIndex = cursor.getColumnIndex("year");

                // Log missing columns for debugging
                if (idIndex == -1) Log.e(TAG, "Column _id not found");
                if (fullNameIndex == -1) Log.e(TAG, "Column full_name not found");
                if (studentIdIndex == -1) Log.e(TAG, "Column student_id not found");
                if (phoneIndex == -1) Log.e(TAG, "Column phone not found");
                if (emailIndex == -1) Log.e(TAG, "Column email not found");
                if (avatarIndex == -1) Log.e(TAG, "Column avatar not found");
                if (genderIndex == -1) Log.e(TAG, "Column gender not found");
                if (yearIndex == -1) Log.e(TAG, "Column year not found");

                // Use default values if columns are missing
                long id = idIndex != -1 ? cursor.getLong(idIndex) : -1;
                String fullName = fullNameIndex != -1 ? cursor.getString(fullNameIndex) : "Unknown";
                String studentId = studentIdIndex != -1 ? cursor.getString(studentIdIndex) : "Unknown";
                String phone = phoneIndex != -1 ? cursor.getString(phoneIndex) : "";
                String email = emailIndex != -1 ? cursor.getString(emailIndex) : "";
                String avatar = avatarIndex != -1 ? cursor.getString(avatarIndex) : "img";
                String gender = genderIndex != -1 ? cursor.getString(genderIndex) : "N/A";
                String year = yearIndex != -1 ? cursor.getString(yearIndex) : "N/A";

                studentList.add(new Student(id, fullName, studentId, phone, email, avatar, gender, year));
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }
}