package com.example.logreg;

public class RegisterActivity extends AppCompatActivity {
    EditText newUsername, newPassword;
    Button registerBtn;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        newUsername = findViewById(R.id.newUsername);
        newPassword = findViewById(R.id.newPassword);
        registerBtn = findViewById(R.id.registerBtn);
        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        registerBtn.setOnClickListener(v -> {
            String user = newUsername.getText().toString();
            String pass = newPassword.getText().toString();

            if (!user.isEmpty() && !pass.isEmpty()) {
                prefs.edit().putString(user, pass).apply();
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                finish(); // Go back to login
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}