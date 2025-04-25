package com.example.aihelperapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import com.google.gson.annotations.SerializedName;
import android.util.Log;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText questionInput;
    private Button submitButton;
    private TextView responseText;
    private GeminiApiService apiService;
    private static final String API_KEY = "your_gemini_api_key_here"; // Replace with your Gemini API key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        questionInput = findViewById(R.id.question_input);
        submitButton = findViewById(R.id.submit_button);
        responseText = findViewById(R.id.response_text);

        // Set up Retrofit for Gemini API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://generativelanguage.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(GeminiApiService.class);

        // Button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = questionInput.getText().toString().trim();
                if (question.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a question", Toast.LENGTH_SHORT).show();
                    return;
                }
                submitButton.setEnabled(false);
                sendQuestionToAI(question);
                new android.os.Handler().postDelayed(() -> submitButton.setEnabled(true), 10000);
            }
        });
    }

    private void sendQuestionToAI(String question) {
        // Create request body for Gemini
        GeminiRequest request = new GeminiRequest(List.of(new GeminiContent("user", List.of(new GeminiPart(question)))));

        // Make API call
        Call<GeminiResponse> call = apiService.getResponse(API_KEY, request);
        call.enqueue(new Callback<GeminiResponse>() {
            @Override
            public void onResponse(Call<GeminiResponse> call, Response<GeminiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String answer = response.body().candidates.get(0).content.parts.get(0).text;
                    responseText.setText(answer);
                } else {
                    String errorMsg = "Error: " + response.code() + " - " + response.message();
                    try {
                        if (response.errorBody() != null) {
                            errorMsg += "\nDetails: " + response.errorBody().string();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                    Log.e("API_ERROR", errorMsg);
                }
            }

            @Override
            public void onFailure(Call<GeminiResponse> call, Throwable t) {
                String errorMsg = "Network Failure: " + t.getMessage();
                Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                Log.e("API_ERROR", errorMsg, t);
            }
        });
    }

    // Retrofit API interface for Gemini
    interface GeminiApiService {
        @POST("v1beta/models/gemini-1.5-flash:generateContent")
        Call<GeminiResponse> getResponse(@Query("key") String apiKey, @Body GeminiRequest request);
    }

    // Request model for Gemini
    static class GeminiRequest {
        List<GeminiContent> contents;

        GeminiRequest(List<GeminiContent> contents) {
            this.contents = contents;
        }
    }

    static class GeminiContent {
        String role;
        List<GeminiPart> parts;

        GeminiContent(String role, List<GeminiPart> parts) {
            this.role = role;
            this.parts = parts;
        }
    }

    static class GeminiPart {
        String text;

        GeminiPart(String text) {
            this.text = text;
        }
    }

    // Response model for Gemini
    static class GeminiResponse {
        List<GeminiCandidate> candidates;

        static class GeminiCandidate {
            GeminiContent content;
        }
    }
}