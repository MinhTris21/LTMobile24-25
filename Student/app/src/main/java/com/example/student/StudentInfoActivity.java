package com.example.student;
//CODE JAVA CHO VIEW THỨ 2, NHẬP CÁC THNG TIN CƠ BẢN CỦA SINH VIÊN
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class StudentInfoActivity extends AppCompatActivity {
    //CÁC THUỘC TÍNH TÊN, MSSV, LỚP, SĐT
    EditText editTextName, editTextStudentID, editTextClass, editTextPhone;
    RadioGroup radioGroupYear;
    //CHUYÊN NGÀNH
    CheckBox checkElectronic, checkComputer, checkNetwork;
    Button buttonDone;
    String purpose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        // Initialize input fields
        editTextName = findViewById(R.id.editTextName);
        editTextStudentID = findViewById(R.id.editTextStudentID);
        editTextClass = findViewById(R.id.editTextClass);
        editTextPhone = findViewById(R.id.editTextPhone);

        // Initialize radio group (Fix the issue)
        radioGroupYear = findViewById(R.id.radioGroupYear);

        // Initialize checkboxes
        checkElectronic = findViewById(R.id.checkElectronic);
        checkComputer = findViewById(R.id.checkComputer);
        checkNetwork = findViewById(R.id.checkNetwork);

        buttonDone = findViewById(R.id.buttonDone);

        // Get Purpose from Intent
        purpose = getIntent().getStringExtra("purpose");

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String studentID = editTextStudentID.getText().toString();
                String className = editTextClass.getText().toString();
                String phone = editTextPhone.getText().toString();

                // Get selected year
                int selectedId = radioGroupYear.getCheckedRadioButtonId();
                String year = "Not selected";
                if (selectedId != -1) {
                    RadioButton selectedYear = findViewById(selectedId);
                    year = selectedYear.getText().toString();
                }

                // Get selected majors
                StringBuilder majorBuilder = new StringBuilder();
                if (checkElectronic.isChecked()) {
                    majorBuilder.append("Điện tử, ");
                }
                if (checkComputer.isChecked()) {
                    majorBuilder.append("Máy tính - HTN, ");
                }
                if (checkNetwork.isChecked()) {
                    majorBuilder.append("Mạng VT, ");
                }

                // Remove trailing comma
                String majors = majorBuilder.toString().trim();
                if (majors.endsWith(",")) {
                    majors = majors.substring(0, majors.length() - 1);
                }

                // GỬI DỮ LIỆU ĐÃ NHẬP TỚI VIEW THỨ 2, SUMMARY VIEW
                Intent intent = new Intent(StudentInfoActivity.this, SummaryActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("studentID", studentID);
                intent.putExtra("class", className);
                intent.putExtra("phone", phone);
                intent.putExtra("purpose", purpose);
                intent.putExtra("year", year);
                intent.putExtra("major", majors);
                startActivity(intent);
            }
        });
    }
}
