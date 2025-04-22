package com.example.geminiclone;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText inputText;
    private MaterialTextView responseText;
    private MaterialButton submitButton;
    private OkHttpClient client;
    private static final String API_KEY = "IMPORT API KEY HERE"; // Replace with your Gemini API key
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        inputText = findViewById(R.id.inputText);
        responseText = findViewById(R.id.responseText);
        submitButton = findViewById(R.id.submitButton);
        client = new OkHttpClient();

        // Set up button click listener using lambda
        submitButton.setOnClickListener(v -> {
            String prompt = inputText.getText().toString().trim();
            if (prompt.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter a prompt", Toast.LENGTH_SHORT).show();
                return;
            }
            sendInferenceRequest(prompt);
        });
    }

    private void sendInferenceRequest(String prompt) {
        // Show loading state
        submitButton.setEnabled(false);
        responseText.setText("Loading...");

        try {
            // Construct the request body for Gemini API
            JSONObject contentPart = new JSONObject();
            contentPart.put("text", prompt);

            JSONArray partsArray = new JSONArray();
            partsArray.put(contentPart);

            JSONObject contentObject = new JSONObject();
            contentObject.put("parts", partsArray);

            JSONArray contentsArray = new JSONArray();
            contentsArray.put(contentObject);

            JSONObject requestBody = new JSONObject();
            requestBody.put("contents", contentsArray);

            RequestBody body = RequestBody.create(JSON, requestBody.toString());
            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(() -> {
                        Toast.makeText(MainActivity.this, "Network error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        responseText.setText("Failed to get response");
                        submitButton.setEnabled(true);
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    // Reset UI state and clear prompt
                    runOnUiThread(() -> {
                        submitButton.setEnabled(true);
                        inputText.setText(""); // Clear the prompt after sending
                    });

                    if (response.isSuccessful()) {
                        String responseBody = response.body() != null ? response.body().string() : null;
                        if (responseBody == null) {
                            runOnUiThread(() -> {
                                Toast.makeText(MainActivity.this, "Empty response from API", Toast.LENGTH_LONG).show();
                                responseText.setText("No response received");
                            });
                            return;
                        }

                        try {
                            JSONObject jsonResponse = new JSONObject(responseBody);
                            String generatedText = jsonResponse.getJSONArray("candidates")
                                    .getJSONObject(0)
                                    .getJSONObject("content")
                                    .getJSONArray("parts")
                                    .getJSONObject(0)
                                    .getString("text");
                            runOnUiThread(() -> responseText.setText(generatedText));
                        } catch (Exception e) {
                            runOnUiThread(() -> {
                                Toast.makeText(MainActivity.this, "Error parsing response: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                responseText.setText("Error parsing response");
                            });
                        }
                    } else {
                        String errorBody = response.body() != null ? response.body().string() : "No error details available";
                        runOnUiThread(() -> {
                            if (errorBody.trim().startsWith("<!doctype html>")) {
                                Toast.makeText(MainActivity.this, "Error: Model not available or not supported on free-tier API", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "API error: " + response.message() + " - " + errorBody, Toast.LENGTH_LONG).show();
                            }
                            responseText.setText("Failed to get response");
                        });
                    }
                }
            });
        } catch (Exception e) {
            runOnUiThread(() -> {
                Toast.makeText(MainActivity.this, "Error preparing request: " + e.getMessage(), Toast.LENGTH_LONG).show();
                responseText.setText("Failed to prepare request");
                submitButton.setEnabled(true);
            });
        }
    }
}
